package com.summercash.mcsummercash.commands;

import com.summercash.mcsummercash.api.NewTransaction;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

// NewTransactionCmd - The Minecraft command wrapper for the NewTransaction class
public class NewTransactionCmd implements CommandExecutor {

    // onCommand - Run when the command is called
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Check that the player's inputs are not null
        

        NewTransaction newTransaction = new NewTransaction();
        return true;
    }
}