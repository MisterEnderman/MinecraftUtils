package me.itamar.plugins.mcutils.ingame;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GUIUtility {

    public static void setFillerGlass(Inventory inventory) {
        for (ItemStack item : inventory) {
            if (item.getType() == Material.AIR) {
                item.setType(Material.GRAY_STAINED_GLASS_PANE);
            }
        }
    }

}
