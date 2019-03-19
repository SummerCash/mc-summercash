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
public class Connection {

    // Initialize the connection data
    private String endpoint;
    private HttpURLConnection conn;
    private boolean established;
    private String URLString;

    // Connection - The connection constructor, 
    public Connection(String endpoint) throws IOException {
        this.endpoint = endpoint;
        established = false;
        try {
            conn = getConn();
            established = true;
        }
        
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    // getConn - Return an HttpURLConnection based off of the connection url
    private HttpURLConnection getConn() throws IOException {
        // Create the URL
        URL url = new URL("http://" + Common.PROVIDER + ":" + Common.PORT + "/twirp/" + endpoint);
        URLString = url.toString();
        
        // Create the httpURLConnection
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        // Set the headers and other meta-data
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);
        conn.setDoInput(true);

        // Return the connection
        return conn;
    }

    // Write - Write a string to the established connection (the POST request)
    public void Write(String data) throws IOException {
        // If the connection has been established
        if (established) {
            // Write the data
            DataOutputStream output = new DataOutputStream(conn.getOutputStream());
            output.writeBytes(data);
            output.close();
        }
    }

    // Read - Read from the established connection (the server's response)
    public String Read() throws IOException {
        // If the connection has been initialized
        if (established) {
            // Setup the buffers
            DataInputStream input = new DataInputStream(conn.getInputStream());
            BufferedReader buffer = new BufferedReader(new InputStreamReader(input));            
            
            // Read (and print) some data
            String message = buffer.readLine();
            input.close();
            if (Common.LOGGING) {
                System.out.println("\n" + URLString);
                System.out.println("READ: " + message + "\n\n");
            }
            return message;
        }
        return null;
    }

    // Close - Close the established connection
    public void Close() {
        conn.disconnect();
    }
}