/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

/**
 *
 * @author lucas
 */

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

public class GerenciadorChavesPubPriv {

    // Map para armazenar as chaves públicas associadas aos CPFs
    private Map<String, String> chavesPublicasClientes;
    private String chavePublicaServidor;
    
    public GerenciadorChavesPubPriv() {
        chavePublicaServidor = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsM/vnm79ksY770mQRPbP6n43TpkTZ3PqRZb2dpRcPhytZWZcPW18fzdLg9SnjOet9z2yiwnQCFaBvimEwEteEwiW7A0oabGs5SgXD0d47YvJcgSIv4Giue6szazvFQNoPNlv00R33AD9/juTySinh2itRNJ16laDT9zZu4uUv82X+OY0lSNOfZ4sQEjY6NmcnIrDIRCL9lyw+JKAdpDeAI/nrjC81TFI2SO4/qGRUiUA+6lRqGlIbJ9xDqPSJUhN1h4obmy10vfKCUpGFEiz1PPdHssP9O1mCqZo31g5ZvA2howaAtVUGS7WkPIVUIwQE+o1PcRo6H+GLKKMiGErewIDAQAB" ;
        // Inicializa o Map para armazenar as chaves
        chavesPublicasClientes = new HashMap<>();
        adicionarChaveCliente("78342858901", "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA6jE118BfhNsFDIIxT/we5dLJj5HGfmTqWp7VVHNziBFzRPAjEUQHjqKXg+bG63Ax6VxVnLCCEH/UlPqNLgdwiJXQVnqiexEtXBuJAm6Cnhg4ACuRQOcHD5bkg8wjQE5YZQfjrI/Azb6WU6cr3jsjg/+54RYAVjQ8GMZF0AY+6XY2UuY9wYibLv7LB1knbt3nW8kXj7CgPg0KtzkXIOCbu6OoVMjehHm8jXRIOr5c3p4OY/jBfDbjLlXTgcd60fP6ih5y2I9YQdFH8Lw/dEdVYM+5ChfrgBsdU1Vt7WxQ1tWW6e2lPGZpMoSoWp8I4n1EeHD+CsglKtjyqqcAGv5CdQIDAQAB");
        adicionarChaveCliente("21337720496", "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAox24v5CwgtK7LN5ceTOI20SWBKPjjaVRcsMJDrKF38ZSC5NUP9ujhJH4TRyk+7Bx1EUjKMT2ZIUYVHyCRregP0WM49OTIj6MgLEphAzIcwX5MZblkVjUZGG+8rp+w75Scry+QupP96dmMJeDakVsJ+xaeZQho7TykjGYsGt01ra4dmeGIFu770aNUAVvHOp7hQQzI4LGu+8Bixsyd1u1K8pzaIO43gIZHTsctVY9Cstxg0izPQ2j6D34JrI/9PYrZxE0Q7wFhfDJh3/1VMN4Kh3PcI4yRC3eoQXik9zot7EsV96DS+vY+kJTp1VvzeWQ5Lp+ifzx/JzgU06TOCk3CQIDAQAB");
        adicionarChaveCliente("57400126865", "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApiH2sT8f7eN3VENSUSaYJjebUJY6ZiNQ+SuDkSzdQv0prYyP4Rs72PQ+MnX/hTUB3+yRqeyWT7TQ0m5AhwKOJg8eatgcEkR0IaaGXh3yf1xZm63rYPUyZUJL7cwJDF2j/dWyA/grda91gta9Ur51cRaRHOtHloXWV+IBrSWXRIiCxV0xHaILQDEmIXMERdIJ8m5ZCYMjHn/HqkC8MqZNbiT+y5FHwKUhkOP7cVKNbR0lhgtn/v5pxk7PyXhv3SU9V0ArIuziykphZ77mPvaNuN51Dw0I1yx7fQMx6W5eM0g9Oxf74nK/TJsbNGl9HpUzygVF90Cpa2uDFSVU5ZabWwIDAQAB");
    }

    // Método para adicionar um CPF e sua chave pública associada
    public void adicionarChaveCliente(String cpf, String chavePublica) {
        chavesPublicasClientes.put(cpf, chavePublica);
    }

    // Método para buscar a chave pública de um CPF
public String buscarChavePublicaCliente(String cpf) {
    String chavePublica = chavesPublicasClientes.get(cpf);
    
    if (chavePublica == null) {
        JOptionPane.showMessageDialog(null, "Erro: CPF não encontrado ou chave pública não correspondente!", 
                                      "Erro de Autenticação", JOptionPane.ERROR_MESSAGE);
        return null;
    }

    return chavePublica;
}


    public String getChavePublicaServidor() {
        return chavePublicaServidor;
    }

    // Método para verificar se um CPF possui chave pública registrada
    public boolean possuiChaveCliente(String cpf) {
        return chavesPublicasClientes.containsKey(cpf);
    }

    // Método para exibir todas as chaves armazenadas (para debug ou inspeção)
    public void exibirChavesClientes() {
        for (Map.Entry<String, String> entry : chavesPublicasClientes.entrySet()) {
            System.out.println("CPF: " + entry.getKey() + " | Chave Pública: " + entry.getValue());
        }
    }
    
    public PublicKey stringParaChavePublica(String chavePublicaBase64) throws Exception {
    byte[] chaveBytes = Base64.getDecoder().decode(chavePublicaBase64);
    X509EncodedKeySpec spec = new X509EncodedKeySpec(chaveBytes);
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    return keyFactory.generatePublic(spec);
}
    
    public PrivateKey stringParaChavePrivada(String chavePrivadaBase64) throws Exception {
    // Decodifica a chave privada de Base64
    byte[] chaveBytes = Base64.getDecoder().decode(chavePrivadaBase64);
    
    // Cria a especificação para a chave privada usando o formato PKCS8
    PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(chaveBytes);
    
    // Obtém a chave privada usando o KeyFactory para RSA
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    return keyFactory.generatePrivate(spec);
}

}
