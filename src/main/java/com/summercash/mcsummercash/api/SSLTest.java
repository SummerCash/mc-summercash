package com.summercash.mcsummercash.api;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.URL;
import java.security.SecureRandom;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;

import org.json.simple.JSONObject;

public class SSLTest {

    public class GeneralRequest {
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

    public void Test() throws Exception {
        // Configure the SSLContext with a TrustManager
        SSLContext ctx = SSLContext.getInstance("TLS");
        ctx.init(new KeyManager[0], new TrustManager[] {new DefaultTrustManager()}, new SecureRandom());
        SSLContext.setDefault(ctx);

        // URL url = new URL("https://mms.nw.ru");
        URL url = new URL("https://localhost:8080/twirp/accounts.Accounts/NewAccount");
        // {"address": "", "privateKey": ""} // Takes some JSON like this
        
        // Setup the url connection
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);
        conn.setDoInput(true);

        // Setup ignoring SSL self-signed errors
        conn.setHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String arg0, SSLSession arg1) {
                return true;
            }
        });
        System.out.println("working");

        // Send the req
        DataOutputStream output = new DataOutputStream(conn.getOutputStream());
        GeneralRequest req = new GeneralRequest("", "");
        output.writeBytes(req.getRequest());
        output.close();

        System.out.println("sent data");
        
        // Read from conn
        DataInputStream input = new DataInputStream(conn.getInputStream());
        System.out.println(input.readLine());
        // int c;
        // for (c = input.read(); c != -1; c = input.read());
        // System.out.print((char)c);
        
        input.close();
        
        // System.out.println(conn.getResponseCode());

        System.out.println("Resp Code:" + conn.getResponseCode());
        System.out.println("Resp Message:" + conn.getResponseMessage());

        conn.disconnect();
    }
}