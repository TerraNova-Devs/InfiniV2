package de.mcterranova.infini.rpg.world.functionality.items.components.comps.basic;

import de.mcterranova.infini.rpg.world.functionality.Attribute;
import de.mcterranova.infini.rpg.world.functionality.items.components.ComponentType;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponent;

public class BasicAttributeComponent extends CustomComponent {

    private final Attribute attribute;

    public BasicAttributeComponent(Attribute attribute) {
        super(ComponentType.ATTRIBUTE);
        this.attribute = attribute;
    }

    @Override
    public Attribute getAttribute() {
        return attribute;
    }
}
