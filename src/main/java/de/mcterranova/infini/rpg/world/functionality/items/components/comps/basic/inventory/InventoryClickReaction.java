package de.mcterranova.infini.rpg.world.functionality.items.components.comps.basic.inventory;

import de.mcterranova.infini.rpg.world.functionality.inventory.CustomGUI;
import de.mcterranova.infini.rpg.world.functionality.inventory.CustomGUIClass;
import de.mcterranova.infini.rpg.world.functionality.items.components.ComponentType;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponentClass;
import de.mcterranova.infini.rpg.world.functionality.items.components.comps.RunAction;

import java.util.UUID;

public class InventoryClickReaction extends CustomComponentClass {
    private final RunAction action;
    private final String serialized;

    public InventoryClickReaction(String serialized, RunAction action) {
        super(ComponentType.INVENTORY);
        this.action = action;
        this.serialized = serialized;
    }

    @Override
    public RunAction getAction() {
        return this.action;
    }

    @Override
    public String getSerialized() {
        return serialized;
    }
}
