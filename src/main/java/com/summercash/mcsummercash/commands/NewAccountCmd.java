package com.summercash.mcsummercash.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.summercash.mcsummercash.api.*;
import com.summercash.mcsummercash.common.Common;

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
            String mcUsername = Common.ParseMCUsername(sender.toString());

            // Get the response and parse
            String address = newAccount.CreateNewAccount(mcUsername); // Already parsed data

            // Tell the user their new address
            // Check that an account was actually created
            if (address == null) {
                sender.sendMessage("You already have an account!");
                return true;
            }
            sender.sendMessage("SummerCash account created!");
            sender.sendMessage("Address: " + address);
        }

        catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

}