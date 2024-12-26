package de.mcterranova.infini.rpg.world.functionality.items.components.comps.basic.storage;

import de.mcterranova.infini.rpg.world.functionality.items.components.ComponentType;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponent;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class StorageComponent extends CustomComponent {

    private final String declaration;

    public StorageComponent(String declaration) {
        super(ComponentType.STORAGE);
        this.declaration = declaration;
    }

    @Override
    public String getDeclaration() {
        return this.declaration;
    }
}
