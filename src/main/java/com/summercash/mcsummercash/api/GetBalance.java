package com.summercash.mcsummercash.api;

import java.io.IOException;

import org.json.simple.JSONObject;

public class GetBalance {

    // GeneralRequest - A JSON object for go-summercash's protobuf RPC server
    private class GeneralRequest {
        // The request itself
        private JSONObject request;

        // GeneralRequest - Create the request
        @SuppressWarnings("unchecked") // The warnings really don't mean anything
        GeneralRequest(String address) {
            request = new JSONObject();
            request.put("address", address);
        }

        // GetRequest - Return the JSON object as a string
        public String GetRequest() {
            System.out.println(request.toString());
            return request.toString();
        }
    }

    // GetAccountBalance - Return the balance of an account
    public String GetAccountBalance(String account) throws IOException {
        // Create a connection
        Connection connection = new Connection("chain.Chain/GetBalance");
        
        // Send the request
        GeneralRequest req = new GeneralRequest(account);
        connection.Write(req.GetRequest());

        // Read from the connection
        String message = connection.Read();
        System.out.println(message);

        // Close the connection
        connection.Close();

        return message;
    }

}