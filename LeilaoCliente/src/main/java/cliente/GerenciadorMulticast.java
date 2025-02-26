package cliente;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.DatagramPacket;
import java.net.NetworkInterface;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class GerenciadorMulticast {

    public MulticastSocket socket;
    private InetAddress grupo;
    private int porta;
    private String enderecoMulticast;

    public GerenciadorMulticast(String enderecoMulticast, int porta) {
        this.enderecoMulticast = enderecoMulticast;
        this.porta = porta;

        try {
            // Cria o socket multicast
            socket = new MulticastSocket(this.porta);

            // Cria o grupo multicast com o endereço fornecido
            grupo = InetAddress.getByName(this.enderecoMulticast);

            // Junta-se ao grupo multicast
            socket.joinGroup(grupo);

            System.out.println(" Conectado ao grupo multicast: " + enderecoMulticast + ":" + porta);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enviarMensagem(String mensagem) {
        try {
            // Enviar a mensagem para o grupo multicast

            DatagramPacket pacote = new DatagramPacket(mensagem.getBytes(), mensagem.length(), grupo, porta);
            //  socket.setTimeToLive(5);
            socket.send(pacote);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao enviar mensagem multicast.");
        }
    }

    // Método para fechar a conexão com o grupo multicast
    public void fecharConexao() throws UnknownHostException, IOException {
        if (socket != null) {
            try {
                InetAddress inetAddress = InetAddress.getByName(enderecoMulticast);
                this.socket.leaveGroup(inetAddress);
            } catch (SocketException e) {
                // Tratar a exceção, talvez logar o erro
                System.err.println("Erro ao sair do grupo multicast: " + e.getMessage());
            }
        }
    }

    public String getEnderecoMulticast() {
        return enderecoMulticast;
    }

    public int getPorta() {
        return porta;
    }
}
