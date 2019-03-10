package com.summercash.mcsummercash.api;

import java.io.IOException;

import org.json.simple.JSONObject;

// NewTransaction - Create a new transaction on the SummerCash network
public class NewTransaction {

    // GeneralRequest - A JSON object for go-summercash's protobuf RPC server
    private class GeneralRequest {
        // The request itself
        private JSONObject request;

        // GeneralRequest - Create the request
        @SuppressWarnings("unchecked") // The warnings really don't mean anything
        GeneralRequest(String sender, String recipient, double amount) {
            request = new JSONObject();
            request.put("nonce", 0);
            request.put("address", sender);
            request.put("address2", recipient);
            request.put("amount", amount);
            request.put("payload", "test payload");
        }
        
        // GetRequest - Return the JSON object as a string
        public String GetRequest() {
            System.out.println(request.toString());
            return request.toString();
        }
    }

    // CreateNewTransaction - Call the RPC server's NewTransaction() method and
    // return the server's response
    public String CreateNewTransaction(String sender, String recipient, double amount) throws IOException {
        // Create a connection
        Connection connection = new Connection("transaction.Transaction/NewTransaction");

        // Send the req
        GeneralRequest req = new GeneralRequest(sender, recipient, amount);
        connection.Write(req.GetRequest());
        
        // Read from connection
        String message = connection.Read();
        System.out.println(message);

        // Close the connection
        connection.Close();

        return message;
    }
}