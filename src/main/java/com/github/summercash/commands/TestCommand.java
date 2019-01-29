package com.github.summercash.commands;

import org.bukkit.plugin.java.JavaPlugin; // Not sure if I need this just yet

public class TestCommand implements CommandExecutor {

    // This method is called, when somebody uses our command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        System.out.println("console: command typed");
        sender.sendMessage("minecraft: command worked");
        return false;
    }

    @Override
    public void onEnable() {
        // Register our command "kit" (set an instance of your command class as executor)
        this.getCommand("test").setExecutor(new TestCommand());
    }

}