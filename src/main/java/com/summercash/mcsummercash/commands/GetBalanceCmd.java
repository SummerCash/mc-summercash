package com.summercash.mcsummercash.commands;

import java.io.IOException;

import com.summercash.mcsummercash.api.GetBalance;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GetBalanceCmd implements CommandExecutor {

    // onCommand - Run when the command is called
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String address;

        if (args.length != 1) {
            return false;
        } else {
            address = args[0];
        }

        // Link to the API
        GetBalance getBalance = new GetBalance();

        try {
            // Call the API and get the response
            String response = getBalance.GetAccountBalance(address);
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