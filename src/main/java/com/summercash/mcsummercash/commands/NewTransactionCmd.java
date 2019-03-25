package com.summercash.mcsummercash.commands;

import com.summercash.mcsummercash.api.NewTransaction;
import com.summercash.mcsummercash.common.Common;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

// NewTransactionCmd - The Minecraft command wrapper for the NewTransaction class
public class NewTransactionCmd implements CommandExecutor {

    // onCommand - Run when the command is called
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String recipientUsername;
        double amount;

        // Check that the inputs are not null
        if (args.length != 2) {
            return false;
        }

        // Store the values from the command arguments
        recipientUsername = args[0];
        amount = Double.parseDouble(args[1]);

        // Get the sender's username
        String senderUsername = Common.ParseMCUsername(sender.toString());

        // Create a NewTransaction class (API)
        NewTransaction transactionCreator = new NewTransaction();

        try {
            // Read the server's response
            String response = transactionCreator.Transaction(senderUsername, recipientUsername, amount);

            // Check that the transaction happened
            if (response == null) {
                sender.sendMessage("That transaction can't happen!");
                return true;
            }

            // Tell the user that the transaction has completed successfully
            sender.sendMessage("Transaction completed!");
            sender.sendMessage("Transaction address: " + response);
        }

        // If err != nil
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}