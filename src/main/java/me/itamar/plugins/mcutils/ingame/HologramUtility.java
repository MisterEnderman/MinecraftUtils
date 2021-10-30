package me.itamar.plugins.mcutils.ingame;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

public class HologramUtility {

    /**
     * Creates a hologram
     * @param location the location of the hologram
     * @param title the title of the hologram
     */
    public static void createHologram(Location location, String title) {
        World world = location.getWorld();
        if (world == null) return;

        ArmorStand armorStand = (ArmorStand) world.spawnEntity(location, EntityType.ARMOR_STAND);

        armorStand.setArms(false);
        armorStand.setGravity(false);
        armorStand.setVisible(false);
        armorStand.setCustomName(title);
        armorStand.setCustomNameVisible(true);
    }

}
