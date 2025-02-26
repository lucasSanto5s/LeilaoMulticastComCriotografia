/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package cliente;

import Util.CriptoUtil;
import javax.swing.JOptionPane;
import java.awt.Dimension;
import java.io.IOException;
import java.net.DatagramPacket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import org.json.JSONObject;

/**
 *
 * @author lucas
 */
public class InterfaceGraficaCliente extends javax.swing.JFrame {

    private Cliente cliente;
    private ClienteConexao conexao; // Conexão do cliente com o servidor
    private final String chave1 = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDqMTXXwF+E2wUMgjFP/B7l0smPkcZ+ZOpantVUc3OIEXNE8CMRRAeOopeD5sbrcDHpXFWcsIIQf9SU+o0uB3CIldBWeqJ7ES1cG4kCboKeGDgAK5FA5wcPluSDzCNATlhlB+Osj8DNvpZTpyveOyOD/7nhFgBWNDwYxkXQBj7pdjZS5j3BiJsu/ssHWSdu3edbyRePsKA+DQq3ORcg4Ju7o6hUyN6EebyNdEg6vlzeng5j+MF8NuMuVdOBx3rR8/qKHnLYj1hB0UfwvD90R1Vgz7kKF+uAGx1TVW3tbFDW1Zbp7aU8ZmkyhKhanwjifUR4cP4KyCUq2PKqpwAa/kJ1AgMBAAECggEAA/6rZl8Bx3HMpqo43kj/cO2qd7BlpFB4iUEGLrmFGhLWy8ap3nichWPDdbdJI4knfM17qLTVFHl4aO3Thy+5d8b7Gw0z+ilF7rk0hNiu5zSkDegFrgeoK0KoarwfyiVxT2Xgoxf/qAgifHz7RqrtFT/BOLRH9LB8PvLw4WvIhRxxVAiV3OQ2fjOv2hAfgCry242ez80ntvMj8YKS8nx8KGEybCvfV1UdtOEVlGIf5GRBBYDLSBcao32+wFFq4/yPwQTEXd3/0T8GivuDjM12sjFtBun5+ox5kpB4CnWNi3bYxzUeD0RRFJHIRLic59KfYgeKvngoeV3eVW+5JS6FwQKBgQDrGkZnPR/5XM/kPPl9tWBZP5po9IvXkp7HLJzroXxMjrwGI7fZNigPZFdUjqwaOOx14O0mOJwlC/bI2S/QYXqKy4mpx7KLAKVlENJjzKn+YdiC+WNhd1H4NSj8C9pEclSVRYYChUr5j0m6vrAN4snzAI2LAf1q+UCopmZLPRjXtQKBgQD/AjgWc3IRCbFmDkvXE2cRw5qDdTuuEBbUk6un9oYv3zZqcR5C9GjCDsTfpG3y6g3dCalllzb2QUY8Pbn8mFS0I5o2UvpgYu1hTGg4Dx5I5VQE7LuWG7BKQ4KBM8c2ixOfrResZddmzyI7yrbdvXTejZppszn+rWdtukYH9RT3wQKBgQDXzYWEbGI2JhCaA7JLxrqLEcvtvVPD7E0crIHZ9KBBMd+7jvvKQWDiSAu1jUTCyS+cQHvO0rXZd7LuyBWQpHm0f4J8utbTqyMmsxs8CnicDTqNdtfXdoxiVoGpYkPXAk9LucZ599pLW1ClPzYheORAz2gdihURJXxJknxhYdK05QKBgQDtunvfMjCcgZTHt3wsPQJRJTDebGP+mTEva5sTMldWOZjZEitL793ZSztt4jQ9yMSz01YAR0ANwjxf3IuOV9RfOvGAw0p2oML7eeeOm0l4W5JAJRGblqR6FwWKvWJyDqV0rkDrjuWICoG3i/t+LCjPcOMKtvLR4YYSXRz8PhYWAQKBgQDo837uQodJgPhnNsjsVyHe01K4qbdE7n5akQeyAfaeUAno1eeXT6M5GfvQ3p93wNnLA+/koFMAw8qDx+6IH4nNgzox9y2inR4JiDaR04lMOyuN4pqcR4h9096fiCdmHo7/U0EmTb9ATUkt9L2dbVZz5W0K0s/NFiCb4tLj7hlurA==";
    private final String chave2 = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCjHbi/kLCC0rss3lx5M4jbRJYEo+ONpVFywwkOsoXfxlILk1Q/26OEkfhNHKT7sHHURSMoxPZkhRhUfIJGt6A/RYzj05MiPoyAsSmEDMhzBfkxluWRWNRkYb7yun7DvlJyvL5C6k/3p2Ywl4NqRWwn7Fp5lCGjtPKSMZiwa3TWtrh2Z4YgW7vvRo1QBW8c6nuFBDMjgsa77wGLGzJ3W7UrynNog7jeAhkdOxy1Vj0Ky3GDSLM9DaPoPfgmsj/09itnETRDvAWF8MmHf/VUw3gqHc9wjjJELd6hBeKT3Oi3sSxX3oNL69j6QlOnVW/N5ZDkun6J/PH8nOBTTpM4KTcJAgMBAAECggEAFaKJERuLVebGfcaGhGrh8ZdQwI/OTEjLm0mkJ2dlW09uLWveplOnBhvoT64HcfyutGVxKuSo+58toXMcQm3zmsDKVaOH5ehe8WjOJV4eribyLPIjQ9qA3Umbro0u6B6Tx/Uh1TVH4wS4iKRoAU3w8QCn5W1Q4upKrDpY0rmF9z5x1YOe2Jw8Gy9G4J9O65V5WuEYVHZzYfkRJiSqd/8Be76E5n4ZurNpfshxc15cnWW5kpRQx+XitfF5DaLQtWBlnMYbwgW7JIoHwR5p+ZjaSC0fDXIG4a9vIHv2YjMo83RbB1i8woQcKHztcVvbYz58V38GgyJ/9pBaq3+By3dL2wKBgQDJolJFk2GW7QwgxzfnApzhSUA/pqIP5CK5LXwSIATf2xbLOtln9e09eEz3UVoVE3QEiK+QG1e7kk+EmsfJwvjzdQnt36C9YnlDF9cNf2y6eqCWnmGRzU0yYBKYEgkjsl/2/Xc2UofhOzxvSDfat7PFP2P+SwqYbnU8aILvEGgnpwKBgQDPGLgakTc0RDsKZJo99r6bRL/WKv8kLkvM7oY8uhLF+pK91skouhrUqKDNPEp+0sS7lqkeS6S4SrwbA71kW3PcZJmKcpyBqZhLi48xQOmZrmM5mXb3JWwoI/BVy/QLCVBAczj8gem2fs2ksNUVD8cqssz+2YDjYfCmJSHOJxqBzwKBgQCk2xlnF2QP8QocdqWnM8SukaR9JjGUypL1UlIBw7oYiNmvGhOM406wFn7fKt17I+eUmWjpauGlU6++HIrC1K2U6BlRKqkSjsPUblZ/C7MLGJ3Z5+6gNfoq+M/bE8vzKrE5kOz6Niowpaz401QPeGj8GaMY06P7Ezy5Qj3jVkfCOwKBgClmKp98+gkjvp20QLU0VyQY2TSKAdkMvDBqWkFXfRtAnQcAIdRXZ7etQf/lFotFvPg3BPW+b1476mCIHdsrAP1EywDHKowtB/nKG0TgUhJbrxA7onGDdJyNmqS8EbwHD5jir7iii8LklF1g0CfWsJzTg3FKr55E3OOJtrlMTds3AoGAGZIJcslVxYgEkNSRZudNmI8aqBtI+f8TjK/ECMfTGRlHi7cI91NNA8plFV1vuQhUDmYNK5lWc3VCwTfzsnXAuFAQU2EwA3I+iqf60wBNzSs8zUZ0c2DtT1rpzWU/xkxvI56cwpW7p2WfrZSqQ5Eyb/orxt7m+cpr1QlvBVKrT7g=";
    private final String chave3 = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCmIfaxPx/t43dUQ1JRJpgmN5tQljpmI1D5K4ORLN1C/SmtjI/hGzvY9D4ydf+FNQHf7JGp7JZPtNDSbkCHAo4mDx5q2BwSRHQhpoZeHfJ/XFmbretg9TJlQkvtzAkMXaP91bID+Ct1r3WC1r1SvnVxFpEc60eWhdZX4gGtJZdEiILFXTEdogtAMSYhcwRF0gnyblkJgyMef8eqQLwypk1uJP7LkUfApSGQ4/txUo1tHSWGC2f+/mnGTs/JeG/dJT1XQCsi7OLKSmFnvuY+9o243nUPDQjXLHt9AzHpbl4zSD07F/vicr9Mmxs0aX0elTPKBUX3QKlra4MVJVTllptbAgMBAAECggEAAe+UC/1b+439U6UJLjA7Rk6655XTOQzLomuoYnuvBFsmLi3cy1cMO9Cfcj+/v3yeTflYD9kq6I4x1LyQ0eyXXXLVpucSb1VkM7QuDVC0r66SzhFDvzh8OC6Clf3HqTY6H/i/1J4CsPdfcNaHFDzAlOsaER0zaK9HOnTuvLwbEXhaQFlEKGuy9J3bQI1A1Qigo7JeFecM+Auk9NFv1knmGD+VphI5mNtPnx0bWZx0ce7iXouWg3nzmRoSU1ZCsL5Y2mhHW8tjgE+ZMhIuw7bF+RBhRl4xIo9sxSzuRAGfrzd/840jq1P2/MlmmtlSKqGKi8NEUPtrI8zMi/JyKpfr+QKBgQDlc7Nf/MXQ8TQGm51b6YGWEw5F/bXACQ4jsLhtOG1XqbByvgwzy0B5NzlZzcQstfQt00tKMe2dcYvJDLiHegCQLJRazG6UVhJm2aEqjPMQRddaeBLqdjHFWd8Kjnh5p87dHxk51C5NsX0OfaKpwNg77mBZgk10dXKC4ojQGBi1XwKBgQC5WsOu0R/txX91NBgD5HwkNC9Q+N9PDL73ETOcuteBK+x69cLe2psjkhNKIMWryZlHOdxBbM/Z8hfOgY26A14/LvwfbSZg4VtXvyfYVftTfTCO4rFuBZxKcMpG/9ECi4YrWaVnDZubtD2nKzx8ABW+UicwFrqf75+2+vdwOqI/hQKBgQDTHd3RV7Z7RTNlURE8XGFLQEVFyObNxocn95X5iI2ytwMO0CZSEZVE8rbmFhmGOfRtpZgEsGO9FZhUaEvw+yRBg4MM+ERWX8uWrm9Hw2BxfJFlQE3HRPEOcF50TBo1dV00v0RqnPvtqH00NAzSvAk5O1+Te/zgRqASJXVUYpd5aQKBgDRMxAJDx7qTi49Kj6wpgE3G28MBNiFb+1ZDaEjE/6HW/vcC+WVgUX4L7Z1w+dWrILDURq2/QjbA9K60J4VhBerWGxXS+o1DiT6NFXUZbbz1CgqHNIFS2/K1OJ4n+JW7QLRiqyUU1d9tUCgnLv3eGESHcb9D6ozOVEaRMdhc8wLxAoGBAKHELDDZlZAT/TnzX88P5Pvwm/id4qLWIiD5ZnA5vhJlmhGO/qc+GRYzH7r0rg3uOg1/YxWmwUz7tfyVaUJX0SlXyOFFWwO1rm/8cwPjUwB6U2IgDBykjcLa6wo7iVkQCMmKGOZNY4/pOR/3CY9S/MnPpwmBOCUtTMr/rU7sAs1b";
    private GerenciadorMulticast gerenciadorMulticast;
    private Thread escutaThread;

