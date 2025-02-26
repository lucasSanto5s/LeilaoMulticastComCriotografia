///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package Util;
//
//import java.security.KeyPair;
//import java.security.KeyPairGenerator;
//import java.security.PrivateKey;
//import java.security.PublicKey;
//import java.util.Base64;
//
//public class GeradorChave {
//
//    private PublicKey chavePublica;
//    private PrivateKey chavePrivada;
//
//    // Construtor para gerar o par de chaves
//    public GeradorChave() throws Exception {
//        // Gerador de par de chaves RSA
//        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
//        keyPairGenerator.initialize(2048); // Tamanho das chaves (2048 bits é um valor recomendado)
//        KeyPair keyPair = keyPairGenerator.generateKeyPair();
//
//        // Armazena as chaves no objeto
//        this.chavePublica = keyPair.getPublic();
//        this.chavePrivada = keyPair.getPrivate();
//    }
//
//    // Método para obter a chave pública
//    public PublicKey getChavePublica() {
//        return chavePublica;
//    }
//
//    // Método para obter a chave privada
//    public PrivateKey getChavePrivada() {
//        return chavePrivada;
//    }
//
//    // Método para obter a chave pública em Base64 (para envio ou armazenamento)
//    public String getChavePublicaBase64() {
//        return Base64.getEncoder().encodeToString(chavePublica.getEncoded());
//    }
//
//    // Método para obter a chave privada em Base64 (para envio ou armazenamento seguro)
//    public String getChavePrivadaBase64() {
//        return Base64.getEncoder().encodeToString(chavePrivada.getEncoded());
//    }
//
//    // Método principal para testar
//    public static void main(String[] args) {
//        try {
//            // Gerar par de chaves
//            GeradorChave gerador = new GeradorChave();
//
//            // Imprimir as chaves em Base64
//            System.out.println("Chave Pública (Base64):");
//            System.out.println(gerador.getChavePublicaBase64());
//
//            System.out.println("\nChave Privada (Base64):");
//            System.out.println(gerador.getChavePrivadaBase64());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
