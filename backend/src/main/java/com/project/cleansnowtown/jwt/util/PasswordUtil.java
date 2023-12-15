package com.project.cleansnowtown.jwt.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

import java.security.SecureRandom;
import java.util.Base64;

public class PasswordUtil {

    private static final String CHAR_SET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@#$%^&*()-_=+[]{}|;:'\",.<>/?";
    private static final int PASSWORD_LENGTH = 8;

    private static final int SALT_LENGTH = 16;

    public static boolean equals(String plaintext, String hashed) {
        if (plaintext == null || plaintext.length() < 1) {
            return false;
        }

        if (hashed == null || hashed.length() < 1) {
            return false;
        }

        return BCrypt.checkpw(plaintext, hashed);
    }

    public static String encPassword(String plaintext) {
        if (plaintext == null || plaintext.length() < 1) {
            return "";
        }

        byte[] saltBytes = generateRandomSalt();
        String salt = Base64.getEncoder().encodeToString(saltBytes);

        return BCrypt.hashpw(plaintext, BCrypt.gensalt(salt, 12));
    }

    public static String generateRandomPassword() {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int index = secureRandom.nextInt(CHAR_SET.length());
            password.append(CHAR_SET.charAt(index));
        }

        return password.toString();
    }

    private static byte[] generateRandomSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return salt;
    }
}
