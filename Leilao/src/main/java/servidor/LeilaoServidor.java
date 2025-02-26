package servidor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class LeilaoServidor {

    // Gerenciadores
    private GerenciadorItens gerenciadorItens;
    private GerenciadorClientesLeilao gerenciadorClientes;

    public LeilaoServidor(GerenciadorLances gerenciadorLances, RelogioLeilao relogio, GerenciadorMulticast gerenciadorMulti) {
        // Inicializa os gerenciadores

        this.gerenciadorItens = new GerenciadorItens();

        this.gerenciadorClientes = new GerenciadorClientesLeilao(gerenciadorLances, relogio, gerenciadorMulti);

        // Carrega os itens do arquivo
        try {

            carregarItens("src/main/java/Util/itens.txt"); // Verifique o caminho do arquivo
            System.out.println("Itens carregados com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao carregar os itens do arquivo: " + e.getMessage());
        }

        new Thread(() -> gerenciadorClientes.iniciarConexao(8001)).start();

    }

    private void carregarItens(String caminhoArquivo) throws IOException {

        Path caminho = Paths.get(caminhoArquivo);
        if (!Files.exists(caminho)) {
            throw new IOException("Arquivo de itens não encontrado: " + caminhoArquivo);
        }

        List<String> linhas = Files.readAllLines(caminho);

        for (String linha : linhas) {

            String[] partes = linha.split(",");
            if (partes.length == 2) {
                String nome = partes[0].trim();
                String descricao = partes[1].trim();

                gerenciadorItens.adicionarItem(nome, descricao);
            } else {
                System.err.println("Linha inválida no arquivo de itens: " + linha);
            }
        }
    }

    // Getters para os gerenciadores
    public GerenciadorItens getGerenciadorItens() {
        return gerenciadorItens;
    }

    public GerenciadorClientesLeilao getGerenciadorClientes() {
        return gerenciadorClientes;
    }
}
