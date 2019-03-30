package com.summercash.mcsummercash.api;

import java.io.IOException;

import com.summercash.mcsummercash.common.Common;
import com.summercash.mcsummercash.database.Database;

// NewTransaction - Create, sign, and publish a transaction to the SummerCash network
// This is a wrapper class that makes the process of creating a transaction easier
public class NewTransaction {
    private String transactionHash;

    // Transaction - Call the internal create, sign, and publish methods
    public String Transaction(String sender, String recipient, double amount) throws IOException {
        // Get the addresses from the database
        Database usernameDB = new Database();
        usernameDB.Open();
        String senderAddress = usernameDB.GetAddress(sender);
        String recipientAddress = usernameDB.GetAddress(recipient);

        System.out.println("senderAddress: " + senderAddress);
        System.out.println("recipientAddress: " + recipientAddress);

        // Check that the database calls are not null
        if (senderAddress == null) {
            System.out.println("db lookup failed on txn sender");
            usernameDB.Close();
            return Common.UnknownSenderAddress;
        }
        
        if (recipientAddress == null) {
            System.out.println("db lookup failed on txn recipient");
            usernameDB.Close();
            return Common.UnknownRecipientAddress;
        }
        usernameDB.Close();

        // Call the internal methods
        boolean didCreate = Create(senderAddress, recipientAddress, amount);
        boolean didSign = Sign();
        boolean didPublish = Publish();

        if (didCreate && didSign && didPublish) {
            return transactionHash;
        }

        return null;
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
        SignTransaction transactionSigner = new SignTransaction();

        // Call the SignTransactionHash method
        try {
            transactionSigner.SignTransactionHash(transactionHash);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    // Publish - Publish the transaction to the network
    private boolean Publish() {
        PublishTransaction transactionPublisher = new PublishTransaction();

        // Call the PublishTransactionHash method
        try {
            transactionPublisher.PublishTransactionHash(transactionHash);
        } catch (Exception e) {
            System.out.println("publishing error: insufficient funds\n");
            // e.printStackTrace();
            return false;
        }

        return true;
    }

}