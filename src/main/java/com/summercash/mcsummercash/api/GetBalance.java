package com.summercash.mcsummercash.api;

import java.io.IOException;

import com.summercash.mcsummercash.database.Database;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// GetBalance - Get the balance associated with a certain account address
public class GetBalance {

    // GeneralRequest - A JSON object for go-summercash's protobuf RPC server
    private class GeneralRequest {
        // The request itself
        private JSONObject request;

        // GeneralRequest - Create the request
        @SuppressWarnings("unchecked") // The warnings really don't mean anything
        GeneralRequest(String address) {
            request = new JSONObject();
            request.put("address", address);
        }

        // GetRequest - Return the JSON object as a string
        public String GetRequest() {
            return request.toString();
        }
    }

    // GetAccountBalance - Return the balance of an account
    public String GetAccountBalance(String mcUsername) throws IOException {
        // Create a connection
        Connection connection = new Connection("chain.Chain/GetBalance");

        // Retrieve the address from the MC username
        Database usernameDB = new Database();
        usernameDB.Open();

        // Get the address
        String address = usernameDB.GetAddress(mcUsername);

        usernameDB.Close();

        // Send the request
        GeneralRequest req = new GeneralRequest(address);
        connection.Write(req.GetRequest());

        // Read from the connection
        String message = connection.Read();

        // Close the connection
        connection.Close();

        return message;
    }

    // Parse - Parse the return of the GetAccountBalance method for the balance of
    // the account
    public String Parse(String response) throws ParseException {
        // Parse the response
        JSONObject parsedResponse = (JSONObject) (new JSONParser().parse(response));
        String parsedMessage = (String) parsedResponse.get("message");

        // Get the balance
        String balance = parsedMessage.split("balance: ")[1];
        return balance;
    }

}