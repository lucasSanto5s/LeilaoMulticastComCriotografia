/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cliente;

/**
 *
 * @author lucas
 */
public  class ClienteLeilao {
    private Cliente cliente;
    private double lanceAtual;

    public ClienteLeilao(Cliente cliente) {
        this.cliente = cliente;
        this.lanceAtual = 0.0;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getLanceAtual() {
        return lanceAtual;
    }

    public void enviarLance(double valor) {
        if (valor <= lanceAtual) {
            System.out.println("Lance inválido! O valor deve ser maior que o lance atual.");
            return;
        }
        this.lanceAtual = valor;
        System.out.println(cliente.getNome() + " enviou um lance de: R$" + valor);
    }
}
