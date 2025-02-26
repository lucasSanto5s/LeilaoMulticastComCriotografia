package cliente;

import java.io.*;
import java.net.Socket;
import javax.crypto.SecretKey;
import java.security.PrivateKey;
import java.security.Signature;
import java.util.Base64;
import Util.GerenciadorChavesPubPriv;
import Util.CriptoUtil;
import java.security.PublicKey;
import javax.swing.JOptionPane;
import org.json.JSONObject;



/**
 * Classe para gerenciar a conexão do cliente com o servidor.
 */
public class ClienteConexao {

    private String host;
    private int porta;
    private Cliente cliente;
    private Socket socket;
    private BufferedReader entrada;
    private PrintWriter saida;
    protected SecretKey chaveSimetrica;
    private CriptoUtil CriptoUtil;
    GerenciadorChavesPubPriv gerenciadorPubPriv;
    protected int MulticastPort;
    protected String MulticastHost;


    public ClienteConexao(String host, int porta, Cliente cliente) throws Exception {
        this.host = host;
        this.porta = porta;
        this.cliente = cliente;  // Inicializa o cliente
        this.CriptoUtil = new CriptoUtil();
        gerenciadorPubPriv = new GerenciadorChavesPubPriv();
        conectar();

    }

    /**
     * Conecta ao servidor e realiza a autenticação.
     */
   private void conectar() throws Exception {

        socket = new Socket(host, porta);
        entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        saida = new PrintWriter(socket.getOutputStream(), true);

        // Dados que serão enviados em claro
        String mensagem = "join";  // Exemplo, a mensagem que o cliente quer enviar ao servidor
        String cpf = cliente.getCpf();
        String nome = cliente.getNome();

        // Cria o JSON com os dados em claro
        String mensagemJson = "{\"cpf\": \"" + cpf + "\", \"nome\": \"" + nome + "\", \"mensagem\": \"" + mensagem + "\"}";

        // Assina o JSON com a chave privada
        String assinatura = assinarMensagem(mensagemJson);

        // Adiciona a assinatura no JSON
        String mensagemComAssinatura = "{\"cpf\": \"" + cpf + "\", \"nome\": \"" + nome + "\", \"mensagem\": \"" + mensagem + "\", \"assinatura\": \"" + assinatura + "\"}";

        // Envia a mensagem assinada para o servidor
        saida.println(mensagemComAssinatura);

        String mensagemRecebida = entrada.readLine(); // Supondo que entrada é um BufferedReader conectado ao servidor
        processarMensagemEnvelopada(mensagemRecebida,
                gerenciadorPubPriv.stringParaChavePrivada(cliente.getChave()), gerenciadorPubPriv.stringParaChavePublica(gerenciadorPubPriv.getChavePublicaServidor()));

        System.out.println("Chave simétrica e dados do servidor multcast recebidos.");

    }

    // Método para receber e desenvelopar a mensagem do servidor
    public void processarMensagemEnvelopada(String mensagemEnvelopada, PrivateKey chavePrivadaCliente, PublicKey chavePublicaServidor) throws Exception {
        // Converter a mensagem JSON recebida em um objeto JSON
        JSONObject json = new JSONObject(mensagemEnvelopada);

        // Extrair partes do envelopamento
        String chaveSimetricaCriptografada = json.getString("chaveSimetrica");
        String dadosConexaoCriptografados = json.getString("dadosConexao");
        String assinatura = json.getString("assinatura");

        // 1. Decifrar a chave simétrica com a chave privada do cliente
        this.chaveSimetrica = CriptoUtil.decifrarChaveSimetrica(chaveSimetricaCriptografada, chavePrivadaCliente);
         String chaveBase64 = Base64.getEncoder().encodeToString(chaveSimetrica.getEncoded());

  
        // 2. Decifrar os dados de conexão usando a chave simétrica
        String dadosConexaoDecifrados = CriptoUtil.descriptografarMensagemComChaveSimetrica(dadosConexaoCriptografados, chaveSimetrica);
      

        JSONObject jsonParaVerificacao = new JSONObject();
        jsonParaVerificacao.put("chaveSimetrica", chaveSimetricaCriptografada);
        jsonParaVerificacao.put("dadosConexao", dadosConexaoCriptografados);

// Obter a string original que foi assinada
        String mensagemAssinada = jsonParaVerificacao.toString();

        // 3. Verificar a assinatura digital com a chave pública do servidor
        boolean assinaturaValida = CriptoUtil.verificarAssinatura(assinatura, mensagemAssinada, chavePublicaServidor);
        if (assinaturaValida) {
            System.out.println("Assinatura do servidor validada com sucesso!");
        } else {
            throw new SecurityException("Assinatura do servidor inválida!");
        }

        // 4. Processar os dados de conexão
        JSONObject dadosConexao = new JSONObject(dadosConexaoDecifrados);
        this.MulticastHost = dadosConexao.getString("enderecoMulticast");
        this.MulticastPort = dadosConexao.getInt("porta");
      
    }


    /**
     * Assina a mensagem com a chave privada do cliente
     *
     * @param mensagem Mensagem a ser assinada
     * @return Assinatura em Base64
     */
    private String assinarMensagem(String mensagemJson) throws Exception {
        // Obter a chave privada para assinar
        PrivateKey chavePrivada = gerenciadorPubPriv.stringParaChavePrivada(cliente.getChave()); // A chave privada que será usada para assinar a mensagem
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(chavePrivada);
        signature.update(mensagemJson.getBytes("UTF-8"));
        byte[] assinaturaBytes = signature.sign();
        return Base64.getEncoder().encodeToString(assinaturaBytes);
    }

    /**
     * Envia um lance ao grupo multicast com CPF e valor do lance em formato
     * JSON.
     *
     * @param valor Valor do lance.
     */
    public void enviarLance(double valor, GerenciadorMulticast gerenciadorMulticast ) {
        try {
            if (chaveSimetrica == null) {
                throw new IllegalStateException("A chave simétrica não foi configurada.");
            }
            String nome = this.cliente.getNome();
            // 1. Criar a mensagem do lance em formato JSON
            JSONObject mensagemJson = new JSONObject();
            mensagemJson.put("remetente", "cliente");
            mensagemJson.put("nome", nome);
            mensagemJson.put("valor", valor);

     

            // 2. Cifrar a mensagem usando a chave simétrica
            String mensagemCifrada = CriptoUtil.criptografarMensagemComChaveSimetrica(mensagemJson.toString(), chaveSimetrica);
       

            // Não faça a codificação novamente, apenas envie a mensagem cifrada
            String mensagemBase64 = mensagemCifrada;  // Não codificar novamente em Base64

// Enviar a mensagem cifrada (agora em Base64) para o GerenciadorMulticast
            gerenciadorMulticast.enviarMensagem(mensagemBase64);  // Envia 
      

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao enviar lance via multicast: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    

    /**
     * Fecha a conexão com o servidor.
     */
    public void desconectar() {
        try {
            if (socket != null) {
                socket.close();

            }
        } catch (IOException e) {
            System.err.println("Erro ao fechar a conexão: " + e.getMessage());
        }
    }

}
