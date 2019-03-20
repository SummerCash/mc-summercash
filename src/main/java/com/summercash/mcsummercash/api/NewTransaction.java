package com.summercash.mcsummercash.api;

// NewTransaction - Create, sign, and publish a transaction to the SummerCash network
// This is a wrapper class that makes the process of creating a transaction easier
public class NewTransaction {
    private String transactionHash;

    // Transaction - Call the internal create, sign, and publish methods
    public boolean Transaction(String sender, String recipient, double amount) {
        // Call the internal methods
        boolean didCreate = Create(sender, recipient, amount);
        boolean didSign = Sign();
        boolean didPublish = Publish();

        if (didCreate && didSign && didPublish) {
            return true;
        }

        return false;
    }

    // Create - Create a transaction
    private boolean Create(String sender, String recipient, double amount) {
        CreateTransaction transactionCreator = new CreateTransaction();

        // Get the server's response
        try {
            String res = transactionCreator.CreateNewTransaction(sender, recipient, amount);
            transactionHash = transactionCreator.Parse(res);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    // Sign - Sign the transaction
    private boolean Sign() {
        return false;
    }

    // Publish - Publish the transaction to the network
    private boolean Publish() {
        return false;
    }

}