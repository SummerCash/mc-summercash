package com.summercash.mcsummercash.commands;

import java.io.IOException;

import com.summercash.mcsummercash.api.GetBalance;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

class GetBalanceCmd implements CommandExecutor {

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

            // Parse the response
            JSONObject parsedResponse = (JSONObject) (new JSONParser().parse(response));
            String parsedMessage = (String) parsedResponse.get("message");
            
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