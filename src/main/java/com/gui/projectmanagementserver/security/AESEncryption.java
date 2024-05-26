package com.gui.projectmanagementserver.security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESEncryption {
    private static String secret_key = "YourSecretKey123" ;
    public static String encrypt(String str_to_encrypt) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(secret_key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

            byte[] encryptedBytes = cipher.doFinal(str_to_encrypt.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public static String decrypt(String str_to_decrypt) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(secret_key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(str_to_decrypt));
            return new String(decryptedBytes);
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

    public static String encryptIntToString(int number) throws Exception {
        SecretKeySpec key = new SecretKeySpec(secret_key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cipher.doFinal(String.valueOf(number).getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static int decryptStringToInt(String encodedString) throws Exception {
        SecretKeySpec key = new SecretKeySpec(secret_key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decoded = cipher.doFinal(Base64.getDecoder().decode(encodedString));
        return Integer.parseInt(new String(decoded));
    }
}
