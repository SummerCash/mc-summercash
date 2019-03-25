package com.summercash.mcsummercash.api;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.summercash.mcsummercash.common.Common;

public class TestCreateTransaction {

    @Test
    public void TestCreateNewTransaction() {
        CreateTransaction newTransaction = new CreateTransaction();

        try {
            String response = newTransaction.CreateNewTransaction(
                Common.TestingAddress1,
                Common.TestingAddress2,
                0.0
            );
            assertTrue(response, response != "");
        }

        catch (Exception e) {
            fail(e.getLocalizedMessage());
        }
    }
}