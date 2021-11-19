package me.itamar.plugins.mcutils.ingame;

import me.itamar.plugins.mcutils.MinecraftUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;

public class ListenerRegisterUtility {

    /**
     * Registers all listeners of a specific plugin
     * @implSpec All the listener classes must be inside a package called "listeners"
     * <b>AND</b> must have either a default constructor or a constructor with no parameters
     * <b>AND</b> the constructor must be public.
     * @param mainClass the main class name of the plugin
     */
    public static void registerAllListeners(String mainClass) {
        try {
            Class<?> mainClassObject = Class.forName(mainClass);
            String packageName = mainClassObject.getPackage().getName();

            for (Class<?> clazz : new Reflections(packageName + ".listeners")
                    .getSubTypesOf(Listener.class)
            ) {
                Listener listener = (Listener) clazz.getDeclaredConstructor().newInstance();
                Bukkit.getServer().getPluginManager().registerEvents(
                        listener,
                        (Plugin) mainClassObject
                        .getDeclaredConstructor()
                        .newInstance()
                );
            }
        } catch (ClassNotFoundException |
                NoSuchMethodException |
                InvocationTargetException |
                InstantiationException |
                IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
