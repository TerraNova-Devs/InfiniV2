package de.mcterranova.infini.rpg.world.functionality.items.components.comps.storage;

import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponent;
import de.mcterranova.infini.rpg.world.functionality.items.components.ComponentType;

public class StorageComponent extends CustomComponent {

    private final Object object;

    public StorageComponent(Object object) {
        super(ComponentType.STORAGE);
        this.object = object;
    }

    @Override
    public Object getStored() {
        return object;
    }
}
