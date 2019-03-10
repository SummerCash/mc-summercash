package com.summercash.mcsummercash.commands;

import com.summercash.mcsummercash.api.NewTransaction;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

// NewTransactionCmd - The Minecraft command wrapper for the NewTransaction class
public class NewTransactionCmd implements CommandExecutor {

    // onCommand - Run when the command is called
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String senderAddr, recipientAddr;
        float amount;
    
        // Check that the sender address is not null
        if (args[1] != "") {
            senderAddr = args[1];
        } else {
            sender.sendMessage("The sender address must not be blank!");
            return false;
        }
        
        // Check that the recipient address is not null
        if (args[2] != "") {
            recipientAddr = args[2];
        } else {
            sender.sendMessage("The recipient address must not be blank!");
            return false;
        }

        // Check that the amount is not null
        if (args[3] != "") {
            senderAddr = args[3];
        } else {
            sender.sendMessage("The amount must not be blank!");
            return false;
        }
        
        // Create a NewTransaction class (API)
        NewTransaction newTransaction = new NewTransaction();

        try {
            // Read and parse the server's response
            String response = newTransaction.CreateNewTransaction(senderAddr, recipientAddr, amount);
            JSONObject parsedResponse = (JSONObject) (new JSONParser().parse(response));
            String parsedMessage = (String) parsedResponse.get("message");
            
            // Get the transaction hash
            String transactionHash = parsedMessage.split("hash: ")[1];
            
            // Tell the user that the transaction has completed successfully
            sender.sendMessage("Transaction '" + transactionHash + "' created!");
        }

        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}