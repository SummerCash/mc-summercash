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
            String response = getBalance.GetAccountBalance("0x04015c81297276300dba9bb18bf4441f3b09");
            assertTrue(response, response != "");
        }
        
        catch (IOException e) {
            fail(e.getLocalizedMessage());
        }
    }
}