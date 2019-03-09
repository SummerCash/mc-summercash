package com.summercash.mcsummercash.plugin;

import com.summercash.mcsummercash.commands.*;
import org.bukkit.plugin.java.JavaPlugin;

public class SummerCash extends JavaPlugin {
    // Run when plugin is first enabled
	@Override
    public void onEnable() {
        // Register the commands
        this.getCommand("summercash").setExecutor(new SummercashCmd());
        this.getCommand("test").setExecutor(new TestCmd());
        this.getCommand("account").setExecutor(new NewAccountCmd());
    }
    
    // Run when plugin is disabled
    @Override
    public void onDisable() {
    }
}
