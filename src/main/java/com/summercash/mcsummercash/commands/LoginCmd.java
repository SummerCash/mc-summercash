package com.summercash.mcsummercash.commands;

import com.summercash.mcsummercash.api.*;
import com.summercash.mcsummercash.common.Common;
import com.summercash.mcsummercash.database.Database;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class LoginCmd implements CommandExecutor {
    // onCommand - Run when the command is called
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 2) { // Check invalid args
            return false; // Show help screen
        }

        final Auth auth = new Auth(); // Initialize auth abstraction class

        final String userAddress = auth.Authenticate(args[0], args[1]); // Authenticate

        if (userAddress == "") { // Check cannot authenticate
            sender.sendMessage("Looks like that password is invalid. Try again."); // Log error

            return true; // Not successful, but don't show the help screen
        }

        try {
            Database usernameDB = new Database(); // Init db helper class
            usernameDB.Open(); // Open database

            final String mcUsername = Common.ParseMCUsername(sender.toString()); // Parse username

            System.out.println("mcUsername: " + mcUsername); // Log user
            System.out.println("userAddress: " + userAddress); // Log address

            usernameDB.PutAddress(mcUsername, userAddress); // Put address

            sender.sendMessage(
                    "Success! You can now send and receive SummerCash via your summer.cash account in this server."); // Log
                                                                                                                      // success!

            usernameDB.Close(); // Close database
        } catch (Exception e) {
            e.printStackTrace(); // Print stack trace

            return false; // Shouldn't happen
        }

        return true; // Success
    }
}