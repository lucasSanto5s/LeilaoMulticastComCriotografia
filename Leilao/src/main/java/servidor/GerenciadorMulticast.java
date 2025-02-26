package servidor;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.DatagramPacket;
import java.net.NetworkInterface;
import java.net.InetSocketAddress;

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
          
        DatagramPacket pacote = new DatagramPacket(mensagem.getBytes(), mensagem.length(), grupo,  porta);
      //  socket.setTimeToLive(5);
        socket.send(pacote);

    } catch (IOException e) {
        e.printStackTrace();
        System.out.println("Erro ao enviar mensagem multicast.");
    }
}




    // Método para fechar a conexão com o grupo multicast
    public void fecharConexao() {
        try {
            // Obtém a interface de rede padrão
            NetworkInterface networkInterface = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());

            // Sai do grupo multicast
            socket.leaveGroup(new InetSocketAddress(grupo, porta), networkInterface);
            socket.close();
            System.out.println("Conexão com o grupo multicast fechada.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getEnderecoMulticast() {
        return enderecoMulticast;
    }

    public int getPorta() {
        return porta;
    }
}
