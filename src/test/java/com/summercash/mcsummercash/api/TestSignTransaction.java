package com.summercash.mcsummercash.api;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;

public class TestSignTransaction {

    @Test
    public void TestSignTransactionHash() throws IOException {
        // Create a transaction
        CreateTransaction createTransaction = new CreateTransaction();
        String txnResponse = createTransaction.CreateNewTransaction(
            "0x040040dc9d49726e5c9d21a3ee3da7e2be5e",
            "0x04015d0c9699aa74db4edc851e322ae9b028",
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