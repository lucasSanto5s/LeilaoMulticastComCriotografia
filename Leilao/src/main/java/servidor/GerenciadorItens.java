
package servidor;

import java.util.List;

/**
 *
 * @author lucas
 */
public class GerenciadorItens {
    private  List<ItemLeilao> itens;

    public GerenciadorItens() {
        this.itens = new java.util.LinkedList<>();
    }

    // Adiciona um item à lista
    public void adicionarItem(String nome, String descricao) {
        itens.add(new ItemLeilao(nome, descricao));
      
    }

    public List<ItemLeilao> getItens() {
        return itens;
    }
    
    
    public ItemLeilao obterItemPorNome(String nome) {
        for (ItemLeilao item : itens) {
            if (item.getNome().equalsIgnoreCase(nome)) {
                return item;
            }
        }
        return null; // Retorna null se o item não for encontrado
    }

    // Verifica se ainda há itens na lista
    public boolean temItens() {
        return !itens.isEmpty();
    }
}

// Classe que representa um item do leilão
class ItemLeilao {
    private String nome;
    private String descricao;

    public ItemLeilao(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }
    public String getNomeeDescricao() {
        return nome+descricao;
    }
    public String getDescricao() {
        return descricao;
    }
}