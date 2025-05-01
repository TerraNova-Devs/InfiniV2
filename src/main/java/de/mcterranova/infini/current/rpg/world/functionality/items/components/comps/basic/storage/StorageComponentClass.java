package de.mcterranova.infini.current.rpg.world.functionality.items.components.comps.basic.storage;

import de.mcterranova.infini.current.rpg.world.functionality.items.components.ComponentType;
import de.mcterranova.infini.current.rpg.world.functionality.items.components.CustomComponentClass;

public class StorageComponentClass extends CustomComponentClass {

    private final String declaration;

    public StorageComponentClass(String declaration) {
        super(ComponentType.STORAGE);
        this.declaration = declaration;
    }

    @Override
    public String getDeclaration() {
        return this.declaration;
    }
}
