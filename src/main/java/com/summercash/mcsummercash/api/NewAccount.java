package com.summercash.mcsummercash.api;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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

    // CreateNewAccount - Call the RPC server's NewAccount() method and return the
    // server's response
    public String CreateNewAccount() throws Exception {
        // Create a connection
        Connection connection = new Connection("accounts.Accounts/NewAccount");

        // Send the req
        GeneralRequest req = new GeneralRequest("", "");
        connection.Write(req.GetRequest());

        // Read from connection
        String message = connection.Read();

        // Close the connection
        connection.Close();

        return message;
    }

    // Parse - Parse the return of the CreateNewAccount method for the address of the new account
    public String Parse(String response) throws ParseException {
        JSONObject parsedResponse = (JSONObject) (new JSONParser().parse(response));

        // Retrieve the 'message' from JSON
        String rawMessage = (String) parsedResponse.get("message");
        String[] parsed = rawMessage.split(", ", 2);

        String address = parsed[0];
        // String privateKey = parsed[1]; // Do something with this later?

		return address;
	}
}