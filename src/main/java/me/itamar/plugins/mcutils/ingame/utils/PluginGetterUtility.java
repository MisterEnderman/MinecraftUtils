package me.itamar.plugins.mcutils.ingame.utils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginGetterUtility {

    public static JavaPlugin getPlugin(String pluginName) {
        return (JavaPlugin) Bukkit.getServer().getPluginManager().getPlugin(pluginName);
    }

}
