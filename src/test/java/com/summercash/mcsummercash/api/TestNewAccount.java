package com.summercash.mcsummercash.api;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestNewAccount {

    @Test
    public void TestCreateNewAccount() {
        NewAccount newAccount = new NewAccount();

        try {
            String response = newAccount.CreateNewAccount();
            assertTrue(response, response != "");
        }

        catch (Exception e) {
            fail(e.getLocalizedMessage());
        }
    }
}