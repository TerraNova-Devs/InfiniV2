package de.mcterranova.infini.rpg.world.functionality.items.components.comps.basic.storage;

import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponent;
import de.mcterranova.infini.rpg.world.functionality.items.components.ComponentType;

public class StorageComponent extends CustomComponent {

    private final StorageDeclaration declaration;

    public StorageComponent(StorageDeclaration declaration) {
        super(ComponentType.STORAGE);
        this.declaration = declaration;
    }

    @Override
    public StorageDeclaration getData() {
        return this.declaration;
    }
}
