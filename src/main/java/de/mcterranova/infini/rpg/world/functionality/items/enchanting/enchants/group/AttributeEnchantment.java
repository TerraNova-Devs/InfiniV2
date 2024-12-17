package de.mcterranova.infini.rpg.world.functionality.items.enchanting.enchants.group;

import de.mcterranova.infini.Infini;
import de.mcterranova.infini.rpg.world.functionality.Attribute;
import de.mcterranova.infini.rpg.world.functionality.items.enchanting.CustomEnchantment;
import de.mcterranova.infini.rpg.world.functionality.items.enchanting.EnchantmentCategory;

import java.util.UUID;

public class AttributeEnchantment extends CustomEnchantment {

    Attribute attribute;

    public AttributeEnchantment( Attribute attribute ) {
        super( EnchantmentCategory.ARMOR, EnchantmentCategory.WEAPON, EnchantmentCategory.SHIELD, EnchantmentCategory.TOOL );
        this.attribute = attribute;
    }

    @Override
    public int getAttributeBonus( int value, Attribute attribute )
    {
        return value * 15;
    }

    @Override
    public void run( UUID uuid )
    {
        Infini.getInstance().getServer().broadcastMessage( "FOUND ENCHANTMENT ATTRIBUTE LOLZ");
    }

    @Override
    public String getDisplayName()
    {
        String v = "NULL";
        switch ( attribute )
        {
            case STRENGTH -> v = "Stärke";
            case CRITICAL_DAMAGE -> v =  "Kritisch";
        }
        return v;
    }
}
