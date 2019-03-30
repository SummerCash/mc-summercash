package com.summercash.mcsummercash.commands;

import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.json.simple.parser.ParseException;

import com.summercash.mcsummercash.api.*;
import com.summercash.mcsummercash.common.Common;

public class GetBalanceCmd implements CommandExecutor {

    // onCommand - Run when the command is called
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Get the Minecraft username
        String mcUsername = Common.ParseMCUsername(sender.toString());

        // Link to the API
        GetBalance getBalance = new GetBalance();

        try {
            // Call the API and get the response
            String response = getBalance.GetAccountBalance(mcUsername);

            // Tell the user the balance
            if (response == null) {
                sender.sendMessage("You don't have an account! Create one with /account.");
                return true; // True so that the command help doesn't show again (ingame)
            }

            // Parse the response & tell the user
            String balance = getBalance.Parse(response);
            sender.sendMessage("Balance: " + balance + " SMC");
        }

        catch (IOException e) {
            e.printStackTrace();
        }

        catch (ParseException e) {
            e.printStackTrace();
        }

        return true;
    }

}