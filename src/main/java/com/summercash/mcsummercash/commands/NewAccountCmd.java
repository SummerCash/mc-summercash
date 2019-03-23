package com.summercash.mcsummercash.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.summercash.mcsummercash.api.*;

// NewAccountCmd - The Minecraft command wrapper for the NewAccount class
public class NewAccountCmd implements CommandExecutor {

    // onCommand - Run when the command is called
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        NewAccount newAccount = new NewAccount();
        sender.sendMessage("Creating SummerCash account...");

        // Create the account
        try {
            // Get the Minecraft username
            String rawMCUsername = sender.toString();
            String mcUsername = rawMCUsername.split("name=")[1].replace("}", "");
            
            // Get the response and parse
            String address = newAccount.CreateNewAccount(mcUsername); // Already parsed data

            // Tell the user their new address
            sender.sendMessage("Address: " + address);
            sender.sendMessage("SummerCash account created!");
        }
        
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}