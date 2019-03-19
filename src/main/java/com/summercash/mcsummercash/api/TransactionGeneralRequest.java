package com.summercash.mcsummercash.api;

import org.apache.commons.codec.binary.Base64;

import org.json.simple.JSONObject;

// TransactionGeneralRequest - A JSON object for go-summercash transaction RPC methods
public class TransactionGeneralRequest {
    // The request itself
    private JSONObject request;

    // GeneralRequest - Create the request
    @SuppressWarnings("unchecked") // The warnings really don't mean anything
    TransactionGeneralRequest(String sender, String recipient, double amount) {
        // Create the payload
        String rawPayload = "test payload!";
        
        // Encode using base64
        byte[] encodedPayload = Base64.encodeBase64(rawPayload.getBytes());

        // Create the JSON request
        request = new JSONObject();
        request.put("nonce", 0);
        request.put("address", sender);
        request.put("address2", recipient);
        request.put("amount", amount);
        request.put("payload", new String(encodedPayload));
    }
    
    // GetRequest - Return the JSON object as a string
    public String GetRequest() {
        return request.toString();
    }
}