package de.mcterranova.infini.rpg.nms;

import de.mcterranova.infini.rpg.utils.NBTUtils;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponent;
import io.papermc.paper.util.ItemComponentSanitizer;
import net.minecraft.core.component.DataComponentHolder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.Unit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class NMSHelper {

    public static ItemStack getEmptyItem(Material material, String id) {
        // Convert to NMS
        var nmsItem = CraftItemStack.asNMSCopy(new ItemStack(material));
        nmsItem.set(DataComponents.HIDE_TOOLTIP, Unit.INSTANCE);
        ItemStack itemStack = CraftItemStack.asBukkitCopy(nmsItem);
        itemStack = NBTUtils.addNBTTag(itemStack, "ID", id);
        return itemStack;
    }
}
