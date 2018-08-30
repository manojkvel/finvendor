package com.finvendor.common.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class PasswordUtil {
    public static final String AES = "AES";

    private static byte[] hexStringToByteArray(String s) {
        byte[] b = new byte[s.length() / 2];
        for (int i = 0; i < b.length; i++) {
            int index = i * 2;
            int v = Integer.parseInt(s.substring(index, index + 2), 16);
            b[i] = (byte) v;
        }
        return b;
    }

    public static String generatePassword(String key, String encryptedPassword)throws Exception{
        byte[] bytekey = hexStringToByteArray(key);
        SecretKeySpec sks = new SecretKeySpec(bytekey, AES);
        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.DECRYPT_MODE, sks);
        byte[] decrypted = cipher.doFinal(hexStringToByteArray(encryptedPassword));
        String originalPassword = new String(decrypted);
        return originalPassword;
    }

    public static void main(String[] args) throws Exception {
        String originalPassword = generatePassword("AD9241FBF9F6CD7202984AB49A5E17D6", "97027DF73AC22B915921AE91719338A1");
        System.out.println(originalPassword);
    }
}
