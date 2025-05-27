package de.mcterranova.infini.rpg.database.content.templates;

import de.mcterranova.infini.rpg.database.TableHandler;
import de.mcterranova.infini.rpg.database.TableID;
import de.mcterranova.infini.rpg.database.content.customserialization.oldSerializer;
import de.mcterranova.infini.rpg.world.functionality.inventory.CustomGUIClass;
import de.mcterranova.infini.rpg.world.functionality.inventory.InventoryWrapper;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponent;
import de.mcterranova.infini.rpg.world.functionality.items.control.ItemMask;
import org.bukkit.inventory.Inventory;

public class TemplateHelper {

    public void saveItemTemplate(ItemMask itemMask) {
        TableHandler.insertValue(TableID.ITEM_TEMPLATES, itemMask.data.get(CustomComponent.ID), itemMask.serialize());
    }

    public ItemMask getItemTemplate(String id) {
        return ItemMask.deserialize(TableHandler.selectValue(TableID.ITEM_TEMPLATES, id));
    }

    public ItemMask getBlank(String id) {
        return oldSerializer.deserializeBlank(TableHandler.selectValue(TableID.ITEM_TEMPLATES, id));
    }

    public void saveInventoryTemplate(CustomGUIClass inventory) {
        TableHandler.insertValue(TableID.ITEM_TEMPLATES, itemMask.data.get(CustomComponent.ID), inventory.serialize());
    }

    public Inventory getInventoryTemplate(String id) {
        return CustomGUIClass.deserialize(TableHandler.selectValue(TableID.ITEM_TEMPLATES, id));
    }

    public static TemplateHelper get() { return new TemplateHelper(); }
}
