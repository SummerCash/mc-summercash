package com.summercash.mcsummercash.api;

import com.summercash.mcsummercash.common.Common;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class TestNewTransaction {

    @Test
    public void TestTransaction() {
        NewTransaction newTransaction = new NewTransaction();

        boolean completedTxn = newTransaction.Transaction(
            Common.TestingAddress1,
            Common.TestingAddress2,
            0.0
        );

        assertTrue("completed transaction: ", completedTxn);

    }
}