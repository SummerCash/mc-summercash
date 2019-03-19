package com.summercash.mcsummercash.api;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// NewTransaction - Create a new transaction on the SummerCash network
public class CreateTransaction {

    // CreateNewTransaction - Create a transaction on the SummerCash network
    public String CreateNewTransaction(String sender, String recipient, double amount) throws IOException {
        // Create a connection
        Connection connection = new Connection("transaction.Transaction/NewTransaction");

        // Send the req
        TransactionGeneralRequest req = new TransactionGeneralRequest(sender, recipient, amount);
        connection.Write(req.GetRequest());

        // Read from connection
        String message = connection.Read();
        System.out.println(message);

        // Close the connection
        connection.Close();

        return message;
    }

    // Parse - Parse the return of the CreateNewTransaction method for the transaction hash
    public String Parse(String response) throws ParseException {
        JSONObject parsedResponse = (JSONObject) (new JSONParser().parse(response));
        String parsedMessage = (String) parsedResponse.get("message");
        
        // Get the transaction hash
        String transactionHash = parsedMessage.split("hash: ")[1];
        return transactionHash;
    }
}