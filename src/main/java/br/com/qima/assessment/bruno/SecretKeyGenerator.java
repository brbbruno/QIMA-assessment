package br.com.qima.assessment.bruno;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class SecretKeyGenerator {

  public static void main(String[] args) throws Exception {
    KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
    keyGen.init(256); // Tamanho da chave
    SecretKey secretKey = keyGen.generateKey();
    String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
    System.out.println("Generated Secret Key: " + encodedKey);
  }
}
