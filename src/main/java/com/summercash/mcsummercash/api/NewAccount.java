package com.summercash.mcsummercash.api;

import org.json.JSONObject;

public class NewAccount {

    private class GeneralRequest {
        private JSONObject request;
        GeneralRequest(String privateKey, String address) {
            request = new JSONObject();
            request.put("address", address);
            request.put("privateKey", privateKey);
        }

        String GetRequest() {
            return request.toString();
        }
    }

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