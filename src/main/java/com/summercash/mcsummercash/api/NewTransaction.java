package com.summercash.mcsummercash.api;

import org.json.simple.JSONObject;

// NewTransaction - Create a new transaction on the SummerCash network
class NewTransaction {

    // GeneralRequest - A JSON object for go-summercash's protobuf RPC server
    private class GeneralRequest {
        // The request itself
        private JSONObject request;

        // Create the request
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

    // CreateNewTransaction - Call the RPC server's NewTransaction() method and return the server's response
    public String CreateNewTransaction() {
        return "";    
    }
}