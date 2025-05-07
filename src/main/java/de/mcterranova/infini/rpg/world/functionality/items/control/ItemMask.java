package de.mcterranova.infini.rpg.world.functionality.items.control;

import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponent;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponentClass;
import org.bukkit.Material;

import java.util.*;

public class ItemMask {

    // declare place where it is later
    public final Map<CustomComponentClass, String> data = new HashMap<>();
    public final Map<CustomComponentClass, Integer> attributes = new HashMap<>();

    public ItemMask(Material material, String id) {
        this.data.put(CustomComponent.ID, id);
        this.data.put(CustomComponent.UUID, UUID.randomUUID().toString());
        this.data.put(CustomComponent.MATERIAL, material.name());
    }

    public UUID getUUID() { return UUID.fromString(this.data.get(CustomComponent.UUID)); }
}
