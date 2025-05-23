package de.mcterranova.infini.rpg.world.functionality.items.control;

import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponent;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponentClass;
import de.mcterranova.infini.rpg.world.functionality.items.components.comps.advanced.runes.RuneWrapper;
import org.bukkit.Material;

import java.util.*;

public class ItemMask {

    public final Map<CustomComponentClass, String> data = new HashMap<>();
    public final Map<CustomComponentClass, Integer> attributes = new HashMap<>();
    public final List<RuneWrapper> runes = new ArrayList<>();

    public ItemMask(Material material, String id) {
        this.data.put(CustomComponent.ID, id);
        this.data.put(CustomComponent.MATERIAL, material.name());
    }

    public UUID getUUID() { return UUID.fromString(this.data.get(CustomComponent.UUID)); }
    public void newUUID() { this.data.put(CustomComponent.UUID, UUID.randomUUID().toString()); }
}
