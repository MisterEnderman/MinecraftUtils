package me.itamar.plugins.mcutils;

import org.bukkit.plugin.java.JavaPlugin;

public final class MinecraftUtils extends JavaPlugin {

    @Override
    public void onEnable() {
        super.onEnable();

        System.out.println("Plugin turned on");
    }

    @Override
    public void onDisable() {
        super.onDisable();

        System.out.println("Plugin turned off");
    }
}
