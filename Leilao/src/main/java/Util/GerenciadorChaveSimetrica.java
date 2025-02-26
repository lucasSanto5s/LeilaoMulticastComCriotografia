/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

/**
 *
 * @author lucas
 */

import java.security.spec.KeySpec;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;



public class GerenciadorChaveSimetrica {
    private static final String CHAVE_FIXA = "MinhaChaveSecretaSimetrica"; // Seed fixa
    private static final String SALT = "12345678"; // Salt fixo (deve ter pelo menos 8 bytes)
    
    public static SecretKey obterChaveSimetrica() throws Exception {
        byte[] saltBytes = SALT.getBytes("UTF-8");

        // Configurações do PBKDF2 para derivar a chave
        KeySpec spec = new PBEKeySpec(CHAVE_FIXA.toCharArray(), saltBytes, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] chaveBytes = factory.generateSecret(spec).getEncoded();

        // Converte para AES
        SecretKey chave = new SecretKeySpec(chaveBytes, "AES");

    
        
        return chave;
    }
}

