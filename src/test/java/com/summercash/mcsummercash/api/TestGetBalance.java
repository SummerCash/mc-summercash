package com.summercash.mcsummercash.api;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;

public class TestGetBalance {

    @Test
    public void TestGetAccountBalance() {
        try {
            Connection connection = new Connection("chain.Chain/GetBalance");

            String data = "{\"address\": \"0x04013a3e055fd5d07a18a1d5f9b88abba6f9\"}";
            connection.Write(data);

            String response = connection.Read();
            assertTrue(response, response != "");

            connection.Close();
        } catch (IOException e) {
            fail(e.getLocalizedMessage());
        }
    }
}