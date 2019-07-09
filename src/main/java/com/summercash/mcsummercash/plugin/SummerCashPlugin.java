package com.summercash.mcsummercash.plugin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import com.summercash.mcsummercash.JarUtils;
import com.summercash.mcsummercash.commands.*;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/*
    For loading external jars:
    Thank you @fletch_to_99 for your awesome solution.
    https://bukkit.org/threads/tutorial-use-external-library-s-with-your-plugin.103781/
*/

// SummerCashPlugin - The main Minecraft plugin file
public class SummerCashPlugin extends JavaPlugin {

    // AddClassPath - Add a class to the path (used for loading external jars
    // (leveldbjni))
    private void AddClassPath(final URL url) throws IOException {
        final URLClassLoader sysloader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        final Class<URLClassLoader> sysclass = URLClassLoader.class;
        try {
            final Method method = sysclass.getDeclaredMethod("addURL", new Class[] { URL.class });
            method.setAccessible(true);
            method.invoke(sysloader, new Object[] { url });
        } catch (final Throwable t) {
            t.printStackTrace();
            throw new IOException("Error adding " + url + " to system classloader");
        }
    }

    // LoadExternalJars - Load external jars
    private void LoadExternalJars() {
        try {
            final File[] libs = new File(getDataFolder(), "lib/").listFiles();
            for (final File lib : libs) {
                if (!lib.exists()) {
                    JarUtils.extractFromJar(lib.getName(), lib.getAbsolutePath());
                }
            }
            for (final File lib : libs) {
                if (!lib.exists()) {
                    getLogger().warning(
                            "There was a critical error loading My plugin! Could not find lib: " + lib.getName());
                    Bukkit.getServer().getPluginManager().disablePlugin(this);
                    return;
                }
                AddClassPath(JarUtils.getJarUrl(lib));
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    // onEnable - Runs when the plugin is enabled upon MC server startup
    @Override
    public void onEnable() {
        // Initialize the the leveldb jar
        LoadExternalJars();

        // Register the commands
        this.getCommand("account").setExecutor(new NewAccountCmd());
        this.getCommand("sendcash").setExecutor(new NewTransactionCmd());
        this.getCommand("bank").setExecutor(new GetBalanceCmd());
        this.getCommand("login").setExecutor(new LoginCmd());
    }

    // Run when plugin is disabled
    @Override
    public void onDisable() {
    }
}
