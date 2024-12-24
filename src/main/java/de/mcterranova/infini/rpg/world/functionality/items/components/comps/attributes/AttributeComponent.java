package de.mcterranova.infini.rpg.world.functionality.items.components.comps.attributes;

import de.mcterranova.infini.rpg.world.functionality.Attribute;
import de.mcterranova.infini.rpg.world.functionality.items.components.ComponentType;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponent;

public class AttributeComponent extends CustomComponent {

    private final Attribute attribute;

    public AttributeComponent(Attribute attribute) {
        super(ComponentType.ATTRIBUTE);
        this.attribute = attribute;
    }

    @Override
    public Attribute getAttribute() {
        return attribute;
    }
}
