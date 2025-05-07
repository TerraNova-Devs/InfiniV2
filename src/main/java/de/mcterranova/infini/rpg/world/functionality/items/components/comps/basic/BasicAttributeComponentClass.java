package de.mcterranova.infini.rpg.world.functionality.items.components.comps.basic;

import de.mcterranova.infini.rpg.world.functionality.items.components.Attribute;
import de.mcterranova.infini.rpg.world.functionality.items.components.ComponentType;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponentClass;

public class BasicAttributeComponentClass extends CustomComponentClass {

    private final Attribute attribute;
    private final String color;
    private final String serialized;

    public BasicAttributeComponentClass(String serialized, Attribute attribute, String color) {
        super(ComponentType.ATTRIBUTE);
        this.attribute = attribute;
        this.color = color;
        this.serialized = serialized;
    }


    @Override
    public Attribute getAttribute() {
        return attribute;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String getSerialized() {
        return serialized;
    }
}
