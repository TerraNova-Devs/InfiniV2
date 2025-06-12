package de.mcterranova.infini.rpg.database.content;

import de.mcterranova.infini.rpg.database.TableID;
import de.mcterranova.infini.rpg.utils.NBTUtils;
import de.mcterranova.infini.rpg.world.functionality.inventory.CustomGUIClass;
import de.mcterranova.infini.rpg.world.functionality.inventory.GUITitle;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponent;
import de.mcterranova.infini.rpg.world.functionality.items.control.ItemMask;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class DatabaseHelper {

    public static void saveItemTemplate(ItemMask itemMask) {
        TableHandler.insertValue(TableID.ITEM_TEMPLATES, itemMask.data.get(CustomComponent.ID), itemMask.serialize());
    }

    public static ItemMask getItemTemplate(String id) {
        return ItemMask.deserialize(TableHandler.selectValue(TableID.ITEM_TEMPLATES, id));
    }

    public static void saveInventoryTemplate(CustomGUIClass inventory) {
        TableHandler.insertValue(TableID.INVENTORY_TEMPLATES, inventory.getTitle().display, inventory.serialize());
    }

    public static Inventory getInventoryTemplate(GUITitle title) {
        return CustomGUIClass.deserialize(TableHandler.selectValue(TableID.ITEM_TEMPLATES, title.name()));
    }

    public static Inventory getPlayerInventory(GUITitle title, UUID uuid) {
        return CustomGUIClass.deserialize(TableHandler.selectValue(TableID.PLAYER_INVENTORIES, title.name() + uuid.toString()));
    }

    public static void savePlayerInventory(CustomGUIClass inventory, UUID uuid) {
        TableHandler.insertValue(TableID.PLAYER_INVENTORIES, inventory.getTitle().name() + uuid.toString(), inventory.serialize());
    }

    public static ItemMask getSavedItemOrTemplate(ItemStack itemStack) {
        String tag = NBTUtils.getNBTTag(itemStack, "UUID");
        if (tag != null) {
            return ItemMask.deserialize(TableHandler.selectValue(TableID.ITEMS, tag));
        }
        return ItemMask.deserialize(TableHandler.selectValue(TableID.ITEM_TEMPLATES, NBTUtils.getNBTTag(itemStack, "ID")));
    }
}
