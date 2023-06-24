package com.chalwk.util;

import org.json.JSONObject;

import java.io.*;

public class FileIO {

    static String programPath = getProgramPath();

    public static void checkFile(File file) {
        if (!file.exists()) {
            try {

                file.createNewFile();
                JSONObject obj = new JSONObject();
                obj.put("example_name", "example_id");
                obj.put("another_example_name", "another_example_id");

                try (FileWriter f = new FileWriter(file)) {
                    f.write(obj.toString(4));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getProgramPath() {
        String currentDir = System.getProperty("user.dir");
        currentDir = currentDir.replace("\\", "/");
        return currentDir;
    }

    public static JSONObject getJSONObject(String fileName) throws IOException {
        String content = readFile(fileName);
        if (content.equals("")) {
            return new JSONObject();
        } else {
            return new JSONObject(content);
        }
    }

    public static String readFile(String file) throws IOException {

        File f = new File(programPath + "/" + file);
        checkFile(f);

        BufferedReader reader = new BufferedReader(new FileReader(f));
        String line = reader.readLine();
        StringBuilder stringBuilder = new StringBuilder();

        while (line != null) {
            stringBuilder.append(line);
            line = reader.readLine();
        }

        reader.close();
        return stringBuilder.toString();
    }
}
