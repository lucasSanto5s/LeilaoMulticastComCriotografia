package servidor;

import Util.CriptoUtil;
import Util.GerenciadorChavesPubPriv;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Base64;
import java.security.PublicKey;
import Util.GerenciadorChaveSimetrica;
import java.security.Signature;
import javax.swing.JOptionPane;
import org.json.JSONObject;

public class GerenciadorClientesLeilao {

    private final List<Cliente> clientes;
    private final GerenciadorChavesPubPriv gerenciadorChaves;
    private final CriptoUtil criptoUtil;
    public String chavePrivada = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCwz++ebv2SxjvvSZBE9s/qfjdOmRNnc+pFlvZ2lFw+HK1lZlw9bXx/N0uD1KeM5633PbKLCdAIVoG+KYTAS14TCJbsDShpsazlKBcPR3jti8lyBIi/gaK57qzNrO8VA2g82W/TRHfcAP3+O5PJKKeHaK1E0nXqVoNP3Nm7i5S/zZf45jSVI059nixASNjo2ZycisMhEIv2XLD4koB2kN4Aj+euMLzVMUjZI7j+oZFSJQD7qVGoaUhsn3EOo9IlSE3WHihubLXS98oJSkYUSLPU890eyw/07WYKpmjfWDlm8DaGjBoC1VQZLtaQ8hVQjBAT6jU9xGjof4YsooyIYSt7AgMBAAECggEAAl4xvnFZFwg6aqivDgvKJ0Do4U+xtiIT+9ObDTKADvR+XZj8Pw+PNvryf9tiVRCppuG3v7hYn2WQZKlH4sIy1wlqtmv+SkNImZcRKMsgJpPhLxQ6RDcdvhft6FR0Rteq8GmvEpxcbvqNn3dRZUSUncuI/bhEP2h7zYWB98q73Se2uOcEljy+359kroTzaQpvZ1O92PsalYgFf2eiQoM+tJxErgVNxK9NR2GWt/gqbtSlbW2gHwGQLB3DPSOu5TSYX0vblo/rf4ZGMOKgFcoKopjqxUPr1spiDI2iknI50lux+QpW6DJ+in9B0lKmddc+JMdlC9uoCadiX6NfHx57dQKBgQDMETXVn+icyp8WKygfWC22i+fXS7LEAOYIyWY828/+XvUs74fgX+5EQuQ/sfbRvGqRNVZzMykvmuA36TorDIE1JcT9n5Xu2T9e4oxHHdGG1xtdNT9p8Dl5BNNYB/IPmgrFFud0khudVIDusNoQYMdnbTPQjR1Jf8gL4yZH8LeJTQKBgQDdzxbeDV7FTwzhjH/cA5Ol2ZW3RIlWfQffKBry+RQcfKhEER6zGjXut2uiB23CW1d8FzfJmCOMcaGLTzPqriyxLVmUgP1RkJS2pdGTY90bmLy2dcEcUR1EGvI1exXvD/nK8I7unCeptDNPkgWIP+sp+NYAzm6CsOYjGZRuGZnj5wKBgQCSuiUtLsdARWhRtkIpF2v2/215nhIxYDwGQVljlOFeksRSxP+70v4s0rPvIHTJpWIyelloh3arpf5l9So/3cPhhQC5I8/YZxhrjxUx5TzMaUphoHGyKGoKm++iMkRX93ia8bcGWC/G1gXGmh6Q/CUkNp6062INgPVDptXVkD1HWQKBgDYOlmAfmg00Kst9Vlvkc0NkN9ymU2JErnpwZ8D3ezNGHiwYkAbOSH6ZLR4b+V/4quWPMwVqkp9Twi8Vam2zbSjxCHfsx8/tEJFC8ESffe+P1c4R/LJOAnfqqUTnbn0Iw7P11J/KZtAxHqAx0i9WhJWCE4W2ybAINM4xGb+LdbnZAoGBAMBBqK0SUWAozT5HF39dnQ8Vg/SoS6z078TSIF+74Yhr7g8JpdhjArbUrDuKXvryjlGEuA5jjsArxQVteb3ys+SvhmSNZedgHeJSfoq+J6xWS0SsG0Z9h39l0omB0N8y0BAE51qdPggyQRXkyRxZlhInULEPsmr18XegoDiCS3P3";
    private static final String ENDERECO_MULTICAST = "224.0.0.1";
    private static final int PORTA = 4446;
    private final GerenciadorLances gerenciadorLances;
    private final GerenciadorMulticast gerenciadorMulticast;

