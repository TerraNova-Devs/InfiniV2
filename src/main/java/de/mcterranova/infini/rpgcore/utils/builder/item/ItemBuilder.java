package de.mcterranova.infini.rpgcore.utils.builder.item;

import de.mcterranova.infini.rpg.utils.enumplaceholders.Slot;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import de.mcterranova.infini.Infini;
import de.mcterranova.infini.rpg.utils.Chat;
import de.mcterranova.infini.rpg.utils.NBTUtils;
import de.mcterranova.infini.rpg.world.functionality.Attribute;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomItemTemplate;
import de.mcterranova.infini.rpg.world.functionality.items.components.ItemCategory;
import de.mcterranova.infini.rpg.world.functionality.items.components.ItemClass;
import de.mcterranova.infini.rpg.world.functionality.items.components.ItemTier;
import de.mcterranova.infini.rpg.world.functionality.items.control.ItemArchive;
import de.mcterranova.infini.rpg.world.functionality.items.control.ItemMask;
import de.mcterranova.infini.rpg.world.functionality.items.enchanting.CustomEnchantment;
import de.mcterranova.infini.rpg.world.functionality.items.reforging.Rune;

import java.util.*;

public class ItemBuilder {

    private final ItemStack item;
    private ItemMeta meta;
    private Material material;
    private int amount = 1;
    private int customModelData;
    private Map< CustomEnchantment, Integer > enchantments = new HashMap<>();
    private String displayName;
    private List<String> lore = new ArrayList<>();
    private boolean isGlowing = false;
    private final ItemArchive archive = ItemArchive.get();
    private ItemTier itemTier;
    private final Map< Attribute, Integer > attributes = new HashMap<>();
    private boolean custom = false;
    private String description;
    private ItemClass itemClass;
    private ItemCategory itemCategory;
    private UUID uuid;
    private String id;
    private final List<Rune> runes = new ArrayList<>();
    private int runeSlots = 0;

    private final NamespacedKey keyTwo = new NamespacedKey(Infini.getInstance(), "attributemodifier");
    private AttributeModifier attributeModifier() {
        return new AttributeModifier(keyTwo, 0d, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND.getGroup());
    }

    private final NBTUtils nbt = new NBTUtils();

    private NamespacedKey key( String key ) {
        return new NamespacedKey( Infini.getInstance(), key );
    }

    public ItemBuilder( Material material ) {
        this.item = new ItemStack(material);
        this.meta = item.hasItemMeta() && item.getItemMeta() != null ? item.getItemMeta() : Bukkit.getItemFactory().getItemMeta( item.getType() );
        this.material = material;
        this.displayName = material.name().toLowerCase();
    }

    public ItemBuilder( Material material, int amount) {
        this.item = new ItemStack(material);
        this.meta = item.hasItemMeta() && item.getItemMeta() != null ? item.getItemMeta() : Bukkit.getItemFactory().getItemMeta(item.getType());
        this.amount = amount;
        this.material = material;
        this.displayName = material.name().toLowerCase();
    }

    public ItemBuilder( Material material, String displayName) {
        this.item = new ItemStack(material);
        this.meta = item.hasItemMeta() && item.getItemMeta() != null ? item.getItemMeta() : Bukkit.getItemFactory().getItemMeta(item.getType());
        this.material = material;
        this.displayName = displayName;
    }

    public ItemBuilder( Material material, int amount, String displayName) {
        this.item = new ItemStack(material, amount);
        this.meta = item.hasItemMeta() && item.getItemMeta() != null ? item.getItemMeta() : Bukkit.getItemFactory().getItemMeta(item.getType());
        this.material = material;
        this.amount = amount;
        this.displayName = displayName;
    }

    public ItemBuilder( CustomItemTemplate item ) {
        this.itemTier = item.getTier();
        this.id = item.name();
        if ( !item.getItemCategory().equals( ItemCategory.MATERIAL  ) )
        {
            this.uuid = UUID.randomUUID();
            nbt.addNBTTag( key( "UUID" ), uuid.toString() );
        }
        this.itemClass = item.getItemClass();
        this.itemCategory = item.getItemCategory();
        this.description = item.getLore();
        this.custom = true;
        this.item = new ItemStack( item.getMaterial() );
        this.meta = Bukkit.getItemFactory().getItemMeta( item.getMaterial() );
        this.material = item.getMaterial();
        this.displayName = item.getName();

        this.lore = new LoreBuilder( item ).addDescription( !item.getLore().equalsIgnoreCase( "null" ) ).build();

        for ( Attribute attribute : item.getAttributes().keySet() ) {
            this.attributes.put( attribute, item.getAttributes().get( attribute ) );
        }

        nbt.addNBTTag( key( "ID" ), item.name() );
    }

