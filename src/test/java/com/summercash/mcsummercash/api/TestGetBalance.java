package com.summercash.mcsummercash.api;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;

public class TestGetBalance {

    @Test
    public void TestGetAccountBalance() {
        GetBalance getBalance = new GetBalance();

        try {
            // Check one account
            String response = getBalance.GetAccountBalance("alice");
            assertTrue(response, response != "");
        }

        catch (IOException e) {
            fail(e.getLocalizedMessage());
        }
    }
}