    public GerenciadorClientesLeilao(GerenciadorLances gerenciadorLances, RelogioLeilao relogio, GerenciadorMulticast gerenciadorMulti) {
        this.gerenciadorMulticast = gerenciadorMulti ;
        this.gerenciadorLances = gerenciadorLances;
        this.gerenciadorChaves = new GerenciadorChavesPubPriv();
        this.clientes = new ArrayList<>();
        this.criptoUtil = new CriptoUtil();

    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void iniciarConexao(int porta) {
        try (ServerSocket serverSocket = new ServerSocket(porta)) {
            System.out.println("Servidor aguardando conexões na porta " + porta + "...");

            while (true) {
                Socket socket = serverSocket.accept();

                new Thread(() -> autenticarCliente(socket)).start();
            }
        } catch (Exception e) {
            System.err.println("Erro ao iniciar o servidor: " + e.getMessage());
        }
    }

// Método para autenticar o cliente
    private void autenticarCliente(Socket socket) {
        try (BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream())); PrintWriter saida = new PrintWriter(socket.getOutputStream(), true)) {

            // Recebe a mensagem JSON
            String mensagemJson = entrada.readLine().trim();

            // Parse do JSON
            JSONObject json = new JSONObject(mensagemJson);

            String cpfRecebido = json.optString("cpf", "");
            String nomeRecebido = json.optString("nome", "");
            String mensagemRecebida = json.optString("mensagem", "");
            String assinaturaRecebida = json.optString("assinatura", "");

            // Verificar se algum campo está vazio
            if (cpfRecebido.isEmpty() || nomeRecebido.isEmpty() || mensagemRecebida.isEmpty() || assinaturaRecebida.isEmpty()) {
                System.out.println("Erro: Campos vazios no JSON recebido.");
                saida.println("Erro: Campos vazios no JSON.");
                return;
            }
            // Verifica se o CPF está registrado no gerenciador de chaves
            PublicKey chavePublica = gerenciadorChaves.stringParaChavePublica(gerenciadorChaves.buscarChavePublicaCliente(cpfRecebido));

            if (chavePublica != null) {

                // Verifica se a assinatura corresponde à mensagem
                if (verificarAssinatura(cpfRecebido, nomeRecebido, mensagemRecebida, assinaturaRecebida, chavePublica)) {
                    // Se o CPF for válido e a assinatura for verificada, autentica o cliente
                    System.out.println("Cliente autenticado com sucesso: " + cpfRecebido);
                    // Criação do novo cliente
                    Cliente novoCliente = new Cliente(nomeRecebido, gerenciadorChaves.buscarChavePublicaCliente(cpfRecebido), cpfRecebido);
                    clientes.add(novoCliente);  // Adiciona o cliente à lista
                    String envelopamento = criptoUtil.criarEnvelopamento(
                            this.ENDERECO_MULTICAST,
                            this.PORTA,
                            GerenciadorChaveSimetrica.obterChaveSimetrica(),
                            chavePublica,
                            this.gerenciadorChaves.stringParaChavePrivada(this.chavePrivada)
                    );

                    // Enviar o pacote com o envelopamento digital para o cliente
                    saida.println(envelopamento);
       
                    // Envia o lance inicial e o valor mínimo
                    enviarDadosLeilao();
                } else {
                    JOptionPane.showMessageDialog(null, "Erro: falha na verificação de assinatura ",
                            "Erro de Autenticação", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                System.out.println("CPF não registrado: " + cpfRecebido);
                saida.println("Erro: CPF não registrado");
            }

        } catch (Exception e) {
            System.err.println("Erro ao autenticar cliente: " + e.getMessage());
        }
    }

    private boolean verificarAssinatura(String cpf, String nome, String mensagem, String assinatura, PublicKey chavePublica) {
        try {
            // Concatena exatamente o mesmo conteúdo que o cliente assinou
            String conteudo = "{\"cpf\": \"" + cpf + "\", \"nome\": \"" + nome + "\", \"mensagem\": \"" + mensagem + "\"}";

            // Inicializa a verificação da assinatura com a chave pública
            Signature signature = Signature.getInstance("SHA256withRSA");
            PublicKey publicKey = chavePublica;
            signature.initVerify(publicKey);

            // Usa UTF-8 para garantir que a codificação seja a mesma em ambos os lados
            signature.update(conteudo.getBytes("UTF-8"));

            // Verifica a assinatura
            boolean isValid = signature.verify(Base64.getDecoder().decode(assinatura));

            if (!isValid) {
                System.err.println("Assinatura inválida!");
            }

            return isValid;
        } catch (Exception e) {
            System.err.println("Erro ao verificar assinatura: " + e.getMessage());
            e.printStackTrace();  // Exibe o stack trace para ajudar a depurar
            return false;
        }
    }

    private void enviarDadosLeilao() throws Exception {
        if (this.gerenciadorLances.getItem()!= null) {
            JSONObject dadosLanceInicial = new JSONObject();
            dadosLanceInicial.put("remetente", "servidordeLeilao");
            dadosLanceInicial.put("item", this.gerenciadorLances.getItem().getNomeeDescricao());
            dadosLanceInicial.put("lanceMinimo", this.gerenciadorLances.getLanceAtual());
            dadosLanceInicial.put("intervaloMinimo", this.gerenciadorLances.getIntervaloMin());
             dadosLanceInicial.put("usuario", this.gerenciadorLances.getUsuarioAtual());
            dadosLanceInicial.put("tipo", "iniciarLeilao");
            String mensagem = CriptoUtil.criptografarMensagemComChaveSimetrica(dadosLanceInicial.toString(), GerenciadorChaveSimetrica.obterChaveSimetrica());

            gerenciadorMulticast.enviarMensagem(mensagem);
            return;
        }
        JOptionPane.showMessageDialog(null, "Nenhum Leilão está ocorrendo no momento! ",
                            "", JOptionPane.INFORMATION_MESSAGE);

    }

}
