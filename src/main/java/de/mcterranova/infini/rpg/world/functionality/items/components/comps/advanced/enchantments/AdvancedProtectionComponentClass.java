package de.mcterranova.infini.rpg.world.functionality.items.components.comps.advanced.enchantments;

import de.mcterranova.infini.rpg.world.entities.Element;
import de.mcterranova.infini.rpg.world.functionality.items.components.Attribute;
import de.mcterranova.infini.rpg.world.functionality.items.components.ComponentType;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponentClass;

public class AdvancedProtectionComponentClass extends CustomComponentClass {

    private final Element element;
    private final String serialized;

    public AdvancedProtectionComponentClass(String serialized, Element element, EnchantmentCategory... categories) {
        super(ComponentType.ENCHANTMENT, categories);
        this.element = element;
        this.serialized = serialized;
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

    @Override
    public String getSerialized() {
        return serialized;
    }
}
