package com.example.Foodstore.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha256Util {
    public static String hash(String input) {
        try {
            // 1️⃣ Crear instancia del algoritmo SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // 2️⃣ Convertir el string a bytes y calcular el hash
            byte[] encodedHash = digest.digest(input.getBytes(StandardCharsets.UTF_8));

            // 3️⃣ Convertir el hash (byte[]) a string hexadecimal
            return bytesToHex(encodedHash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al calcular SHA-256", e);
        }
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b); // máscara para byte positivo
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}