    public ItemBuilder( ItemMask item ) {
        this.id = item.getId();
        this.uuid = item.getUuid();
        this.item = new ItemStack( item.getItem().getType() );
        this.meta = Bukkit.getItemFactory().getItemMeta( item.getItem().getType() );
        this.material = item.getItem().getType();
        this.displayName = item.getDisplayName().substring(2);
        this.isGlowing = Boolean.parseBoolean( nbt.getItemNBTString( item.getItem(), "GLOWING" ) );
        this.custom = Boolean.parseBoolean( nbt.getItemNBTString( item.getItem(), "CUSTOM" ) );
        this.lore = new LoreBuilder( item ).addDescription( !item.getDescription().equalsIgnoreCase( "null" ) ).build();
        this.itemTier = ItemTier.valueOf(nbt.getItemNBTString( item.getItem(), "TIER" ));

        // TODO replace this in the item control
        for ( Attribute attribute : item.getAttributes().keySet() ) {
            this.attributes.put( attribute, item.getAttributes().get( attribute ) );
        }

        nbt.addNBTTag( key( "ID" ), id );
        nbt.addNBTTag( key( "UUID" ), uuid.toString() );
    }

    public ItemBuilder addNBTTag( String key, String value )
    {
        nbt.addNBTTag( key( key ), value );
        return this;
    }

    public ItemBuilder setProtected( boolean value )
    {
        nbt.addNBTTag( key( "PROTECTED" ), String.valueOf( value ) );
        return this;
    }

    public ItemBuilder setRune(Rune rune)
    {
        this.runes.add(rune);
        return this;
    }

    public ItemBuilder setRuneSlots(int runeSlots)
    {
        this.runeSlots = runeSlots;
        return this;
    }

    public ItemBuilder tier( ItemTier tier )
    {
        this.itemTier = tier;
        return this;
    }

    public ItemBuilder custom( boolean value )
    {
        this.custom = value;
        return this;
    }

    public ItemBuilder attribute( Attribute attribute, int value )
    {
        this.attributes.put( attribute, value );
        return this;
    }

    public ItemBuilder amount( int amount ) {
        this.amount = amount;
        return this;
    }

    public ItemBuilder amount( int amount, String displayName ) {
        this.amount = amount;
        return this;
    }

    public ItemBuilder material( Material material ) {
        this.material = material;
        return this;
    }

    public ItemBuilder customModelData( int customModelData ) {
        this.customModelData = customModelData;
        return this;
    }

    public ItemBuilder enchant( CustomEnchantment enchantment, int level )
    {
        this.enchantments.put( enchantment, level );
        return this;
    }

    public ItemBuilder enchant( Map< CustomEnchantment, Integer > enchantments ) {
        this.enchantments = enchantments;
        return this;
    }

    public ItemBuilder displayname( String displayname ) {
        this.displayName = Chat.translate( displayname );
        return this;
    }

    public ItemBuilder setGlowing() {
        this.isGlowing = true;
        return this;
    }

    public ItemBuilder setGlowing( boolean v) {
        this.isGlowing = v;
        return this;
    }

    public ItemStack build()
    {
        item.setType( material );
        item.setAmount( amount );
        // TODO replace with archive n shit
        /*
        if ( !enchantments.isEmpty() )
        {
            nbt.addNBTTag( key( "ENCHANTED" ), "true" );
            for ( CustomEnchantment enchantment : enchantments.keySet() )
                nbt.addNBTTag( CustomEnchantment.getID( enchantment ), enchantments.get( enchantment ) );
        }
        
         */
        // TODO replace with archive n shit
        if ( itemTier != null ) {
            nbt.addNBTTag( key( "TIER" ), itemTier.name() );
            this.displayName = itemTier.getColor() + displayName;
        }
        if ( displayName != null )
            meta.setDisplayName( Chat.translate( displayName) );
        if ( !lore.isEmpty() )
            meta.setLore( Chat.translate( lore ) );
        if ( customModelData != 0 )
            meta.setCustomModelData( customModelData );
        if ( isGlowing )
        {
            nbt.addNBTTag( key( "GLOWING" ), String.valueOf( isGlowing ) );
            meta.addEnchant( Enchantment.CHANNELING, 1, true );
        }

        meta.addAttributeModifier(org.bukkit.attribute.Attribute.GENERIC_ATTACK_DAMAGE, attributeModifier());
        meta.addItemFlags( ItemFlag.HIDE_ENCHANTS );
        meta.addItemFlags( ItemFlag.HIDE_ATTRIBUTES );
        meta.addItemFlags( ItemFlag.HIDE_ARMOR_TRIM );
        meta.addItemFlags( ItemFlag.HIDE_ADDITIONAL_TOOLTIP );
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        // TODO replace with archive n shit
        if ( !attributes.isEmpty() )
            for ( Attribute attribute : attributes.keySet() ) {
                nbt.addNBTTag( key( "ATTRIBUTE_" + attribute.name() ), attributes.get( attribute ) );
            }

        if ( custom ) {
            meta.setUnbreakable(true);
            nbt.addNBTTag("CUSTOM", "true");
            archive.add( new ItemMask( item, uuid, id, displayName, itemTier, runeSlots, runes, attributes, enchantments, description, itemClass, itemCategory ) );
        }

        meta = nbt.parseAllItemNBTTags( meta );
        item.setItemMeta( meta );


        return item;
    }
}