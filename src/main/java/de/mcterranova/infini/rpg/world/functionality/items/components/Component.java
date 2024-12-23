package de.mcterranova.infini.rpg.world.functionality.items.components;

import de.mcterranova.infini.rpg.world.functionality.items.enchanting.CustomEnchantment;
import org.bukkit.NamespacedKey;

import java.util.HashMap;

public abstract class Component {
    private final ComponentType type;

    private static final HashMap<NamespacedKey, CustomEnchantment> enchants = new HashMap<>();

    protected Component( ComponentType type )
    {
        this.type = type;
    }
}
