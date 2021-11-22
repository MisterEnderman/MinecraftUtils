package me.itamar.plugins.mcutils.ingame;

import dev.jcsoftware.jscoreboards.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public class ScoreboardUtility {

    /**
     * Creates a new Global Function-Based Scoreboard.<br>
     * A Global Scoreboard - a scoreboard with the same information shown to all players.
     * @param title The title of the scoreboard.
     * @param lines The lines of the scoreboard.
     * @return The new scoreboard.
     */
    public static JGlobalScoreboard makeGlobalScoreboard(String title, List<String> lines) {
        JGlobalScoreboard scoreboard = new JGlobalScoreboard(
                () -> title,
                () -> lines
        );
        scoreboard.updateScoreboard();
        return scoreboard;
    }

    /**
     * Creates a new Global Method-Based Scoreboard.<br>
     * A Global Scoreboard - a scoreboard with the same information shown to all players.
     * @param title The title of the scoreboard.
     * @param lines The lines of the scoreboard.
     * @return The new scoreboard.
     */
    public static JGlobalMethodBasedScoreboard makeGlobalMethodBasedScoreboard(String title, List<String> lines) {
        JGlobalMethodBasedScoreboard scoreboard = new JGlobalMethodBasedScoreboard();
        scoreboard.setTitle(title);
        scoreboard.setLines(lines);
        return scoreboard;
    }

    /**
     * Creates a new Per Player Function-Based Scoreboard.<br>
     * A Per Player Scoreboard - a scoreboard with the information differ based on the player it's
     * being shown to.
     * @param title The title of the scoreboard.
     * @param lines The lines of the scoreboard.
     * @return The new scoreboard.
     */
    public static JPerPlayerScoreboard makePerPlayerScoreboard(String title, List<String> lines) {
        JPerPlayerScoreboard scoreboard = new JPerPlayerScoreboard(
                player -> title,
                player -> lines
        );
        scoreboard.updateScoreboard();
        return scoreboard;
    }

    /**
     * Creates a new Per Player Method-Based Scoreboard.<br>
     * A Per Player Scoreboard - a scoreboard with the information differ based on the player it's
     * being shown to.
     * @param title The title of the scoreboard.
     * @param lines The lines of the scoreboard.
     * @return The new scoreboard.
     */
    public static JPerPlayerMethodBasedScoreboard makePerPlayerMethodBasedScoreboard(Player player, String title, List<String> lines) {
        JPerPlayerMethodBasedScoreboard scoreboard = new JPerPlayerMethodBasedScoreboard();
        scoreboard.setTitle(player, title);
        scoreboard.setLines(player, lines);
        return scoreboard;
    }

    /**
     * Adds all players on the server to a specific scoreboard.
     * @apiNote New Players that join the server after this method is called won't be
     * added to the scoreboard.
     * @param scoreboard The scoreboard the players are being added to.
     */
    public static void addAllPlayersToScoreboard(JScoreboard scoreboard) {
        Bukkit.getOnlinePlayers().forEach(scoreboard::addPlayer);
    }

    /**
     * Creates a new Team in a specific Scoreboard.
     * @param name The name of the team.
     * @param displayName The display name of the team.
     * @param teamColor The team color.
     * @param scoreboard The scoreboard that owns the created team.
     * @return The new team.
     */
    public static JScoreboardTeam makeTeam(String name, String displayName, ChatColor teamColor, JScoreboard scoreboard) {
        return scoreboard.createTeam(name, displayName, teamColor);
    }

    /**
     * Adds a player to a specific Team.
     * @param player The player to be added to the team.
     * @param team The team that the player is being added to.
     */
    public static void addPlayerToTeam(Player player, JScoreboardTeam team) {
        team.addPlayer(player);
    }

    /**
     * Removes a player from a specific Team.
     * @param player The player to be removed from the team.
     * @param team The team that the player is being removed from.
     */
    public static void removePlayerFromTeam(Player player, JScoreboardTeam team) {
        team.removePlayer(player);
    }

    /**
     * Removes a specific scoreboard. This means that all players, entities and teams
     * that the scoreboard has/owns will be removed from it.
     * @param scoreboard The scoreboard that is being removed.
     */
    public static void removeScoreboard(JScoreboard scoreboard) {
        scoreboard.destroy();
    }

}
