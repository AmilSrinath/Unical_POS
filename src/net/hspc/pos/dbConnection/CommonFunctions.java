/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hspc.pos.dbConnection;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Sanjuka
 */
public class CommonFunctions {
    public static String getMD5Hashe(String original) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(original.getBytes());
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }
        //System.out.println("original:" + original);
        //System.out.println("digested(hex):" + sb.toString());
        return sb.toString();
    }

    public static String decodePW(String pwString) {
        String pw = "";
        char[] charArray = pwString.toCharArray();

        for (int i = charArray.length; 0 <= i - 1; i--) {
            char c = charArray[i - 1];
            pw += c;
        }
        // remove first 3 characters
        if (pw.length() > 0) {
            pw = pw.substring(0, pw.length() - 3);
        }
        return pw;
    }
}
