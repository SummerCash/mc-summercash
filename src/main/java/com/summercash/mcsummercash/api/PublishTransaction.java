package com.summercash.mcsummercash.api;

import java.io.IOException;

// PublishTransaction - Publish a transaction given its hash and parse the response
public class PublishTransaction {

    // PublishTransactionHash - Publish a transaction given its hash
    public String PublishTransactionHash(String transactionHash) throws IOException {
        // Dial up the RPC node
        Connection connection = new Connection("transaction.Transaction/Publish");

        // Make & send the request
        TransactionGeneralRequest req = new TransactionGeneralRequest(
            transactionHash,
            "",
            0.0
        );
        connection.Write(req.GetRequest());

        // Read from connection
        String message = connection.Read();

        // Close the connection
        connection.Close();

        return message;
    }
}