package com.summercash.mcsummercash.api;

import static org.junit.Assert.assertTrue;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import com.summercash.mcsummercash.common.Common;

public class TestPublishTransaction {

    @Test
    public void TestPublishTransactionHash() throws IOException, ParseException {
        // Create a transaction
        CreateTransaction createTransaction = new CreateTransaction();
        String txnResponse = createTransaction.CreateNewTransaction(Common.TestingAddress1, Common.TestingAddress2,
                0.0);

        String transactionHash = createTransaction.Parse(txnResponse);

        // Sign the transaction
        SignTransaction signTransaction = new SignTransaction();
        signTransaction.SignTransactionHash(transactionHash);

        // Publish the transaction
        PublishTransaction publishTransaction = new PublishTransaction();
        String response = publishTransaction.PublishTransactionHash(transactionHash);
        assertTrue(response, response != "");
    }
}