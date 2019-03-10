package com.summercash.mcsummercash.api;

import org.json.simple.JSONObject;

// NewAccount - Create a new account on the Summercash network
public class NewAccount {

    // GeneralRequest - A JSON object for go-summercash's protobuf RPC server
    private class GeneralRequest {
        // The request itself
        private JSONObject request;

        // GeneralRequest - Create the request
        @SuppressWarnings("unchecked") // The warnings really don't mean anything
        GeneralRequest(String address, String privateKey) {
            request = new JSONObject();
            request.put("address", address);
            request.put("privateKey", privateKey);
        }

        // GetRequest - Return the JSON object as a string
        public String GetRequest() {
            return request.toString();
        }
    }

    // CreateNewAccount - Call the RPC server's NewAccount() method and return the server's response
    public String CreateNewAccount() throws Exception {
        // Create a connection
        Connection connection = new Connection("accounts.Accounts/NewAccount");

        // Send the req
        GeneralRequest req = new GeneralRequest("", "");
        connection.Write(req.GetRequest());
        
        // Read from connection
        String message = connection.Read();
        System.out.println(message);

        // Close the connection
        connection.Close();

        return message;
    }
}