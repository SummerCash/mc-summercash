package com.summercash.mcsummercash.commands;

import java.io.IOException;

import com.summercash.mcsummercash.api.GetBalance;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.json.simple.parser.ParseException;

public class GetBalanceCmd implements CommandExecutor {

    // onCommand - Run when the command is called
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Get the Minecraft username
        String rawMCUsername = sender.toString();
        String mcUsername = rawMCUsername.split("name=")[1].replace("}", "");

        // Link to the API
        GetBalance getBalance = new GetBalance();

        try {
            // Call the API and get the response
            String response = getBalance.GetAccountBalance(mcUsername);
            String balance = getBalance.Parse(response);

            // Tell the user the balance
            sender.sendMessage("Balance: " + balance);
        }

        catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        catch (ParseException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

}