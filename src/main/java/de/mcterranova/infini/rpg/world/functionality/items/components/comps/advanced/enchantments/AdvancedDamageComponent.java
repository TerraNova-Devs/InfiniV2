package de.mcterranova.infini.rpg.world.functionality.items.components.comps.advanced.enchantments;

import de.mcterranova.infini.rpg.world.functionality.items.components.ComponentType;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponent;
import de.mcterranova.infini.rpg.world.functionality.items.enchanting.EnchantmentCategory;
import de.mcterranova.infini.rpg.world.functionality.spells.Element;

public class AdvancedDamageComponent extends CustomComponent {

    private final Element element;

    public AdvancedDamageComponent( Element element ) {
        super(ComponentType.ENCHANTMENT, EnchantmentCategory.WEAPON );
        this.element = element;
    }

    @Override
    public int getAdditiveBonus(int level, Element targetelement) {
        int value = 0;
        switch ( targetelement )
        {
            case NONE -> {
                if (element.equals(targetelement))
                    value = level * 10;
            }
            case UNDEAD, ARACHNID, CUBOID -> {
                if (element.equals(targetelement))
                    value = level * 15;
            }
        }
        return value;
    }

    @Override
    public String getDisplayName()
    {
        String v = "NULL";
        switch ( element )
        {
            case NONE -> v = "Schärfe";
            case UNDEAD -> v = "Bann";
            case ARACHNID -> v = "Nemesis$der$Spinnen";
            case CUBOID -> v = "Cuboid";
        }
        return v;
    }
}
