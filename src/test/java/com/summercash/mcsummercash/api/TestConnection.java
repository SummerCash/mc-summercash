package com.summercash.mcsummercash.api;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;

public class TestConnection {

    @Test
    public void TestCreateNewAccount() {
        try {
            Connection connection = new Connection("common.Common/EncodeString");

            String data = "{\"input\": \"test\", \"s\": \"\"}";
            connection.Write(data);

            String response = connection.Read();
            assertTrue(response, response != "");

            connection.Close();
        } catch (IOException e) {
            fail(e.getLocalizedMessage());
        }
    }
}