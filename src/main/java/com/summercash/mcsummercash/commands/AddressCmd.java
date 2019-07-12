package com.summercash.mcsummercash.commands;

import com.summercash.mcsummercash.common.Common;
import com.summercash.mcsummercash.database.Database;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AddressCmd implements CommandExecutor {
    // onCommand - Run when the command is called
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            Database usernameDB = new Database(); // Init db
            usernameDB.Open(); // Open DB

            final String senderUsername = Common.ParseMCUsername(sender.toString()); // Parse address

            final String address = usernameDB.GetAddress(senderUsername); // Get address

            if (address == null) {
                System.out.println("db lookup failed on txn sender");
                usernameDB.Close();
                sender.sendMessage("You don't have an account! Create one with /account."); // Log no account

                return true; // Return
            }

            usernameDB.Close(); // Close DB

            sender.sendMessage(address); // Log address

            return true;
        } catch (Exception e) {
            e.printStackTrace();

            return false; // Lol
        }
    }
}