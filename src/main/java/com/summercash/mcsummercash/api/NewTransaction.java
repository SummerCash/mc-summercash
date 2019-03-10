package com.summercash.mcsummercash.api;

import org.json.simple.JSONObject;

class NewTransaction {

    private class GeneralRequest {
        private JSONObject request;
        public GeneralRequest(String sender, String recipient, float amount) {
            request = new JSONObject();
            request.put("address", sender);
            request.put("address2", recipient);
            request.put("amount", amount);
            request.put("nonce", "");
            request.put("payload", "test payload");
        }

        String GetRequest() {
            return request.toString();
        }
    }

    public NewTransaction() {
        
    }
}