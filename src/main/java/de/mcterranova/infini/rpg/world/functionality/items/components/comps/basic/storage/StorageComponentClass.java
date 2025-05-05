package de.mcterranova.infini.rpg.world.functionality.items.components.comps.basic.storage;

import de.mcterranova.infini.rpg.world.functionality.items.components.ComponentType;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponentClass;

public class StorageComponentClass extends CustomComponentClass {

    private final String declaration;
    private final String serialized;

    public StorageComponentClass(String serialized, String declaration) {
        super(ComponentType.STORAGE);
        this.declaration = declaration;
        this.serialized = serialized;
    }

    @Override
    public String getDeclaration() {
        return this.declaration;
    }

    @Override
    public String getSerialized() {
        return serialized;
    }
}
