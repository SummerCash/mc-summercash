package com.summercash.mcsummercash.api;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestNewTransaction {

    @Test
    public void TestCreateNewTransaction() {
        NewTransaction newTransaction = new NewTransaction();

        try {
            String response = newTransaction.CreateNewTransaction("0x0400fadce1aef1990196200628dfd46ec5f0", "0x0401c3f0b97400b3bcc568e3c305bd2222ac", 0.0);
            assertTrue(response, response != "");
        }

        catch (Exception e) {
            fail(e.getLocalizedMessage());
        }
    }
}