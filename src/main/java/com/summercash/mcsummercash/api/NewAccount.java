package com.summercash.mcsummercash.api;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;

public class NewAccount {

    private class GeneralRequest {
        private JSONObject genReq;
        GeneralRequest(String privateKey, String address) {
            genReq = new JSONObject();
            genReq.put("address", address);
            genReq.put("privateKey", privateKey);
        }

        String getRequest() {
            return genReq.toString();
        }
    }

    public NewAccount() {

    }

    public String CreateNewAccount() throws Exception {
        URL url = new URL("http://localhost:8081/twirp/accounts.Accounts/NewAccount");
        
        // Setup the url connection
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        System.out.println("init url");

        // Send the req
        DataOutputStream output = new DataOutputStream(conn.getOutputStream());
        GeneralRequest req = new GeneralRequest("", "");
        output.writeBytes(req.getRequest());
        output.close();

        System.out.println("sent POST request");
        
        // Read from connection
        DataInputStream input = new DataInputStream(conn.getInputStream());
        // String message = input.readLine();

        BufferedReader d = new BufferedReader(new InputStreamReader(input));

        String message = d.toString();
        System.out.println(message);
        
        input.close();
        
        System.out.println("Resp Code:" + conn.getResponseCode());
        System.out.println("Resp Message:" + conn.getResponseMessage());

        conn.disconnect();
        return message;
    }
}