package me.itamar.plugins.mcutils.ingame;

import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ItemBuilder {

    /**
     * Item to be built
     */
    private ItemStack itemStack;

    /**
     * Creates a new ItemBuilder from scratch
     * @param material The material to create the Builder with
     */
    public ItemBuilder(Material material) {
        this(material, 1);
    }

    /**
     * Creates a new ItemBuilder over an existing ItemStack
     * @param itemStack The itemstack to create the Builder with
     */
    public ItemBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    /**
     * Creates a new ItemBuilder from scratch
     * @param material The material to create the Builder with
     * @param amount The amount of items to create
     */
    public ItemBuilder(Material material, int amount) {
        itemStack = new ItemStack(material, amount);
    }

    /**
     * Creates a new ItemBuilder from scratch
     * @param material The material to create the Builder with
     * @param amount The amount of items to create
     * @param durability The durability of the item
     */
    public ItemBuilder(Material material, int amount, short durability) {
        itemStack = new ItemStack(material, amount, durability);
    }

    /**
     * Copies the ItemBuilder and returns a new one
     * @return the new ItemBuilder
     */
    public ItemBuilder clone() {
        return new ItemBuilder(itemStack);
    }

    /**
     * Changes the durability of an existing ItemBuilder
     * @param durability the new durability
     * @return the new ItemBuilder with the durability changed
     */
    public ItemBuilder setDurability(short durability) {
        itemStack.setDurability(durability);
        return this;
    }

    /**
     * Changes the name of the ItemBuilder
     * @param name the new name
     * @return the new ItemBuilder with the name
     */
    public ItemBuilder setName(String name) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    /**
     * Adds a single unsafe enchantment to the ItemBuilder
     * @param enchantment the enchantment to add
     * @param level the level to set
     * @return the new ItemBuilder with the enchantment
     */
    public ItemBuilder addUnsafeEnchantment(Enchantment enchantment, int level) {
        itemStack.addUnsafeEnchantment(enchantment, level);
        return this;
    }

    /**
     * Adds multiple unsafe enchantments to the ItemBuilder
     * @param enchantments the enchantments to add
     * @return the new ItemBuilder with the enchantments
     */
    public ItemBuilder addUnsafeEnchantments(Map<Enchantment, Integer> enchantments) {
        itemStack.addUnsafeEnchantments(enchantments);
        return this;
    }

    /**
     * Adds a safe enchantment to the ItemBuilder
     * @param enchantment the enchantment to add
     * @param level the level to set
     * @return the new ItemBuilder with the enchantment
     */
    public ItemBuilder addEnchantment(Enchantment enchantment, int level) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.addEnchant(enchantment, level, true);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    /**
     * Adds multiple safe enchantments to the ItemBuilder
     * @param enchantments the enchantments to add
     * @return the new ItemBuilder with the enchantments
     */
    public ItemBuilder addEnchantments(Map<Enchantment, Integer> enchantments) {
        itemStack.addEnchantments(enchantments);
        return this;
    }

    /**
     * Removes a single enchantment from the ItemBuilder
     * @param enchantment the enchantment to remove
     * @return the new ItemBuilder without the enchantment removed
     */
    public ItemBuilder removeEnchantment(Enchantment enchantment) {
        itemStack.removeEnchantment(enchantment);
        return this;
    }

    /**
     * Changes the skull owner of the skull
     * @param owner the IGN (in-game name) of the skull owner
     * @return the new ItemBuilder skull
     */
    public ItemBuilder setSkullOwner(String owner) {
        try {
            SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
            skullMeta.setOwner(owner);
            itemStack.setItemMeta(skullMeta);
        } catch (ClassCastException expected) {}
        return this;
    }

    /**
     * Changes the durability of the ItemBuilder to 32767
     * @return the new ItemBuilder with the infinite durability
     */
    public ItemBuilder setInfiniteDurability() {
        itemStack.setDurability(Short.MAX_VALUE);
        return this;
    }

    /**
     * Resets the lore to a new one
     * @param lore the lore to be set
     * @return the new ItemBuilder with the new lore
     */
    public ItemBuilder setLore(String... lore) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(Arrays.asList(lore));
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    /**
     * Resets the lore
     * @param lore the lore to be set
     * @return the new ItemBuilder with the new lore
     */
    public ItemBuilder setLore(List<String> lore) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    /**
     * Removes a lore line from the ItemBuilder's lore
     * @param line the line of lore to remove
     * @return the exact same ItemBuilder if line doesn't exist, otherwise the new ItemBuilder without the lore line removed
     */
    public ItemBuilder removeLoreLine(String line) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lore = new ArrayList<>(itemMeta.getLore());
        if (!lore.contains(line)) {
            return this;
        }

        lore.remove(line);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    /**
     * Removes a lore line from the ItemBuilder
     * @param index the line number to remove
     * @return the new ItemBuilder without the lore line removed
     */
    public ItemBuilder removeLoreLine(int index) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lore = new ArrayList<>(itemMeta.getLore());
        if (index < 0 || index > lore.size()) {
            return this;
        }

        lore.remove(index);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    /**
     * Adds a lore line to the ItemBuilder
     * @param line the lore line to add
     * @return the new ItemBuilder with the lore line
     */
    public ItemBuilder addLoreLine(String line) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lore = new ArrayList<>();
        if (itemMeta.hasLore()) {
            lore = new ArrayList<>(itemMeta.getLore());
        }

        lore.add(line);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    /**
     *
     * @param line the line of lore to add
     * @param pos the position of the lore line
     * @return the new ItemBuilder with the lore line
     * @throws ArrayIndexOutOfBoundsException if pos is out of range
     */
    public ItemBuilder addLoreLine(String line, int pos) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lore = new ArrayList<>(itemMeta.getLore());
        lore.set(pos, line);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    /**
     * Changes the dye color of the ItemBuilder
     * @param color the color to add
     * @return the new ItemBuilder with the color
     */
    public ItemBuilder setDyeColor(DyeColor color) {
        this.itemStack.setDurability(color.getDyeData());
        return this;
    }

    /**
     * Changes the armor color in case it's a leather armor piece
     * @param color the color to add
     * @return the new ItemBuilder with the color
     */
    public ItemBuilder setLeatherArmorColor(Color color) {
        try {
            LeatherArmorMeta itemMeta = (LeatherArmorMeta) itemStack.getItemMeta();
            itemMeta.setColor(color);
            itemStack.setItemMeta(itemMeta);
        } catch (ClassCastException expected) {}
        return this;
    }

    /**
     * Converts an ItemBuilder to an ItemStack
     * @return the ItemStack with all of the ItemBuilder "settings"
     */
    public ItemStack toItemStack() {
        return itemStack;
    }

}
