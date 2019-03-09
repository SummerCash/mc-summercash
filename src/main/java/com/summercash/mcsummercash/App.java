package com.summercash.mcsummercash;

import com.summercash.mcsummercash.api.*;

public class App {
    public static void main(String[] args) throws Exception {
        NewAccount sslTest = new NewAccount();
        String s = sslTest.CreateNewAccount();
        System.out.println(s);
        
    }
}
