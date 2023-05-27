package org.ed.utilities;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Objects;

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

    /**
     * This method saves the user in txt file.
     * @param user The user to save.
     * @param password The password of the user.
     */
    public static void saveUser(String user, String password, String file) {
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(user + " " + hashPassword(password));
            bw.close();
            System.out.println("El archivo se ha escrito correctamente.");
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error al escribir en el archivo: " + e.getMessage());
        }


    }

    public static String getUserLogged(String file) {
        return Objects.requireNonNull(loadUserLogged(file)).split(" ")[0];
    }

    public static String getPasswordLogged(String file) {
        return Objects.requireNonNull(loadUserLogged(file)).split(" ")[1];
    }


    public static String loadUserLogged(String file) {
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            if(line != null && !line.equals("")){
                return line;
            }
            return "a a";
        }catch (IOException e) {
            System.out.println("Ha ocurrido un error al leer el archivo: " + e.getMessage());
        }
        return "a a";
    }


    public static ArrayList<String> getOptions() {
        ArrayList<String> options = new ArrayList<>();
        options.add("Cuenta");
        options.add("Sesión privada");
        options.add("Preferencias");
        options.add("Perfil");
        options.add("Acerca de");
        options.add( "Cerrar sesión");
        return options;
    }

    public static ArrayList<String> getSearchOptions() {
        ArrayList<String> options = new ArrayList<>();
        options.add("Artista");
        options.add("Coincidencia parcial");
        options.add("Coincidencia total");
        return options;
    }

    /**
     * This method converts seconds to minutes and seconds.
     * @param seconds The seconds to convert.
     * @return The minutes and seconds.
     */
    public static String convertToMinutesSeconds(double seconds) {
        int minutes = (int) (seconds / 60);
        int secondsInt = (int) (seconds % 60);
        if(secondsInt < 10) return minutes + ":0" + secondsInt;
        return minutes + ":" + secondsInt;
    }
}
