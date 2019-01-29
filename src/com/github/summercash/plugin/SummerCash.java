package com.github.summercash.plugin;

import org.bukkit.plugin.java.JavaPlugin;
import com.github.summercash.commands.TestCommand;

public class SummerCash extends JavaPlugin {
    // Fired when plugin is first enabled
	@Override
    public void onEnable() {
        // Register our command "kit" (set an instance of your command class as executor)
        this.getCommand("test").setExecutor(new TestCommand());
    }
    
    // Fired when plugin is disabled
    @Override
    public void onDisable() {
        
    }
}
 