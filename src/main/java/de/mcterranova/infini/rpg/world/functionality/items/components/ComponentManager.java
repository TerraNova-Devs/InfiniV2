package de.mcterranova.infini.rpg.world.functionality.items.components;

import de.mcterranova.infini.Infini;
import de.mcterranova.infini.rpg.world.functionality.items.control.ItemMask;
import de.mcterranova.infini.rpg.world.functionality.spells.Element;
import org.bukkit.NamespacedKey;

import java.util.UUID;

public class ComponentManager {

    private static NamespacedKey key(String key ) { return new NamespacedKey( Infini.getInstance(), key ); }

    public static CustomComponent register(String id, CustomComponent customComponent) {
        CustomComponent.registerComponent( key( id ), customComponent);
        return customComponent;
    }

    public void runComponentTask(ItemMask itemMask, UUID uuid )
    {
        for ( CustomComponent customComponent : itemMask.getEnchantments().keySet() )
            customComponent.run( uuid );
    }

    public int getAdditiveBonus( ItemMask itemMask, Element targetElement )
    {
        int value = 0;
        for ( CustomComponent customComponent : itemMask.getEnchantments().keySet() )
            value += customComponent.getAdditiveBonus( itemMask.getEnchantments().get(customComponent), targetElement);
        return value;
    }

    public int getMultiplicativeBonus( ItemMask itemMask )
    {
        int value = 0;
        for ( CustomComponent customComponent : itemMask.getEnchantments().keySet() )
            value += customComponent.getMultiplicativeBonus( itemMask.getEnchantments().get(customComponent) );
        return value;
    }
}
