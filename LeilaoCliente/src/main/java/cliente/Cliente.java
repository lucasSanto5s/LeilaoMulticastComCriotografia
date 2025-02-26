
package cliente;


/**
 * Classe que representa um cliente participante do leilão.
 */
public class Cliente {
    private String nome;
    private String chave;
    private String cpf;

    public Cliente(String nome, String chave, String cpf) {
        this.nome = nome;
        this.chave = chave;
        this.cpf = cpf;
    }
   
    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getChave() {
        return chave;
    }


}
