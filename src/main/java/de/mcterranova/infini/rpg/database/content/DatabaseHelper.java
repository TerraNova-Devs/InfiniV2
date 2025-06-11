package de.mcterranova.infini.rpg.database.content;

import de.mcterranova.infini.rpg.database.TableID;
import de.mcterranova.infini.rpg.world.functionality.inventory.CustomGUIClass;
import de.mcterranova.infini.rpg.world.functionality.inventory.GUITitle;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponent;
import de.mcterranova.infini.rpg.world.functionality.items.control.ItemMask;
import org.bukkit.inventory.Inventory;

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

    public static Inventory getPlayerInventory(UUID uuid) {
        return CustomGUIClass.deserialize(TableHandler.selectValue(TableID.PLAYER_INVENTORIES, uuid));
    }
}
