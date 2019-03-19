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
            // Get the response and parse
            String response = newAccount.CreateNewAccount();
            String address = newAccount.Parse(response);

            // Tell the user their new address
            sender.sendMessage(address);
            sender.sendMessage("SummerCash account created!");
        }
        
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}