package de.mcterranova.infini.rpg.world.functionality.items.components;

import de.mcterranova.infini.Infini;
import de.mcterranova.infini.rpg.world.functionality.items.control.ItemMask;
import de.mcterranova.infini.rpg.world.functionality.spells.Element;
import org.bukkit.NamespacedKey;

import java.util.*;

public class ComponentHelper {

    private static NamespacedKey key(String key ) { return new NamespacedKey( Infini.getInstance(), key ); }

    public static CustomComponent register(String id, CustomComponent customComponent) {
        CustomComponent.registerComponent( key( id ), customComponent);
        return customComponent;
    }

    public void runComponentTask(ItemMask itemMask, UUID uuid) {
        for (CustomComponent customComponent : itemMask.getComponents())
            customComponent.run(uuid);
    }

    public Map<CustomComponent, Integer> assignValues(List<CustomComponent> unassigned, List<Object> values) {
        Map<CustomComponent, Integer> map = new HashMap<>();
        int v = 0;
        for (CustomComponent component : unassigned) {
            map.put(component, (Integer)values.get(v));
            v+=1;
        }
        return map;
    }

    public Objects getData(CustomComponent component) {

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
