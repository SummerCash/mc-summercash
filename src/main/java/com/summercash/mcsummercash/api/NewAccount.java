package com.summercash.mcsummercash.api;

import com.summercash.mcsummercash.database.Database;

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
    public String CreateNewAccount(String mcUsername) throws Exception {
        // Make sure that the user doesn't have an account already
        Database usernameDB = new Database();
        usernameDB.Open();
        if (usernameDB.GetAddress(mcUsername) != null) {
            usernameDB.Close();
            return null;
        }

        // Create a connection
        Connection connection = new Connection("accounts.Accounts/NewAccount");

        // Send the req
        GeneralRequest req = new GeneralRequest("", "");
        connection.Write(req.GetRequest());

        // Read from connection
        String message = connection.Read();
        String parsedAddress = Parse(message);

        System.out.println("mcUsername: " + mcUsername);
        System.out.println("parsedAddress: " + parsedAddress);

        // Parse and add to the database
        usernameDB.PutAddress(mcUsername, parsedAddress);

        // Close the database
        usernameDB.Close();

        // Close the connection
        connection.Close();

        return parsedAddress;
    }

    // Parse - Parse the return of the CreateNewAccount method for the address of
    // the new account
    public String Parse(String response) throws ParseException {
        JSONObject parsedResponse = (JSONObject) (new JSONParser().parse(response));

        // Retrieve the 'message' from JSON
        String rawMessage = (String) parsedResponse.get("message");
        String[] parsed = rawMessage.split(", ", 2);

        String address = parsed[0];
        String rawAddress = address.substring(10); // Remove the "\nAddress: "
        // String privateKey = parsed[1]; // Do something with this later?

        return rawAddress;
    }
}