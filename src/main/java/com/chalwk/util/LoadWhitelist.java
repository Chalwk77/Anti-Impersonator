package com.chalwk.util;

import org.json.JSONObject;

import java.io.IOException;

import static com.chalwk.util.FileIO.getJSONObject;

public class LoadWhitelist {

    private static final JSONObject whitelist;

    /**
     * Gets the whitelist JSON object.
     * @return The whitelist JSON object.
     */
    public static JSONObject getWhitelist(){
        return whitelist;
    }

    static {
        try {
            whitelist = getJSONObject("whitelist.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
