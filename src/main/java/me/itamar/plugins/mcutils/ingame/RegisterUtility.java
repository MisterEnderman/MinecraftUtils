package me.itamar.plugins.mcutils.ingame;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;

public class RegisterUtility {

    /**
     * Registers all listeners of a specific plugin
     * @implSpec All the listener classes must follow these requirements:<br>
     * <ul>
     *   <li>Must be inside a package that is inside the plugin package with the name "listeners"</li>
     *   <li>Must have either a default constructor or a constructor with no parameters</li>
     *   <li>Must have its constructor public.</li>
     * </ul>
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

    /**
     * Registers all commands of a specific plugin
     * @implSpec All the listener classes must follow these requirements:<br>
     * <ul>
     *  <li>Must be inside a package that is inside the plugin package with the name "commands"</li>
     *  <li>Must extend CommandUtility.PluginCommand</li>
     *  <li>Must have the annotation CommandUtility.CommandInfo along with its parameters.</li>
     *  <li>Must have either a default constructor or a constructor with no parameters</li>
     *  <li>Must have its constructor public.<br>
     * </ul>
     * @param mainClass the main class name of the plugin<br>
     * @see CommandUtility
     * @see me.itamar.plugins.mcutils.ingame.CommandUtility.PluginCommand
     * @see me.itamar.plugins.mcutils.ingame.CommandUtility.CommandInfo
     */
    public void registerAllCommands(String mainClass) {
        try {
            Class<?> mainClassObject = Class.forName(mainClass);
            String packageName = mainClassObject.getPackage().getName();
            for (Class<? extends CommandUtility.PluginCommand> clazz : new Reflections(packageName + ".commands")
                    .getSubTypesOf(CommandUtility.PluginCommand.class)
            ) {
                CommandUtility.PluginCommand pluginCommand = clazz.getDeclaredConstructor().newInstance();

                Bukkit.getPluginCommand(pluginCommand.getCommandInfo().name()).setExecutor(pluginCommand);
            }
        } catch (ClassNotFoundException |
                NoSuchMethodException |
                InstantiationException |
                IllegalAccessException |
                InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
