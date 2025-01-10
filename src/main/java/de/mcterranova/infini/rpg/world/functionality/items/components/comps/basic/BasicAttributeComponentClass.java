package de.mcterranova.infini.rpg.world.functionality.items.components.comps.basic;

import de.mcterranova.infini.rpg.world.functionality.Attribute;
import de.mcterranova.infini.rpg.world.functionality.items.components.ComponentType;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponentClass;

public class BasicAttributeComponentClass extends CustomComponentClass {

    private final Attribute attribute;

    public BasicAttributeComponentClass(Attribute attribute) {
        super(ComponentType.ATTRIBUTE);
        this.attribute = attribute;
    }

    @Override
    public Attribute getAttribute() {
        return attribute;
    }
}
