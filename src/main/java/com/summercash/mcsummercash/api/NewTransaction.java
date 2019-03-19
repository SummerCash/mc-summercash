package com.summercash.mcsummercash.api;

import org.apache.commons.codec.binary.Base64;
import java.io.IOException;

import org.json.simple.JSONObject;

// NewTransaction - Create a new transaction on the SummerCash network
public class NewTransaction {

    // CreateNewTransaction - Call the RPC server's NewTransaction() method and
    // return the server's response
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
}