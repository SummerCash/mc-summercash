package com.summercash.mcsummercash.common;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class TestCommon {

    @Test
    public void TestGlobals() {
        assertTrue("PROVIDER exists and is not null", Common.PROVIDER != "" && Common.PROVIDER != null);
        assertTrue("PORT exists and is not null", Common.PORT != "" && Common.PORT != null);
    }
}