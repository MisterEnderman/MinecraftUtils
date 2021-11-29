package me.itamar.plugins.mcutils.ingame;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Objects;

public class CommandUtility {

    /**
     * The PluginCommand class. All command classes must extend it.
     */
    public abstract static class PluginCommand implements CommandExecutor {

        private final CommandInfo commandInfo;

        public PluginCommand() {
            commandInfo = getClass().getDeclaredAnnotation(CommandInfo.class);
            Objects.requireNonNull(commandInfo, "Commands must have a @CommandInfo annotation!");
        }

        public CommandInfo getCommandInfo() {
            return commandInfo;
        }

        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if (!commandInfo.permission().isEmpty()) {
                if (!sender.hasPermission(commandInfo.permission())) {
                    sender.sendMessage(Colorize.color("&cYou don't have permission to run this command!"));
                    return true;
                }
            }

            if (commandInfo.requiresPlayer()) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(Colorize.color("&cYou must be a player to run this command!"));
                    return true;
                }

                execute((Player) sender, args);
                return true;
            }

            execute(sender, args);
            return true;
        }

        public void execute(Player player, String[] args) {}
        public void execute(CommandSender sender, String[] args) {}

    }

    /**
     * CommandInfo annotation used for commands. All command classes must have it in its
     * declaration.
     */
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface CommandInfo {
        String name();
        String permission() default "";
        boolean requiresPlayer();
    }

    @CommandInfo(name = "heal", permission = "plugin.admin", requiresPlayer = true)
    private static class ExampleHealCommand extends PluginCommand {

        @Override
        public void execute(Player player, String[] args) {
            player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
            player.sendMessage(Colorize.color("&6Healed!"));
        }

    }

    @CommandInfo(name = "broadcast", requiresPlayer = false)
    private static class ExampleBroadcastCommand extends PluginCommand {

        @Override
        public void execute(CommandSender sender, String[] args) {
            Bukkit.broadcastMessage(stringArrayToString(args));
        }

        private String stringArrayToString(String[] args) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < args.length; i++) {
                stringBuilder.append(args[i]);
                if (i != args.length - 1) {
                    stringBuilder.append(" ");
                }
            }
            return stringBuilder.toString();
        }

    }

}
