package de.mcterranova.infini.rpg.world.functionality.items.components.comps.advanced.runes;

import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponentClass;
import de.mcterranova.infini.rpg.world.functionality.items.components.ComponentType;

public class RuneComponentClass extends CustomComponentClass {

    private final RuneType type;

    protected RuneComponentClass(RuneType type) {
        super(ComponentType.RUNE);
        this.type = type;
    }

    @Override
    public String getDisplayName()
    {
        String v = "NULL";
        switch (type)
        {
            case STRENGTH -> v = "Stärke";
            case INTELLIGENCE -> v = "Intelligenz";
            case MIGHT -> v = "Macht";
        }
        return v;
    }
}
