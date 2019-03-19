package com.summercash.mcsummercash.commands;

import com.summercash.mcsummercash.api.CreateTransaction;

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
        double amount;
    
        // for (int i = 0; i < args.length; i++) {
        //     sender.sendMessage("args[" + i + "]" + ": " + args[i]);
        // }

        // Check that the inputs are not null
        if (args.length != 3) {
            return false;
        } else {
            senderAddr = args[0];
            recipientAddr = args[1];
            amount = Double.parseDouble(args[2]);
        }
        
        // Create a NewTransaction class (API)
        CreateTransaction newTransaction = new CreateTransaction();

        try {
            // Read and parse the server's response
            String response = newTransaction.CreateNewTransaction(senderAddr, recipientAddr, amount);
            System.out.println(response);

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