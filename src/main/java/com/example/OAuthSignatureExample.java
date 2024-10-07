package com.example;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class OAuthSignatureExample {
    public static String generateHMACSHA1Signature(String baseString, String keyString) throws Exception {
        // Algoritmo HMAC-SHA1
        String algorithm = "HmacSHA1";
        // Creazione di una chiave speciale per l'algoritmo HMAC
        SecretKeySpec key = new SecretKeySpec((keyString).getBytes(StandardCharsets.UTF_8), algorithm);
        // Creazione di un'istanza di Mac
        Mac mac = Mac.getInstance(algorithm);
        mac.init(key);
        // Calcolo del HMAC
        byte[] signatureBytes = mac.doFinal(baseString.getBytes(StandardCharsets.UTF_8));
        // Codifica della firma in base64
        String signature = Base64.getEncoder().encodeToString(signatureBytes);
        return signature;
    }

    public static void main(String[] args) {
        try {
            String baseString = "your_base_string_here";
            String consumerSecret = "your_consumer_secret_here";
            // L'OAuth Consumer Secret è usato qui come parte della chiave
            String signature = generateHMACSHA1Signature(baseString, consumerSecret + "&");
            System.out.println("Generated HMAC-SHA1 Signature: " + signature);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
