package com.summercash.mcsummercash.api;

import org.json.JSONObject;

// AccountsGeneralRequest - A JSON object for go-summercash account RPC methods
public class AccountsGeneralRequest {
    // The request itself
    private JSONObject request;

    // GeneralRequest - Create the request
    @SuppressWarnings("unchecked") // The warnings really don't mean anything
    AccountsGeneralRequest(String address, String privateKey) {
        // Create the JSON request
        request = new JSONObject();
        request.put("address", address);
        request.put("privateKey", privateKey);
    }

    // GetRequest - Return the JSON object as a string
    public String GetRequest() {
        return request.toString();
    }
}