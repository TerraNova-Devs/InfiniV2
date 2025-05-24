package de.mcterranova.infini.rpg.nms.v1_21_4_R0_1;

import de.mcterranova.infini.rpg.nms.NMSHelper;
import org.bukkit.inventory.ItemStack;

public class NMSHelperImpl implements NMSHelper {

    @Override
    public ItemStack removeDisplayTag(ItemStack bukkitItem) {
        NMSItemStack nmsItem = CraftItemStack.asNMSCopy(bukkitItem);
        NBTTagCompound tag = nmsItem.getTag();
        if (tag != null && tag.hasKey("display")) {
            tag.remove("display");
            nmsItem.setTag(tag);
        }
        return CraftItemStack.asBukkitCopy(nmsItem);
    }
}
