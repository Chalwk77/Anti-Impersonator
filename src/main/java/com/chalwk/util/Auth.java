package com.chalwk.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Auth {

    /**
     * This method is used to get the bot token from the auth.token file.
     *
     * @return the bot token.
     * @throws IOException if the file is not found.
     */
    public static String getToken() throws IOException {
        BufferedReader text = new BufferedReader(new FileReader("auth.token"));
        return text.readLine();
    }
}
