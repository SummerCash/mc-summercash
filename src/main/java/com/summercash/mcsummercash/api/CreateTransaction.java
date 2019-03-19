package com.summercash.mcsummercash.api;

import java.io.IOException;

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
}