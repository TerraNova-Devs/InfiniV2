package de.mcterranova.infini.rpg.world.functionality.items.enchanting.enchants.group;

import de.mcterranova.infini.rpg.world.functionality.Attribute;
import de.mcterranova.infini.rpg.world.functionality.items.enchanting.CustomEnchantment;
import de.mcterranova.infini.rpg.world.functionality.items.components.comps.advanced.enchantments.EnchantmentCategory;
import de.mcterranova.infini.rpg.world.functionality.spells.Element;

public class ProtectionEnchantment extends CustomEnchantment {

    private final Element element;

    public ProtectionEnchantment( Element element ) {
        super( EnchantmentCategory.WEAPON );
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
}
