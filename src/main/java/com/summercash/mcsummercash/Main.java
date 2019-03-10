package com.summercash.mcsummercash;

import com.summercash.mcsummercash.api.*;

// Main - The project's main function. This doesn't have much of a purpose
public class Main {

    private static void NewTxn() {
        NewTransaction newTransaction = new NewTransaction();

        try {
            String response = newTransaction.CreateNewTransaction("0x0400fadce1aef1990196200628dfd46ec5f0", "0x0401c3f0b97400b3bcc568e3c305bd2222ac", 0.0);
            System.out.println(response);
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void NewAccount() {
        NewAccount newAccount = new NewAccount();

        try {
            String response = newAccount.CreateNewAccount();
            System.out.println(response);
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        NewTxn();
        NewAccount();
    }
}