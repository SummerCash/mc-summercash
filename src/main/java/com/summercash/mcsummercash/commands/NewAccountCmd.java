package com.summercash.mcsummercash.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.json.JSONObject;

import com.summercash.mcsummercash.api.*;

public class NewAccountCmd implements CommandExecutor {

    // This method is called, when somebody uses our command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        NewAccount newAccount = new NewAccount("localhost");
        sender.sendMessage("test message");
        sender.sendMessage("Creating SummerCash account...");

        // Create the account
        try {
            // Get the response and parse
            String response = newAccount.CreateNewAccount();
            JSONObject parsedResponse = new JSONObject(response);
            String message = parsedResponse.getString("message");

            // Parse the data to get addr and privkey
            String[] parsed = message.split(", PrivateKey: ", 2);
            String address = parsed[0];
            String privateKey = parsed[1];
            // System.out.println(address);

            sender.sendMessage("Address: " + address);
            sender.sendMessage("SummerCash account created!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

}