package me.itamar.plugins.mcutils.ingame;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;

public class LocationUtility {

    /**
     * Writes a location to a config file
     * @param location the location that is written
     * @param section the section that this location is written to
     */
    public static void writeLocation(Location location, ConfigurationSection section) {
        section.set("x", location.getX());
        section.set("y", location.getY());
        section.set("z", location.getZ());
        section.set("yaw", location.getYaw());
        section.set("pitch", location.getPitch());
    }

    /**
     * Reads a location from a config file
     * @param world the world that this location is containing
     * @param section the section that this location is found in
     * @return the location that is being read
     */
    public static Location readLocation(World world, ConfigurationSection section) {
        return new Location(world, section.getDouble("x"), section.getDouble("y"), section.getDouble("z"),
                (float) section.getDouble("yaw"), (float) section.getDouble("pitch"));
    }

    /**
     * Matches 2 locations <b>WITHOUT</b> the y-axis.
     * @param locationOne the first location to be matched.
     * @param locationTwo the second location to be matched.
     * @return if the locations match (excluding y)
     */
    public static boolean matchExcludingY(Location locationOne, Location locationTwo) {
        return locationOne.getWorld() == locationTwo.getWorld() &&
                locationOne.getX() == locationTwo.getX() &&
                locationOne.getZ() == locationTwo.getZ();
    }

}
