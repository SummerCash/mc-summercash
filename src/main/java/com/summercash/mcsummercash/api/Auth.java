package com.summercash.mcsummercash.api;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

// Auth - Check that a given password, username combination is indeed valid.
public class Auth {
    // Authenticate - Check that a given password, username combination is indeed
    // valid.
    // Will return the address corresponding to a username if authenticated
    // successfully (empty string if invalid).
    public String Authenticate(String username, String password) {
        final HttpResponse<String> response = Unirest
                .post(String.format("https://summer.cash/api/accounts/%s/getPrivatekey", username))
                .header("Content-Type", "application/json")
                .body(String.format("{\"username\": \"%s\", \"password\": \"%s\"", username, password)).asString(); // Do
                                                                                                                    // request

        if (response.getBody().contains("error")) { // Check could not authenticate
            return ""; // Invalid combo
        }

        try {
            Connection connection = new Connection("accounts.Accounts/AccountFromKey"); // Create a connection

            AccountsGeneralRequest req = new AccountsGeneralRequest("", response.getBody()); // Send the req
            connection.Write(req.GetRequest());

            // Read from connection
            String message = connection.Read();

            // Close the connection
            connection.Close();

            return message.replace("\n", ""); // Valid
        } catch (Exception e) {
            e.printStackTrace(); // Log error

            return ""; // Malformed JSON string
        }
    }
}