    public InterfaceGraficaCliente() {
        initComponents();
        jl_status.setText("Status: desconectado");
        jB_enviarLance.setEnabled(false);
        jB_desconectar.setEnabled(false);

    }

    private void conectarAoServidor() {
        try {
            String portaTexto = jT_porta.getText();
            String cpf = jT_Cpf.getText();
            String nome = jT_nome.getText();

            // Verifica se a porta foi preenchida
            if (portaTexto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Digite a porta do servidor.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Verifica se o CPF foi preenchido e é válido
            if (cpf.isEmpty() || !cpf.matches("\\d{11}")) {
                JOptionPane.showMessageDialog(this, "Digite um CPF válido (11 números).", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Verifica se o nome foi preenchido
            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Digite o nome do cliente.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Verifica se a chave privada foi selecionada
            String chavePrivada = "";
            int chaveSelecionada = jC_chavePrivada.getSelectedIndex(); // Obtém o índice da chave selecionada

            switch (chaveSelecionada) {
                case 0:
                    chavePrivada = chave1;
                    break;
                case 1:
                    chavePrivada = chave2;
                    break;
                case 2:
                    chavePrivada = chave3;
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Selecione uma chave privada.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
            }

            if (chavePrivada.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Chave privada não selecionada.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Converte a porta para inteiro
            int porta = Integer.parseInt(portaTexto);

            // Cria o cliente passando o nome, chave privada e CPF
            cliente = new Cliente(nome, chavePrivada, cpf);

            // Cria uma nova conexão com o servidor, passando a chave privada selecionada
            conexao = new ClienteConexao("26.254.124.182", porta, cliente);
            this.gerenciadorMulticast = new GerenciadorMulticast(conexao.MulticastHost, conexao.MulticastPort);
            // Atualiza o status da conexão
            jl_status.setText("Status: Conectado ao servidor.");

            escutar();
            // Habilita os botões após a conexão

            jB_desconectar.setEnabled(true);
            jB_conectar.setEnabled(false); // Desabilita o botão Conectar
            JOptionPane.showMessageDialog(this, "Conectado ao servidor!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Digite um número válido para a porta.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            jl_status.setText("Status: Erro ao conectar.");
            JOptionPane.showMessageDialog(this, "Erro ao conectar: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void enviarLance() {
        try {
            String lanceTexto = jT_lance.getText();
            if (lanceTexto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Digite um valor para o lance.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double valor = Double.parseDouble(lanceTexto);
            conexao.enviarLance(valor, this.gerenciadorMulticast);  // Envia o lance para o servidor via multicast

            jT_lance.setText("");  // Limpa o campo após o envio

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Digite um valor numérico válido.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            jTextArea1.append("Erro ao enviar o lance: " + e.getMessage() + "\n");
        }
    }

    public void escutar() {
        escutaThread = new Thread(() -> {
            try {
                byte[] buffer = new byte[1024];
                DatagramPacket pacote = new DatagramPacket(buffer, buffer.length);

                // Escuta as mensagens recebidas
                while (true) {
                    this.gerenciadorMulticast.socket.receive(pacote);

                    // Processamento da mensagem recebida
                    String mensagemRecebida = new String(pacote.getData(), 0, pacote.getLength()).trim();

                    try {
                        // Decifra a mensagem recebida
                        String mensagemDecifrada = CriptoUtil.descriptografarMensagemComChaveSimetrica(mensagemRecebida, conexao.chaveSimetrica);

                        // Processa o JSON
                        JSONObject mensagemJson = new JSONObject(mensagemDecifrada);

                        // Verifica se o campo "remetente" existe e é igual a "servidordeLeilao"
                        if (!mensagemJson.has("remetente") || !mensagemJson.getString("remetente").equals("servidordeLeilao")) {

                            continue; // Ignora a mensagem e continua escutando
                        }

                        // Verifica se o campo "tipo" existe
                        if (!mensagemJson.has("tipo")) {
                            continue; // Ignora a mensagem e continua escutando
                        }

                        String tipo = mensagemJson.getString("tipo");

                        switch (tipo) {
                            case "timer" -> {
                                int tempo = mensagemJson.getInt("tempo");

                                SwingUtilities.invokeLater(() -> jT_tempoRestante.setText(String.valueOf(tempo)));
                            }

                            case "lance" -> {
                                String nome = mensagemJson.getString("nome");
                                double valor = mensagemJson.getDouble("valor");
                                int tempo = mensagemJson.optInt("tempo", -1); // Busca o campo "tempo", se existir

                                SwingUtilities.invokeLater(() -> {
                                    jT_maiorlance.setText(String.valueOf(valor));
                                    jT_usuarioMaiorLance.setText(nome);
                                    jTextArea1.append("Novo lance: " + nome + " - R$" + valor + " tempo: " + tempo + "\n");
                                });
                            }

                            case "vencedor" -> {

                                String vencedor = mensagemJson.getString("vencedor");
                                String item = mensagemJson.getString("item");
                                double valor = mensagemJson.getDouble("valor");

                                JOptionPane.showMessageDialog(this, "O leilão para '" + item + "' terminou!\nVencedor: " + vencedor + "\nValor final: R$ " + valor, "Leilão Encerrado", JOptionPane.INFORMATION_MESSAGE);

                                settarLabels();
                            }

                            case "iniciarLeilao" -> {
                                double lanceMinimoEntreLances = mensagemJson.getDouble("intervaloMinimo");
                                double lanceInicial = mensagemJson.getDouble("lanceMinimo");
                                String item = mensagemJson.getString("item");
                                String user = mensagemJson.getString("usuario");
                                System.out.println("Leilão iniciado: " + item);
                                SwingUtilities.invokeLater(() -> {
                                    jTextArea1.append("Leilão iniciado!\nValor mínimo entre lances: R$" + lanceMinimoEntreLances
                                            + "\nItem Leiloado: " + item + "\nValor do Lance Inicial: R$" + lanceInicial + "\n");
                                    jT_usuarioMaiorLance.setText(user);
                                    jT_maiorlance.setText(Double.toString(lanceInicial));
                                });
                                jB_enviarLance.setEnabled(true);
                            }

                            default ->
                                System.out.println("Tipo de mensagem desconhecido: " + tipo);
                        }
                    } catch (Exception e) {
                        System.err.println("Erro ao processar mensagem: " + e.getMessage());
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                System.err.println("Erro ao receber pacote multicast: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Erro ao escutar mensagens: " + e.getMessage());
                e.printStackTrace();
            }
        });
        escutaThread.start();
    }

    private void desconectar() throws IOException {
        conexao.desconectar();
        gerenciadorMulticast.fecharConexao();
        this.escutaThread.stop();
        jl_status.setText("Status: Desconectado.");
        JOptionPane.showMessageDialog(this, "Você foi desconectado do servidor.", "Desconexão", JOptionPane.INFORMATION_MESSAGE);
        jB_enviarLance.setEnabled(false);
        jB_desconectar.setEnabled(false);
        jB_conectar.setEnabled(true); // Reabilita o botão Conectar
    }

    private void settarLabels() {
        this.jT_tempoRestante.setText("");
        this.jT_maiorlance.setText("");
        this.jT_usuarioMaiorLance.setText("");
        jB_enviarLance.setEnabled(false);
        this.jTextArea1.append("\n");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jl_status = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jT_lance = new javax.swing.JTextField();
        jB_enviarLance = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jB_desconectar = new javax.swing.JButton();
        jT_porta = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jB_conectar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jT_Cpf = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jC_chavePrivada = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jT_nome = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jT_maiorlance = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jT_usuarioMaiorLance = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jT_tempoRestante = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jl_status.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jl_status.setText("Status");

        jLabel1.setText("Seu lance: ");

        jB_enviarLance.setText("Enviar Lance");
        jB_enviarLance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_enviarLanceActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setEnabled(false);
        jScrollPane1.setViewportView(jTextArea1);

        jB_desconectar.setText("Desconectar");
        jB_desconectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_desconectarActionPerformed(evt);
            }
        });

        jT_porta.setText("8001");

        jLabel2.setText("Porta do Servidor");

        jB_conectar.setText("Conectar");
        jB_conectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_conectarActionPerformed(evt);
            }
        });

        jLabel3.setText("CPF");

        jT_Cpf.setText("78342858901");
        jT_Cpf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jT_CpfActionPerformed(evt);
            }
        });

        jLabel4.setText("Chave Privada");

        jC_chavePrivada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chave 1", "Chave 2", "Chave 3" }));

        jLabel5.setText("Nome:");

        jT_nome.setPreferredSize(new Dimension(64, 22));
        jT_nome.setText("Lucas");
        jT_nome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jT_nomeActionPerformed(evt);
            }
        });

        jLabel6.setText("Maior Lance: ");

        jT_maiorlance.setEnabled(false);

        jLabel7.setText("Usuário Maior Lance");

        jT_usuarioMaiorLance.setEnabled(false);

        jLabel8.setText("Tempo Restante Leilão: ");

        jT_tempoRestante.setEnabled(false);
        jT_tempoRestante.setPreferredSize(new Dimension(64, 22));

        jLabel9.setText("segundos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jT_lance, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jB_enviarLance))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(18, 18, 18)
                                        .addComponent(jT_tempoRestante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel9)))
                                .addGap(39, 39, 39)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jT_usuarioMaiorLance, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel6)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(234, 234, 234)
                                .addComponent(jB_desconectar)))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(24, 24, 24)
                            .addComponent(jl_status, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jT_porta, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jT_nome, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jC_chavePrivada, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jT_Cpf, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jB_conectar)
                                        .addComponent(jT_maiorlance, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jT_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jT_porta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jT_Cpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jB_conectar)
                    .addComponent(jl_status, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jC_chavePrivada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jT_lance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jB_enviarLance)
                    .addComponent(jLabel6)
                    .addComponent(jT_maiorlance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jT_usuarioMaiorLance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jT_tempoRestante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(40, 40, 40)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jB_desconectar)
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jB_enviarLanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_enviarLanceActionPerformed
        enviarLance();
    }//GEN-LAST:event_jB_enviarLanceActionPerformed

    private void jB_desconectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_desconectarActionPerformed
        try {
            desconectar();
        } catch (IOException ex) {
            Logger.getLogger(InterfaceGraficaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jB_desconectarActionPerformed

    private void jB_conectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_conectarActionPerformed
        conectarAoServidor();
    }//GEN-LAST:event_jB_conectarActionPerformed

    private void jT_CpfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jT_CpfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jT_CpfActionPerformed

    private void jT_nomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jT_nomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jT_nomeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new InterfaceGraficaCliente().setVisible(true));
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jB_conectar;
    private javax.swing.JButton jB_desconectar;
    private javax.swing.JButton jB_enviarLance;
    private javax.swing.JComboBox<String> jC_chavePrivada;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jT_Cpf;
    private javax.swing.JTextField jT_lance;
    private javax.swing.JTextField jT_maiorlance;
    private javax.swing.JTextField jT_nome;
    private javax.swing.JTextField jT_porta;
    private javax.swing.JTextField jT_tempoRestante;
    private javax.swing.JTextField jT_usuarioMaiorLance;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel jl_status;
    // End of variables declaration//GEN-END:variables
}
