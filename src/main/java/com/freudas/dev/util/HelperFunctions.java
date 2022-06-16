package com.freudas.dev.util;

import java.util.Random;

public class HelperFunctions {
    public static String generateRandomString(int len) {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                +"lmnopqrstuvwxyz!@#$%&";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        return sb.toString();
    }
}
