package com.summercash.mcsummercash.plugin;

import com.summercash.mcsummercash.commands.*;
import org.bukkit.plugin.java.JavaPlugin;

// SummerCash - The main Minecraft plugin file
public class SummerCash extends JavaPlugin {
    // onEnable - Runs when the plugin is enabled upon MC server startup
	@Override
    public void onEnable() {
        // Register the commands
        this.getCommand("test").setExecutor(new TestCmd());
        this.getCommand("account").setExecutor(new NewAccountCmd());
        this.getCommand("transaction").setExecutor(new NewTransactionCmd());
        this.getCommand("balance").setExecutor(new GetBalanceCmd());
    }
    
    // Run when plugin is disabled
    @Override
    public void onDisable() {
    }
}
