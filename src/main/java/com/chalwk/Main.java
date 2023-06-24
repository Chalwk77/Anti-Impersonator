package com.chalwk;

import com.chalwk.util.Builder;

import javax.security.auth.login.LoginException;
import java.io.IOException;

public class Main {

    /**
     * Loads environment variables and builds the bot shard manager:
     *
     * @throws LoginException if the bot token is invalid.
     */
    public Main() throws LoginException, IOException {
        Builder.build();
    }

    /**
     * Main static method:
     *
     * @param args The arguments passed to the program.
     */
    public static void main(String[] args) {
        try {
            new Main();
        } catch (LoginException | IOException e) {
            e.printStackTrace();
        }
    }
}