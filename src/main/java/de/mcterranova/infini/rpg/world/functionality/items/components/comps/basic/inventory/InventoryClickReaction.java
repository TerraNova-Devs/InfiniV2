package de.mcterranova.infini.rpg.world.functionality.items.components.comps.basic.inventory;

import de.mcterranova.infini.rpg.world.functionality.items.components.ComponentType;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponentClass;
import de.mcterranova.infini.rpg.world.functionality.items.components.comps.ClickAction;

public class InventoryClickReaction extends CustomComponentClass {
    private final ClickAction action;
    private final String serialized;

    public InventoryClickReaction(String serialized, ClickAction action) {
        super(ComponentType.INVENTORY);
        this.action = action;
        this.serialized = serialized;
    }

    @Override
    public ClickAction getAction() {
        return this.action;
    }

    @Override
    public String getSerialized() {
        return serialized;
    }
}
