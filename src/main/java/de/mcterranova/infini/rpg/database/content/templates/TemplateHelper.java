package de.mcterranova.infini.rpg.database.content.templates;

import de.mcterranova.infini.rpg.database.TableHandler;
import de.mcterranova.infini.rpg.database.TableID;
import de.mcterranova.infini.rpg.database.content.customserialization.Serializer;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponent;
import de.mcterranova.infini.rpg.world.functionality.items.control.ItemMask;

public class TemplateHelper {

    public void saveTemplate(ItemMask itemMask) {
        TableHandler.insertValue(TableID.ITEM_TEMPLATES, itemMask.data.get(CustomComponent.ID), Serializer.serializeItemMask(itemMask));
    }

    public ItemMask getTemplate(String id) {
        return Serializer.deserialize(TableHandler.selectValue(TableID.ITEM_TEMPLATES, id));
    }

    public ItemMask getBlank(String id) {
        return Serializer.deserializeBlank(TableHandler.selectValue(TableID.ITEM_TEMPLATES, id));
    }

    public static TemplateHelper get() { return new TemplateHelper(); }
}
