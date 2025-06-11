package de.mcterranova.infini.rpg.utils;

import de.mcterranova.infini.Infini;
import de.mcterranova.infini.rpg.world.functionality.items.control.ItemManipulator;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class NBTUtils {

    private static NamespacedKey key(String key) { return new NamespacedKey(Infini.getInstance(), key); }

    public static ItemStack addNBTTag(ItemStack itemStack, String key, String value) {
        ItemMeta meta = itemStack.getItemMeta();
        meta.getPersistentDataContainer().set(key(key), PersistentDataType.STRING, value);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public static ItemMeta addNBTTag(ItemMeta itemMeta, String key, String value) {
        itemMeta.getPersistentDataContainer().set(key(key), PersistentDataType.STRING, value);
        return itemMeta;
    }

    public static String getNBTTag(ItemStack item, String key) {
        return item.getItemMeta().getPersistentDataContainer().get(key(key), PersistentDataType.STRING);
    }
}
