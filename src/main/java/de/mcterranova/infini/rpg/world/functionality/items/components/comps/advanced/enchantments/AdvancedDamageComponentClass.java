package de.mcterranova.infini.rpg.world.functionality.items.components.comps.advanced.enchantments;

import de.mcterranova.infini.rpg.world.entities.Element;
import de.mcterranova.infini.rpg.world.functionality.items.components.ComponentType;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponentClass;

public class AdvancedDamageComponentClass extends CustomComponentClass {

    private final Element element;
    private final String serialized;

    public AdvancedDamageComponentClass(String serialized, Element element, EnchantmentCategory... categories) {
        super(ComponentType.ENCHANTMENT, categories);
        this.element = element;
        this.serialized = serialized;
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
            case ARACHNID -> v = "Nemesis%der%Spinnen";
            case CUBOID -> v = "Cuboid";
        }
        return v;
    }

    @Override
    public String getColor() {
        return "§9";
    }

    @Override
    public String getSerialized() {
        return serialized;
    }
}
