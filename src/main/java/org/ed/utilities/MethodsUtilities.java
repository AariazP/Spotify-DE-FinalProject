package org.ed.utilities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MethodsUtilities {


    /**
     * This method verifies if the email is valid.
     * @param email The email to verify.
     *              The email must have the following format: " something @ something . something "
     * @return True if the email is valid, false otherwise.
     */

    public static boolean verifyEmail(String email) {
        return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    /**
     * This method verifies if the password is valid.
     * @param text The password to verify.
     * @return True if the password is valid, false otherwise.
     */

    public static boolean verifyPassword(String text) {
        return text.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$");
    }

    /**
     * This method hashes the password.
     * @param password The password to hash.
     * @return The hashed password.
     */
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedPassword = md.digest(password.getBytes());
            return bytesToHex(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method converts a byte array to a hexadecimal string.
     * @param bytes The byte array to convert.
     * @return The hexadecimal string.
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }



}
