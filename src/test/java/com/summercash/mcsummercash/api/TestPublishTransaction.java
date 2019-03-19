package com.summercash.mcsummercash.api;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;

import com.summercash.mcsummercash.common.Common;

import org.json.simple.parser.ParseException;
import org.junit.Test;

public class TestPublishTransaction {

    @Test
    public void TestPublishTransactionHash() throws IOException, ParseException {
        // Create a transaction
        CreateTransaction createTransaction = new CreateTransaction();
        String txnResponse = createTransaction.CreateNewTransaction(
            Common.XoreoAddress,
            Common.OtherAddress,
            0.0
        );

        String transactionHash = createTransaction.Parse(txnResponse);

        // Sign the transaction
        SignTransaction signTransaction = new SignTransaction();
        signTransaction.SignTransactionHash(transactionHash);

        // Publish the transaction
        // PublishTransaction publishTransaction = new PublishTransaction();
        // String response = publishTransaction.PublishTransactionHash(transactionHash);
    }
}