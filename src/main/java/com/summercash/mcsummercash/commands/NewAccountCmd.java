package com.summercash.mcsummercash.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.summercash.mcsummercash.api.*;

public class NewAccountCmd implements CommandExecutor {

    // This method is called, when somebody uses our command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        NewAccount newAccount = new NewAccount();
        sender.sendMessage("test message");
        sender.sendMessage("Creating SummerCash account...");

        // Create the account
        try {
            // Get the response and parse
            String response = newAccount.CreateNewAccount();
            JSONObject parsedResponse = (JSONObject) (new JSONParser().parse(response));

            // Retrieve the 'message' from JSON
            String rawMessage = (String) parsedResponse.get("message");
            String[] parsed = rawMessage.split(", ", 2);
            
            // Get the addr and privkey
            String address = parsed[0];
            String privateKey = parsed[1];
            System.out.println(address);
            System.out.println(privateKey);

            // Tell the user their new address
            sender.sendMessage(address);
            sender.sendMessage("SummerCash account created!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

}