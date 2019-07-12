package com.summercash.mcsummercash.api;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

// Auth - Check that a given password, username combination is indeed valid.
public class Auth {
    // Authenticate - Check that a given password, username combination is indeed
    // valid.
    // Will return the address corresponding to a username if authenticated
    // successfully (empty string if invalid).
    public String Authenticate(String username, String password) {
        try {
            final byte[] encodedParams = String
                    .format("{\"username\": \"%s\", \"password\": \"%s\"}", username, password).getBytes("utf-8"); // Encode
                                                                                                                   // to
                                                                                                                   // UTF-8

            URL url = new URL(String.format("https://summer.cash/api/accounts/%s/getPrivatekey", username)); // Set URL

            HttpURLConnection con = (HttpURLConnection) url.openConnection(); // Open conn
            con.setRequestMethod("POST"); // Set type to post req
            con.setRequestProperty("Content-Type", "application/json"); // Set content type to JSON
            con.setRequestProperty("Accept", "application/json"); // Accept a JSON-formatted output
            con.setRequestProperty("User-Agent", "Mozilla/5.0"); // Set content type to JSON
            con.setDoOutput(true); // Lol Java sucks

            DataOutputStream out = new DataOutputStream(con.getOutputStream()); // Initialize output stream
            out.write(encodedParams, 0, encodedParams.length); // Write
            // params
            out.flush(); // Flush
            out.close(); // Close writer

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8")); // Init
                                                                                                          // response
                                                                                                          // reader

            String inputLine; // Init current line buffer
            StringBuffer response = new StringBuffer(); // Init response buffer

            while ((inputLine = in.readLine()) != null) { // Iterate through lines
                response.append(inputLine); // Append input line
            }

            in.close(); // Close input

            con.disconnect(); // Close connection

            if (response.toString().contains("error")) { // Check invalid
                return ""; // Don't display help again
            }

            Connection connection = new Connection("accounts.Accounts/AccountFromKey"); // Create a connection
            connection.Write(String.format("{\"privateKey\": \"0x%s\"}", response.toString())); // Write request

            // Read from connection
            String message = connection.Read().split("\":\"\n")[1].split("\"}")[0]; // Parse

            // Close the connection
            connection.Close();

            return message; // Valid
        } catch (Exception e) {
            e.printStackTrace(); // Log error

            return ""; // Exception
        }
    }
}