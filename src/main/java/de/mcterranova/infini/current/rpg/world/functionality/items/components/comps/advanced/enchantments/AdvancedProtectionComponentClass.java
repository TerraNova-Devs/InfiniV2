package de.mcterranova.infini.current.rpg.world.functionality.items.components.comps.advanced.enchantments;

import de.mcterranova.infini.rpg.world.functionality.Attribute;
import de.mcterranova.infini.current.rpg.world.functionality.items.components.ComponentType;
import de.mcterranova.infini.current.rpg.world.functionality.items.components.CustomComponentClass;
import de.mcterranova.infini.rpg.world.functionality.spells.Element;

public class AdvancedProtectionComponentClass extends CustomComponentClass {

    private final Element element;

    public AdvancedProtectionComponentClass(Element element, EnchantmentCategory... categories) {
        super(ComponentType.ENCHANTMENT, categories);
        this.element = element;
    }

    @Override
    public int getAttributeBonus( int level, Attribute attribute ) {
        int value = 0;
        switch ( element )
        {
            case NONE -> value = level * 15;
            case UNDEAD, ARACHNID -> value = level * 20;
        }
        return value;
    }

    @Override
    public String getDisplayName()
    {
        String v = "NULL";
        switch ( element )
        {
            case NONE -> v = "Schutz";
            case UNDEAD -> v = "Schutz$der$Untoten";
            case ARACHNID -> v = "Schutz$der$Spinnen";
        }
        return v;
    }

    @Override
    public String getColor() {
        return "§9";
    }
}
