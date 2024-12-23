package de.mcterranova.infini.rpg.world.functionality.items.runecrafting;

import de.mcterranova.infini.rpg.world.functionality.Attribute;
import de.mcterranova.infini.rpg.world.functionality.items.runecrafting.runes.AttributeUpgradeRune;

public class RuneLibrary {
    private static final String e = "RUNE_";
    public static final UpgradeRune STRENGTH;

    static {
        STRENGTH = RuneManager.register(e + "STRENGTH", new AttributeUpgradeRune(Attribute.STRENGTH));
    }
}
