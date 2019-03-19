package com.summercash.mcsummercash.api;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;

import com.summercash.mcsummercash.common.Common;

public class TestGetBalance {

    @Test
    public void TestGetAccountBalance() {
        GetBalance getBalance = new GetBalance();

        try {
            // Check one account
            String xoreoResponse = getBalance.GetAccountBalance(Common.XoreoAddress);
            assertTrue(xoreoResponse, xoreoResponse != "");

            // Check another account
            String otherResponse = getBalance.GetAccountBalance(Common.OtherAddress);
            assertTrue(otherResponse, otherResponse != "");
        }
        
        catch (IOException e) {
            fail(e.getLocalizedMessage());
        }
    }
}