package de.mcterranova.infini.rpg.world.functionality.items.components;

import de.mcterranova.infini.Infini;
import de.mcterranova.infini.rpg.world.entities.Element;
import de.mcterranova.infini.rpg.world.functionality.items.components.comps.advanced.enchantments.EnchantmentCategory;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public abstract class CustomComponentClass {
    private final ComponentType type;

    private final ArrayList< EnchantmentCategory > enchantmentCategories = new ArrayList<>();

    protected CustomComponentClass(ComponentType type) {
        this.type = type;
    }

    protected CustomComponentClass(ComponentType type, EnchantmentCategory category) {
        this.enchantmentCategories.add(category);
        this.type = type;
    }

    protected CustomComponentClass(ComponentType type, EnchantmentCategory... categories) {
        this.enchantmentCategories.addAll( List.of( categories ) );
        this.type = type;
    }

    public Integer getAssignedRuneSlot() { return 0; }

    public Attribute getAttribute() { return Attribute.NONE; }

    public String getColor() { return "NULL"; }

    public ComponentType getType() { return this.type; }

    public String getDisplayName() { return "NULL"; }

    public String getDeclaration() { return null; }

    public void run(UUID uuid) {}

    public int getMinLevel() { return 1; }

    public int getMaxLevel() { return 1; }

    public int getCost( int level ) { return 1 + level * 10; }

    public int getAdditiveBonus( int level, Element targetelement ) { return 0; }

    public int getAttributeBonus( int level, Attribute attribute ) { return 0; }

    public int getAttributeValue( int level, Attribute attribute ) { return 0; }

    public int getMultiplicativeBonus( int level ) { return 0; }

    public boolean canEnchant( ItemStack itemStack ) { return enchantmentCategories.stream().anyMatch(category -> category.canEnchant( itemStack ) ); }

    public boolean conflictsWith( CustomComponentClass customComponentClass) { return false; }

    private static NamespacedKey key(String key ) { return new NamespacedKey( Infini.getInstance(), key ); }
    private static final HashMap<NamespacedKey, CustomComponentClass> components = new HashMap<>();

    public static void registerComponent( NamespacedKey id, CustomComponentClass customComponentClass) {
        if ( !components.containsKey( id ) )
        {
            components.put( id, customComponentClass);
        } else {
            throw new IllegalArgumentException( "Cannot set already-set enchantment" );
        }
    }

    public static CustomComponentClass getByID(NamespacedKey id ) { return components.get( id ); }

    public static NamespacedKey getID( CustomComponentClass enchantment )
    {
        for ( NamespacedKey key : components.keySet() )
        {
            CustomComponentClass enchant = components.get( key );
            if ( enchant.equals( enchantment ) )
                return key;
        }
        return null;
    }

    public static boolean contains( NamespacedKey id ) {
        return components.get( id ) != null ;
    }

    public static CustomComponentClass register(String id, CustomComponentClass customComponentClass) {
        CustomComponentClass.registerComponent( key( id ), customComponentClass);
        return customComponentClass;
    }
}
