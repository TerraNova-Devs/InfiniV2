package de.mcterranova.infini.rpg.world.functionality.items.control;

import de.mcterranova.infini.rpg.world.functionality.items.components.Component;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponent;

import java.util.Map;

public class ItemManipulator {

    private final ItemArchive archive = ItemArchive.get();
    private final ItemMask itemMask;
    private final Map<CustomComponent, Object> data;

    public ItemManipulator(ItemMask mask) {
        this.itemMask = mask;
        this.data = this.itemMask.data;
    }

    public ItemManipulator addComponent(CustomComponent component, Object data) {
        this.data.put(component, data);
        return this;
    }

    public ItemManipulator removeComponent(CustomComponent component) {
        this.data.remove(component);
        return this;
    }

    public void queue() {
        if ( archive.contains( itemMask ) )
            archive.update( itemMask );
    }

    //update itemstack
}
