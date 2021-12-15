package me.itamar.plugins.mcutils.ingame;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

public class ActionBarUtility {

    public static void sendActionBarToPlayer(String message, Player player) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(Colorize.color(message)));
    }

}
