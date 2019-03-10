package com.summercash.mcsummercash.api;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.summercash.mcsummercash.common.Common;

// Connection - This class is just a wrapper for reading and writing from a connection
class Connection {
    String endpoint;
    HttpURLConnection conn;

    public Connection(String endpoint) throws IOException {
        this.endpoint = endpoint;
        conn = getURL();
    }

    private HttpURLConnection getURL() throws IOException {
        URL url = new URL("http://" + Common.PROVIDER + ":8081/twirp/" + endpoint);
        
        // Setup the url connection
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        System.out.println("init url '" + url + "'");
        return conn;
    }

    public void Write(String data) throws IOException {
        DataOutputStream output = new DataOutputStream(conn.getOutputStream());
        output.writeBytes(data);
        output.close();
    }

    public String Read() throws IOException {
        DataInputStream input = new DataInputStream(conn.getInputStream());
        BufferedReader buffer = new BufferedReader(new InputStreamReader(input));

        String message = buffer.readLine();
        input.close();
        return message;
    }

    public void Close() {
        conn.disconnect();
    }
}