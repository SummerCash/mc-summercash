package com.summercash.mcsummercash.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

// TestCmd - This is a Minecraft command for simple testing purposes
public class TestCmd implements CommandExecutor {

    // onCommand - Run when the command is called
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("mc-summercash is loaded");
        return true;
    }

}