package de.mcterranova.infini.rpg.world.functionality.items.runecrafting;

import de.mcterranova.infini.Infini;
import de.mcterranova.infini.rpg.world.functionality.items.control.ItemMask;
import de.mcterranova.infini.rpg.world.functionality.items.enchanting.CustomEnchantment;
import de.mcterranova.infini.rpg.world.functionality.spells.Element;
import org.bukkit.NamespacedKey;

import java.util.UUID;

public class RuneManager {
    private static NamespacedKey key(String key ) { return new NamespacedKey( Infini.getInstance(), key ); }

    private static void registerPrivate( String id, CustomEnchantment customEnchantment ) {
        CustomEnchantment.registerEnchantment( key( id ), customEnchantment );
    }

    public static CustomEnchantment register( String id, CustomEnchantment customEnchantment ) {
        CustomEnchantment.registerEnchantment( key( id ), customEnchantment );
        return customEnchantment;
    }

    public int getAttributeValues( ItemMask itemMask )
    {
        int value = 0;
        return value;
    }

    public int getAdditiveBonuses( ItemMask itemMask, Element targetElement )
    {
        int value = 0;
        for ( CustomEnchantment enchantment : itemMask.getEnchantments().keySet() )
            value += enchantment.getAdditiveBonus( itemMask.getEnchantments().get( enchantment ), targetElement);
        return value;
    }

    public int getMultiplicativeBonuses( ItemMask itemMask )
    {
        int value = 0;
        for ( CustomEnchantment enchantment : itemMask.getEnchantments().keySet() )
            value += enchantment.getMultiplicativeBonus( itemMask.getEnchantments().get( enchantment ) );
        return value;
    }
}
