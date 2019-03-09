package com.summercash.mcsummercash.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.summercash.mcsummercash.api.*;

public class NewAccountCmd implements CommandExecutor {

    // This method is called, when somebody uses our command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        NewAccount newAccount = new NewAccount();
        sender.sendMessage("Creating SummerCash account...");

        // Create the account
        try {
            sender.sendMessage("SummerCash account created!");
            sender.sendMessage("Address: " + newAccount.CreateNewAccount());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

}