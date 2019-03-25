package com.summercash.mcsummercash.api;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;

import com.summercash.mcsummercash.common.Common;

import org.json.simple.parser.ParseException;
import org.junit.Test;

public class TestSignTransaction {

    @Test
    public void TestSignTransactionHash() throws IOException, ParseException {
        // Create a transaction
        CreateTransaction createTransaction = new CreateTransaction();
        String txnResponse = createTransaction.CreateNewTransaction(
            Common.TestingAddress1,
            Common.TestingAddress2,
            0.0
        );

        String transactionHash = createTransaction.Parse(txnResponse);

        // Sign the transaction
        SignTransaction signTransaction = new SignTransaction();

        try {
            String signResponse = signTransaction.SignTransactionHash(transactionHash);
            assertTrue(signResponse, signResponse != "");
        }

        catch (Exception e) {
            fail(e.getLocalizedMessage());
        }
    }
}