package de.mcterranova.infini.rpg.world.functionality.items.runecrafting.runes;

import de.mcterranova.infini.rpg.world.functionality.Attribute;
import de.mcterranova.infini.rpg.world.functionality.items.runecrafting.Rune;

import static de.mcterranova.infini.rpg.world.functionality.items.components.ItemAttributes.NONE;
import static de.mcterranova.infini.rpg.world.functionality.spells.Element.UNDEAD;

public class AttributeRune extends Rune {

    private final Attribute attribute;

    public AttributeRune(Attribute attribute) {
        this.attribute = attribute;
    }

    @Override
    public int getValue() {
        int v = 0;
        switch (attribute) {
            case STRENGTH -> {v = 1 * level}
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
