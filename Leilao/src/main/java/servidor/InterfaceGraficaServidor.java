
package servidor;

import Util.CriptoUtil;
import Util.GerenciadorChaveSimetrica;
import java.awt.Dimension;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import org.json.JSONObject;

/**
 *
 * @author lucas
 */
public class InterfaceGraficaServidor extends javax.swing.JFrame {

    private RelogioLeilao relogioLeilao;
    private GerenciadorItens gerenciadorItens;
    private GerenciadorLances gerenciadorLances;
    private LeilaoServidor leilao;
    private GerenciadorMulticast gerenciadorMulticast;
    private final String ENDERECO_MULTICAST = "224.0.0.1";
    private final int PORTA = 4446;
    private Thread escutaThread;

    public InterfaceGraficaServidor() {
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);

        this.gerenciadorMulticast = new GerenciadorMulticast(ENDERECO_MULTICAST, PORTA);

        this.gerenciadorLances = new GerenciadorLances(0, 0);
        this.relogioLeilao = new RelogioLeilao(gerenciadorLances);

        this.leilao = new LeilaoServidor(this.gerenciadorLances, this.relogioLeilao, this.gerenciadorMulticast);

        this.gerenciadorItens = leilao.getGerenciadorItens();

        // Carrega os itens no ComboBox
        carregarItensNoComboBox();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jT_tempoleilao = new javax.swing.JTextField();
        jB_iniciarLeilao = new javax.swing.JButton();
        jC_selecionaritens = new javax.swing.JComboBox<>();
        jT_temporestante = new javax.swing.JLabel();
        jT_usuarioatual = new javax.swing.JLabel();
        jT_lanceatual = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jT_lanceMinimo = new javax.swing.JTextField();
        jT_valorEntreLances = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Selecionar Item:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Tempo do Leilão (segundos):");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Lance Atual:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Usuário do Lance:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Tempo Restante:");

        jT_tempoleilao.setPreferredSize(new Dimension(64, 22));

        jB_iniciarLeilao.setText("Iniciar Leilão");
        jB_iniciarLeilao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_iniciarLeilaoActionPerformed(evt);
            }
        });

        jC_selecionaritens.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jT_temporestante.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jT_temporestante.setText("0.00");
        jT_temporestante.setPreferredSize(new Dimension(100, 22));

        jT_usuarioatual.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jT_usuarioatual.setText("X");
        jT_usuarioatual.setPreferredSize(new Dimension(100, 22));

        jT_lanceatual.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jT_lanceatual.setText("0.00");
        jT_lanceatual.setPreferredSize(new Dimension(100,22));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Lance Minimo: ");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Valor Mínimo entre os Lances: ");

        jT_lanceMinimo.setPreferredSize(new Dimension(64, 22));

        jT_valorEntreLances.setPreferredSize(new Dimension(64, 22));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(90, 90, 90)
                        .addComponent(jT_usuarioatual, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5))
                                .addGap(97, 97, 97)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jT_temporestante, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jT_lanceatual, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addGap(72, 72, 72)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jT_valorEntreLances, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                                        .addComponent(jT_lanceMinimo)
                                        .addComponent(jT_tempoleilao))
                                    .addComponent(jC_selecionaritens, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(58, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(206, 206, 206)
                .addComponent(jB_iniciarLeilao)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jC_selecionaritens, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jT_tempoleilao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jT_lanceMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jT_valorEntreLances, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jT_lanceatual, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jT_usuarioatual))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jT_temporestante, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(91, 91, 91)
                .addComponent(jB_iniciarLeilao)
                .addGap(64, 64, 64))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void escutar() {
        escutaThread = new Thread(() -> {
            try {

                MulticastSocket socket = gerenciadorMulticast.socket;

                byte[] buffer = new byte[1024];
                DatagramPacket pacote = new DatagramPacket(buffer, buffer.length);

                while (true) {

                    socket.receive(pacote);

                    // Verifica se a mensagem foi recebida
                    String mensagemRecebida = new String(pacote.getData(), 0, pacote.getLength()).trim().replaceAll("[\r\n]", "");

                    try {
                        // Decifra a mensagem recebida
                        String mensagemDecifrada = CriptoUtil.descriptografarMensagemComChaveSimetrica(
                                mensagemRecebida,
                                GerenciadorChaveSimetrica.obterChaveSimetrica()
                        );

                        // Processa o JSON
                        JSONObject mensagemJson = new JSONObject(mensagemDecifrada);

                        // Verifica se o campo "remetente" existe e é igual a "cliente"
                        if (!mensagemJson.has("remetente") || !mensagemJson.getString("remetente").equals("cliente")) {

                            continue; // Ignora a mensagem e continua escutando
                        }

                        // Verifica se o campo "nome" e "valor" existem
                        if (!mensagemJson.has("nome") || !mensagemJson.has("valor")) {

                            continue; // Ignora a mensagem e continua escutando
                        }

                        String nome = mensagemJson.getString("nome");
                        double valor = mensagemJson.getDouble("valor");

                        System.out.println("Lance recebido de " + nome + ": R$" + valor);

                        // Registrar o lance e atualizar a interface, se for maior que o lance atual
                        if (gerenciadorLances.registrarLance(nome, valor)) {
                            SwingUtilities.invokeLater(() -> {
                                jT_lanceatual.setText("R$ " + valor);
                                jT_usuarioatual.setText(nome);
                            });

                            // Envia o lance para todos os clientes
                            JSONObject dadosLance = new JSONObject();
                            dadosLance.put("remetente", "servidordeLeilao");
                            dadosLance.put("tipo", "lance");
                            dadosLance.put("nome", nome);
                            dadosLance.put("valor", valor);
                            dadosLance.put("tempo", relogioLeilao.getTempoRestante());
                            enviar("lance", dadosLance);
                        }

                    } catch (Exception e) {
                        System.err.println("Erro ao decifrar/processar mensagem: " + e.getMessage());
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Erro ao escutar mensagens no multicast.");
            }
        });
        this.escutaThread.start();
    }

    public void enviar(String tipo, JSONObject dados) {
        try {
            dados.put("tipo", tipo);
            String mensagem = CriptoUtil.criptografarMensagemComChaveSimetrica(dados.toString(), GerenciadorChaveSimetrica.obterChaveSimetrica());

            gerenciadorMulticast.enviarMensagem(mensagem);
        } catch (Exception e) {
            System.err.println("[SERVIDOR] Erro ao enviar mensagem: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para carregar itens no ComboBox
    private void carregarItensNoComboBox() {
        try {
            jC_selecionaritens.removeAllItems(); // Limpa itens antigos
            for (ItemLeilao item : gerenciadorItens.getItens()) {
                jC_selecionaritens.addItem(item.getNome());
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar itens no ComboBox: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void jB_iniciarLeilaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_iniciarLeilaoActionPerformed
        String item = jC_selecionaritens.getSelectedItem().toString().trim();
        String tempoStr = jT_tempoleilao.getText().trim();
        String lanceMinimoStr = jT_lanceMinimo.getText().trim();
        String intervaloMinimoStr = jT_valorEntreLances.getText().trim();

        try {
            // Validações
            if (item.isEmpty()) {
                throw new IllegalArgumentException("O item não pode estar vazio.");
            }

            int tempo = Integer.parseInt(tempoStr);
            if (tempo <= 0) {
                throw new IllegalArgumentException("O tempo deve ser maior que 0.");
            }

            double lanceMinimo = Double.parseDouble(lanceMinimoStr);
            if (lanceMinimo <= 0) {
                throw new IllegalArgumentException("O lance mínimo deve ser maior que 0.");
            }

            double intervaloMinimo = Double.parseDouble(intervaloMinimoStr);
            if (intervaloMinimo <= 0) {
                throw new IllegalArgumentException("O intervalo mínimo deve ser maior que 0.");
            }

            // Desativa o botão "Iniciar" enquanto o leilão está em andamento
            jB_iniciarLeilao.setEnabled(false);

            // Atualiza informações iniciais
            jT_lanceatual.setText("0.0");
            jT_usuarioatual.setText("");

            System.out.println("Leilão iniciado para o item: " + item + " com tempo de " + tempo + " segundos.");

            // Inicializa o GerenciadorLances com os valores mínimos
            this.gerenciadorLances.setLanceMin(lanceMinimo);
            this.gerenciadorLances.setIntervaloMin(intervaloMinimo);
            this.gerenciadorLances.setItem(this.gerenciadorItens.obterItemPorNome(item));
            // Envia o lance inicial e o valor mínimo

            JSONObject dadosLanceInicial = new JSONObject();
            dadosLanceInicial.put("remetente", "servidordeLeilao");
            dadosLanceInicial.put("item", this.gerenciadorLances.getItem().getNomeeDescricao());
            dadosLanceInicial.put("lanceMinimo", lanceMinimo);
            dadosLanceInicial.put("intervaloMinimo", intervaloMinimo);
            dadosLanceInicial.put("usuario", this.gerenciadorLances.getUsuarioAtual());
            enviar("iniciarLeilao", dadosLanceInicial);

            // Inicia o relógio do leilão
            relogioLeilao.iniciarContagem(tempo,
                    () -> SwingUtilities.invokeLater(() -> jT_temporestante.setText(relogioLeilao.getTempoRestante() + " segundos")),
                    () -> {
                        // Encerramento do leilão
                        encerrarLeilao(item);  // Função que lida com o encerramento do leilão
                    },
                    item,
                    this // Passa a referência da InterfaceGraficaServidor
            );

            // Atualiza o tempo restante na tela imediatamente
            jT_temporestante.setText(tempoStr + " segundos");

            // Inicia a escuta de mensagens
            escutar();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Valor inválido. Certifique-se de que os campos de tempo, lance mínimo e intervalo mínimo são números válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jB_iniciarLeilaoActionPerformed

    // Função separada que lida com o encerramento do leilão e o envio do vencedor
    private void encerrarLeilao(String item) {
        // Exibe uma mensagem informando que o leilão foi encerrado

        // Realiza qualquer outra lógica que deva ocorrer ao finalizar o leilão
        // Por exemplo, pode exibir o vencedor do leilão e o valor final
        // Suponha que você tenha uma classe GerenciadorLances com o método getVencedor
        String vencedor = gerenciadorLances.getUsuarioAtual();
        double valorFinal = gerenciadorLances.getLanceAtual();

        this.gerenciadorLances.zerarLances();
        this.escutaThread.stop();

        // Pode enviar uma mensagem com o vencedor e o valor final para os clientes
        JSONObject dadosVencedor = new JSONObject();
        dadosVencedor.put("remetente", "servidordeLeilao");
        dadosVencedor.put("item", item);
        dadosVencedor.put("vencedor", vencedor);
        dadosVencedor.put("valor", valorFinal);
        enviar("vencedor", dadosVencedor);

        // Você pode resetar o estado ou preparar para um novo leilão
        prepararNovoLeilao();
    }

    private void prepararNovoLeilao() {
        // Lógica para preparar o sistema para um novo leilão
        // Por exemplo, limpando informações antigas, habilitando o botão de "Iniciar Leilão", etc.

        jT_temporestante.setText("");
        jT_lanceatual.setText("");
        jT_usuarioatual.setText("");
        jB_iniciarLeilao.setEnabled(true);
    }

    /**
     * @param args the command line arguments
     */
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new InterfaceGraficaServidor().setVisible(true));
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jB_iniciarLeilao;
    private javax.swing.JComboBox<String> jC_selecionaritens;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField jT_lanceMinimo;
    private javax.swing.JLabel jT_lanceatual;
    private javax.swing.JTextField jT_tempoleilao;
    private javax.swing.JLabel jT_temporestante;
    private javax.swing.JLabel jT_usuarioatual;
    private javax.swing.JTextField jT_valorEntreLances;
    // End of variables declaration//GEN-END:variables
}
