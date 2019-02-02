package com.github.summercash.api;

import org.json.simple.JSONObject;

public class GeneralRequest {
    private JSONObject reqData;
    
    public String getData() {
        String req = reqData.toJSONString();
        return req;
    }
    
    GeneralRequest(String address, String address2, double amount) {
        reqData = new JSONObject();

        reqData.put("nonce", "");
        reqData.put("address", address);
        reqData.put("address2", address2);
        reqData.put("amount", amount);
        reqData.put("payload", "despacito ree");

    }
}