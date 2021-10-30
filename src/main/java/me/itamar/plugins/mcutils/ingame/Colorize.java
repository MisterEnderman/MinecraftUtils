package me.itamar.plugins.mcutils.ingame;

import org.bukkit.ChatColor;

public class Colorize {

    /**
     * Translates & symbols inside a message into colors
     * @param message the message that is being modified
     * @return the message with the new colors
     */
    public static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    /**
     * Removes the coloring from a message
     * @param message the message that is being modified
     * @return the message without the colors
     */
    public static String strip(String message) {
        return ChatColor.stripColor(message);
    }

}
