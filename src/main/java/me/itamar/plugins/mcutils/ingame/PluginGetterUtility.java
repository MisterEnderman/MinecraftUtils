package me.itamar.plugins.mcutils.ingame;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginGetterUtility {

    /**
     * Gets the Main plugin class of a specific plugin
     * @param pluginName the plugin's name (plugin.yml -> name)
     * @return the Main plugin class of a specific plugin
     */
    public static JavaPlugin getPlugin(String pluginName) {
        return (JavaPlugin) Bukkit.getServer().getPluginManager().getPlugin(pluginName);
    }

}
