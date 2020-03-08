package com.cg.Client;

import beans.reel.ReelBean;
import beans.weight.BasicWeightBean;
import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {

    private static final String BASE_URL = "http://localhost:8008";
    private static final String GET_REQUEST_METHOD = "GET";

    public static String postRequest(String body) throws IOException{
        return makeApiCall("serve", "POST", body);
    }

    public static BasicWeightBean getBasicWeightValue() throws IOException {
        String data = getDataFromServer("table", GET_REQUEST_METHOD);
        return new Gson().fromJson(data, BasicWeightBean.class);
    }

    public static ReelBean getReelResultData() throws IOException {
        String data = getDataFromServer("spin", GET_REQUEST_METHOD);
        return new Gson().fromJson(data, ReelBean.class);
    }

    private static String makeApiCall(String path, String method, String body) throws IOException {
        URL url = new URL(BASE_URL + "/" + path);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoOutput(true);
        con.setRequestMethod(method);
        byte[] outputInBytes = body.getBytes(StandardCharsets.UTF_8);

        OutputStream os = con.getOutputStream();
        os.write( outputInBytes );
        os.close();

        InputStream in = new BufferedInputStream(con.getInputStream());
        String result;
        try (Scanner scanner = new Scanner(in, StandardCharsets.UTF_8.name())) {
            result = scanner.useDelimiter("\\A").next();
        }

        in.close();
        con.disconnect();

        return result;
    }

    private static String getDataFromServer(String path, String requestMethod) throws IOException {
        URL url = new URL(BASE_URL + "/" + path);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoOutput(true);
        con.setRequestMethod(requestMethod);
        con.connect();

        int responseCode = con.getResponseCode();
        try {
            switch (responseCode) {
                case 200:
                    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();
                    return sb.toString();
                default:
                    return "Error occurred, please consult log files";
            }
        }finally {
            con.disconnect();
        }
    }
}
