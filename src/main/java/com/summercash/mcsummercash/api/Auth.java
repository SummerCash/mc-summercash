package com.summercash.mcsummercash.api;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.json.simple.JSONObject;

// Auth - Check that a given password, username combination is indeed valid.
public class Auth {
    // Authenticate - Check that a given password, username combination is indeed
    // valid.
    // Will return the address corresponding to a username if authenticated
    // successfully (empty string if invalid).
    public String Authenticate(String username, String password) {
        try {
            URL url = new URL(String.format("https://summer.cash/api/accounts/%s/getPrivatekey", username)); // Set URL

            HttpURLConnection con = (HttpURLConnection) url.openConnection(); // Open conn
            con.setRequestMethod("POST"); // Set type to post req
            con.setRequestProperty("Content-Type", "application/json"); // Set content type to JSON
            con.setDoOutput(true); // Lol Java sucks

            HashMap<String, String> rawParameters = new HashMap<String, String>(); // Initialize raw params buffer
            rawParameters.put("username", username); // Set username
            rawParameters.put("password", password); // Set password

            JSONObject parameters = new JSONObject(rawParameters);// Initialize params buffer

            DataOutputStream out = new DataOutputStream(con.getOutputStream()); // Initialize output stream
            out.writeChars(parameters.toString()); // Write params
            out.flush(); // Flush
            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream())); // Init response reader

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

            AccountsGeneralRequest req = new AccountsGeneralRequest("", response.toString()); // Send the req
            connection.Write(req.GetRequest());

            // Read from connection
            String message = connection.Read();

            // Close the connection
            connection.Close();

            return message.replace("\n", ""); // Valid
        } catch (Exception e) {
            e.printStackTrace(); // Log error

            return ""; // Exception
        }
    }
}