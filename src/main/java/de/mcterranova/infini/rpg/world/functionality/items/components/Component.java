package de.mcterranova.infini.rpg.world.functionality.items.components;

import de.mcterranova.infini.rpg.world.functionality.Attribute;
import de.mcterranova.infini.rpg.world.functionality.items.enchanting.CustomEnchantment;
import de.mcterranova.infini.rpg.world.functionality.items.enchanting.EnchantmentCategory;
import de.mcterranova.infini.rpg.world.functionality.spells.Element;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public abstract class Component {
    private final ComponentType type;

    private static final HashMap<NamespacedKey, Component> components = new HashMap<>();

    private final ArrayList< EnchantmentCategory > enchantmentCategories = new ArrayList<>();

    protected Component( ComponentType type )
    {
        this.type = type;
    }

    protected Component( ComponentType type, EnchantmentCategory category )
    {
        this.enchantmentCategories.add(category);
        this.type = type;
    }

    protected Component( ComponentType type, EnchantmentCategory... categories )
    {
        this.enchantmentCategories.addAll( List.of( categories ) );
        this.type = type;
    }

    public static Component getByID( NamespacedKey id ) { return components.get( id ); }

    public static NamespacedKey getID( CustomEnchantment enchantment )
    {
        for ( NamespacedKey key : components.keySet() )
        {
            Component enchant = components.get( key );
            if ( enchant.equals( enchantment ) )
                return key;
        }
        return null;
    }

    public static boolean contains( NamespacedKey id )
    {
        return components.get( id ) != null ;
    }

    public String getDisplayName() { return "NULL"; }

    public void run( UUID uuid ) {}

    public int getMinLevel() {
        return 1;
    }

    public int getMaxLevel() {
        return 1;
    }

    public int getCost( int level ) { return 1 + level * 10; }

    public int getAdditiveBonus( int level, Element targetelement ) { return 0; }

    public int getAttributeBonus( int level, Attribute attribute ) { return 0; }

    public int getMultiplicativeBonus( int level ) { return 0; }

    public boolean canEnchant( ItemStack itemStack ) { return enchantmentCategories.stream().anyMatch(category -> category.canEnchant( itemStack ) ); }

    public boolean conflictsWith( Component component ) { return false; }

    public static void registerComponent( NamespacedKey id, Component component ) {
        if ( !components.containsKey( id ) )
        {
            components.put( id, component );
        } else {
            throw new IllegalArgumentException( "Cannot set already-set enchantment" );
        }
    }
}
