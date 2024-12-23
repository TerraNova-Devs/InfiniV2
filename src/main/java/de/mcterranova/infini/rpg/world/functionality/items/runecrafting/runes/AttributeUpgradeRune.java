package de.mcterranova.infini.rpg.world.functionality.items.runecrafting.runes;

import de.mcterranova.infini.rpg.world.functionality.Attribute;
import de.mcterranova.infini.rpg.world.functionality.items.runecrafting.UpgradeRune;

public class AttributeUpgradeRune extends UpgradeRune {

    private final Attribute attribute;

    public AttributeUpgradeRune(Attribute attribute) {
        this.attribute = attribute;
    }

    @Override
    public int getValue() {
        int v = 0;
        switch (attribute) {
            case STRENGTH -> { v = 1 * 9}
        }

        return 0;
    }

    @Override
    public String getDisplayName()
    {
        String v = "NULL";
        switch ( attribute )
        {
            case STRENGTH -> v = "Rune des Schlächters";
            case CRITICAL_CHANCE -> v = "Rune des Kritikers";
            case CRITICAL_DAMAGE -> v = "Rune der Präzision";
            case DEFENSE -> v = "Cuboid";
        }
        return v;
    }
}
