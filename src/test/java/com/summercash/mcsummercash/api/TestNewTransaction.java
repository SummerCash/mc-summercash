package com.summercash.mcsummercash.api;

import com.summercash.mcsummercash.common.Common;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;

public class TestNewTransaction {

    @Test
    public void TestTransaction() {
        NewTransaction newTransaction = new NewTransaction();

        String completedTxn = null;
        try {
            completedTxn = newTransaction.Transaction(Common.TestingAddress1, Common.TestingAddress2, 0.0);
        } catch (IOException e) {
            fail(e.getLocalizedMessage());
        }

        assertTrue("completed transaction: ", completedTxn != null);

    }
}