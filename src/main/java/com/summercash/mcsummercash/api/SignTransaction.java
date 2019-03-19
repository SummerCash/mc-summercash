package com.summercash.mcsummercash.api;

import java.io.IOException;

// SignTransaction - Sign a transaction on the SummerCash network
public class SignTransaction {

    // SignTransactionHash - Sign a transaction given its hash
    public String SignTransactionHash(String transactionHash) throws IOException {
        // Dial up the RPC node
        Connection connection = new Connection("transaction.Transaction/SignTransaction");

        // Make & send the request
        TransactionGeneralRequest req = new TransactionGeneralRequest(
            "HASH",
            "",
            0.0
        );
        connection.Write(req.GetRequest());

        // Read from connection
        String message = connection.Read();
        System.out.println(message);

        // Close the connection
        connection.Close();

        return message;

    }
}