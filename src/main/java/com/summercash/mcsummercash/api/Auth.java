package com.summercash.mcsummercash.api;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

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
                .post(String.format("https://summer.cash/api/accounts/%s/authenticate", username))
                .header("Content-Type", "application/json")
                .body(String.format("{\"username\": \"%s\", \"password\": \"%s\"", username, password)).asString(); // Do
                                                                                                                    // request

        if (response.getBody().contains("error")) { // Check could not authenticate
            return ""; // Invalid combo
        }

        try {
            JSONObject parsedJSON = (JSONObject) new JSONParser().parse(response.getBody()); // Parse response

            return parsedJSON.getString("address"); // Valid
        } catch (Exception e) {
            e.printStackTrace(); // Log error

            return ""; // Malformed JSON string
        }
    }
}