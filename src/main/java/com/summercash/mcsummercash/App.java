package com.summercash.mcsummercash;

import com.summercash.mcsummercash.api.*;

public class App {
    public static void main(String[] args) throws Exception {
        NewAccount newAccount = new NewAccount("localhost");
        String address = newAccount.CreateNewAccount();
        System.out.println(address);
        
    }
}
