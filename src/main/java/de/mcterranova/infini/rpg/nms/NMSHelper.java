package de.mcterranova.infini.rpg.nms;

import io.papermc.paper.util.ItemComponentSanitizer;
import net.minecraft.core.component.DataComponentHolder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.Unit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class NMSHelper {

    public static ItemStack getEmptyItem(Material material) {
        // Convert to NMS
        var nmsItem = CraftItemStack.asNMSCopy(new ItemStack(material));
        nmsItem.set(DataComponents.HIDE_ADDITIONAL_TOOLTIP, Unit.INSTANCE);
        nmsItem.set(DataComponents.HIDE_TOOLTIP, Unit.INSTANCE);
        nmsItem.remove(DataComponents.ITEM_NAME);
        nmsItem.remove(DataComponents.CUSTOM_NAME);
        // Convert back to Bukkit
        return CraftItemStack.asBukkitCopy(nmsItem);
    }
}
