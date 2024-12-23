package de.mcterranova.infini.rpg.world.functionality.items.runecrafting;

import org.bukkit.NamespacedKey;

import java.util.HashMap;

public abstract class UpgradeRune {

    private static final HashMap<NamespacedKey, UpgradeRune> runes = new HashMap<>();

    public int getValue() { return 0; }

    public String getDisplayName() { return "NULL"; }

    public static void registerRune( NamespacedKey id, UpgradeRune upgradeRune ) {
        runes.put( id, upgradeRune );
    }
}
