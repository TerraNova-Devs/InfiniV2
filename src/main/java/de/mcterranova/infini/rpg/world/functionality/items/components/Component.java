package de.mcterranova.infini.rpg.world.functionality.items.components;

import de.mcterranova.infini.rpg.world.functionality.Attribute;
import de.mcterranova.infini.rpg.world.functionality.items.components.comps.attributes.AttributeComponent;
import de.mcterranova.infini.rpg.world.functionality.items.components.comps.storage.StorageComponent;

public class Component {

    private static final String v = "COMPONENT_";

    public static final CustomComponent BASE_DAMAGE;
    public static final CustomComponent UUID;

    static {
        // AAAAA = ComponentManager.register(v + "AAAAA", new AAAAA );
        BASE_DAMAGE = ComponentManager.register(v + "BASE_DAMAGE", new AttributeComponent(Attribute.DAMAGE));
        UUID = ComponentManager.register(v + "UUID", new StorageComponent());
    }
}
