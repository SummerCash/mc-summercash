package com.summercash.mcsummercash.api;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestCreateTransaction {

    @Test
    public void TestCreateNewTransaction() {
        CreateTransaction newTransaction = new CreateTransaction();

        try {
            String response = newTransaction.CreateNewTransaction(
                "0x040040dc9d49726e5c9d21a3ee3da7e2be5e",
                "0x04015d0c9699aa74db4edc851e322ae9b028",
                0.0
            );
            System.out.println(response);
            assertTrue(response, response != "");
        }

        catch (Exception e) {
            fail(e.getLocalizedMessage());
        }
    }
}