package de.mcterranova.infini.current.rpg.world.functionality.items.components.comps.basic;

import de.mcterranova.infini.rpg.world.functionality.Attribute;
import de.mcterranova.infini.current.rpg.world.functionality.items.components.ComponentType;
import de.mcterranova.infini.current.rpg.world.functionality.items.components.CustomComponentClass;

public class BasicAttributeComponentClass extends CustomComponentClass {

    private final Attribute attribute;
    private final String color;

    public BasicAttributeComponentClass(Attribute attribute, String color) {
        super(ComponentType.ATTRIBUTE);
        this.attribute = attribute;
        this.color = color;
    }

    @Override
    public Attribute getAttribute() {
        return attribute;
    }

    @Override
    public String getColor() {
        return color;
    }
}
