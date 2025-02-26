/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor;

/**
 *
 * @author lucas
 */
// Classe GerenciadorLances para gerenciar os lances no leilão
public class GerenciadorLances {

    private double lanceAtual;
    private String usuarioAtual;
    private double lanceMin;
    private double intervaloMin;
    private ItemLeilao item;

    public GerenciadorLances(double lanceMin, double intervaloMin) {
        this.lanceAtual = 0.0;
        this.usuarioAtual = "Ninguém";
        this.lanceMin = lanceMin;
        this.intervaloMin = intervaloMin;
    }

    public boolean registrarLance(String nome, double valor) {
        // Verifica se o lance recebido é maior que o atual
        if (valor >= lanceMin && (valor - lanceAtual) >= intervaloMin) {
            lanceAtual = valor;
            usuarioAtual = nome;
            return true;
        }
        return false;
    }

    public ItemLeilao getItem() {
        return item;
    }

    public void setItem(ItemLeilao item) {
        this.item = item;
    }

    public double getIntervaloMin() {
        return intervaloMin;
    }

    public double getLanceAtual() {
        return lanceAtual;
    }

    public String getUsuarioAtual() {
        return usuarioAtual;
    }

    public void setLanceMin(double lanceMin) {
        this.lanceMin = lanceMin;
    }

    public void setIntervaloMin(double intervaloMin) {
        this.intervaloMin = intervaloMin;
    }

    public void zerarLances() {
        this.lanceAtual = 0.0; // Zera o lance atual
        this.usuarioAtual = "Ninguém"; // Remove o usuário que deu o último lance
        this.lanceMin = 0.0; // Zera o lance mínimo (se necessário)
        this.intervaloMin = 0.0; // Zera o intervalo mínimo (se necessário)
        this.item= null;

    }

}
