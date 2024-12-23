package de.mcterranova.infini.rpg.world.functionality.items.components.comps.attributes;

import de.mcterranova.infini.rpg.world.functionality.Attribute;
import de.mcterranova.infini.rpg.world.functionality.items.components.ComponentType;
import de.mcterranova.infini.rpg.world.functionality.items.components.Component;

public class AttributeComponent extends Component {

    protected AttributeComponent(Attribute attribute) {
        super(ComponentType.ATTRIBUTE);
    }
}
