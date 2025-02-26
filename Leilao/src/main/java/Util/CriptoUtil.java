/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONObject;

/**
 *
 * @author lucas
 */
public class CriptoUtil {

    public CriptoUtil() {

    }

    // Criptografar a chave simétrica com a chave pública do cliente
    private String criptografarChaveSimetricaComChavePublica(SecretKey chaveSimetrica, PublicKey chavePublica) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, chavePublica);
        byte[] chaveSimetricaCriptografada = cipher.doFinal(chaveSimetrica.getEncoded());
        return Base64.getEncoder().encodeToString(chaveSimetricaCriptografada);
    }

    public static String criptografarMensagemComChaveSimetrica(String mensagem, SecretKey chaveSimetrica) throws Exception {
    Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
    cipher.init(Cipher.ENCRYPT_MODE, chaveSimetrica);
    byte[] mensagemCriptografada = cipher.doFinal(mensagem.getBytes(StandardCharsets.UTF_8));
    return Base64.getEncoder().encodeToString(mensagemCriptografada);
}

    // Método para criar o envelopamento digital com assinatura
    public String criarEnvelopamento(
            String enderecoMulticast,
            int porta,
            SecretKey chaveSimetrica,
            PublicKey chavePublica,
            PrivateKey chavePrivadaServidor) throws Exception {

        // Encriptografar a chave simétrica com a chave pública do cliente
        String chaveSimetricaCriptografada = criptografarChaveSimetricaComChavePublica(chaveSimetrica, chavePublica);

        // Criar a mensagem com os dados de conexão no multicast
        String dadosConexao = String.format("{\"enderecoMulticast\": \"%s\", \"porta\": %d}", enderecoMulticast, porta);

        // Encriptografar a mensagem com a chave simétrica
        String mensagemCriptografada = criptografarMensagemComChaveSimetrica(dadosConexao, chaveSimetrica);

        // Montar o envelopamento com a chave simétrica criptografada e a mensagem criptografada
        JSONObject envelopamento = new JSONObject();
        envelopamento.put("chaveSimetrica", chaveSimetricaCriptografada);
        envelopamento.put("dadosConexao", mensagemCriptografada);

        // Assinar o JSON do envelopamento com a chave privada do servidor
        String mensagemParaAssinar = envelopamento.toString();
        String assinatura = assinarMensagem(mensagemParaAssinar, chavePrivadaServidor);

        // Adicionar a assinatura ao JSON do envelopamento
        envelopamento.put("assinatura", assinatura);

        // Retornar o envelopamento completo em formato JSON
        return envelopamento.toString();
    }

    public static String assinarMensagem(String mensagem, PrivateKey chavePrivada) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(chavePrivada);
        signature.update(mensagem.getBytes(StandardCharsets.UTF_8));

        byte[] assinaturaBytes = signature.sign();
        return Base64.getEncoder().withoutPadding().encodeToString(assinaturaBytes); // Garante formato correto
    }

    public static boolean verificarAssinatura(String assinaturaBase64, String mensagem, PublicKey chavePublica) throws Exception {
        byte[] assinaturaBytes = Base64.getDecoder().decode(assinaturaBase64.trim()); // Remove espaços extras

        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(chavePublica);
        signature.update(mensagem.getBytes(StandardCharsets.UTF_8));

        return signature.verify(assinaturaBytes);
    }

    public static SecretKey decifrarChaveSimetrica(String chaveCriptografada, PrivateKey chavePrivada) throws Exception {
        // Converter a chave criptografada de Base64 para bytes
        byte[] chaveBytesCriptografados = Base64.getDecoder().decode(chaveCriptografada);

        // Configurar o Cipher para decifrar com RSA
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, chavePrivada);

        // Decifrar os bytes e criar uma SecretKey
        byte[] chaveBytes = cipher.doFinal(chaveBytesCriptografados);
        return new SecretKeySpec(chaveBytes, "AES");
    }

  public static String descriptografarMensagemComChaveSimetrica(String mensagemCriptografada, SecretKey chaveSimetrica) throws Exception {
    byte[] mensagemBytesCriptografados = Base64.getDecoder().decode(mensagemCriptografada);
    Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
    cipher.init(Cipher.DECRYPT_MODE, chaveSimetrica);
    byte[] mensagemBytes = cipher.doFinal(mensagemBytesCriptografados);
    return new String(mensagemBytes, StandardCharsets.UTF_8);
}

}
