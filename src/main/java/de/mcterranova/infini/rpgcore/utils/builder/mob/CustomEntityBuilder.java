package de.mcterranova.infini.rpgcore.utils.builder.mob;

import de.mcterranova.infini.rpg.world.functionality.spells.Element;
import net.md_5.bungee.api.ChatColor;
import org.apache.commons.lang3.text.WordUtils;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import de.mcterranova.infini.rpg.utils.NBTUtils;
import de.mcterranova.infini.rpg.world.entities.mob.control.CustomType;
import de.mcterranova.infini.rpg.world.entities.mob.control.EntityConnector;
import de.mcterranova.infini.rpg.world.entities.mob.control.EntityMask;
import de.mcterranova.infini.rpg.world.entities.loot.CustomLootTable;
import de.mcterranova.infini.rpg.world.functionality.Attribute;

import java.util.HashMap;

public class CustomEntityBuilder {

    private final EntityConnector connector = EntityConnector.get();
    private final NBTUtils nbt = new NBTUtils();
    private final HashMap< Attribute, Integer > attributes = new HashMap<>();
    private final Location location;
    private String placeHolderName;
    private CustomLootTable lootTable;
    private LivingEntity entity;
    private String nameReplacement = "DEFAULT";
    private int health = 100000;
    private int max_health = 100000;
    private boolean hitbox = true;
    private Element element;
    private CustomType type;
    private int level;
    private String oldName = "§c" + WordUtils.capitalizeFully( "DEFAULT" ) + ChatColor.of( "#18ff03" ) + " " + 100 + "§f/" +  ChatColor.of( "#18ff03" ) + 100 + "§7hp";

    // spawn at default location but teleport to desired location afterwards

    private void addDefaultAttributes()
    {
        attributes.put( Attribute.HEALTH, 100 );
        attributes.put( Attribute.MAX_HEALTH, 100 );
        attributes.put( Attribute.DEFENSE, 0 );
        attributes.put( Attribute.STRENGTH, 0 );
    }

    public CustomEntityBuilder( EntityType type, World world )
    {
        this.location = new Location( world, 0, 200, 0 );
        this.entity = ( LivingEntity ) world.spawnEntity( location, type );
        addDefaultAttributes();
    }

    public CustomEntityBuilder( EntityType type, World world, Location location )
    {
        this.location = location;
        this.entity = ( LivingEntity ) world.spawnEntity( location, type );
        addDefaultAttributes();
    }

    /*
    public CustomEntityBuilder( MobTemplate template, World world )
    {
        this.attributes = template.getAttributes();
        this.location = new Location( world, 0, 2000, 0 );
        this.NBT.addNBTTag( "CUSTOM", "true" );
    }
     */

    public CustomEntityBuilder attribute( Attribute attribute, int value )
    {
        if ( this.attributes.containsKey( attribute ) )
        {
            this.attributes.replace( attribute, value );
        } else
            this.attributes.put( attribute, value );
        return this;
    }

    public CustomEntityBuilder name( String name )
    {
        this.nameReplacement = name;
        return this;
    }

    public CustomEntityBuilder element( Element element )
    {
        this.element = element;
        return this;
    }

    public CustomEntityBuilder type( CustomType type )
    {
        this.type = type;
        return this;
    }

    public CustomEntityBuilder level( int v )
    {
        this.level = v;
        return this;
    }

    public CustomEntityBuilder placeHolderName( String name )
    {
        this.placeHolderName = name;
        return this;
    }

    public CustomEntityBuilder health( int v )
    {
        attribute( Attribute.HEALTH, v );
        this.health = v;
        return this;
    }

    public CustomEntityBuilder maxHealth( int v )
    {
        attribute( Attribute.MAX_HEALTH, v );
        this.max_health = v;
        return this;
    }

    public CustomEntityBuilder strength( int v )
    {
        attribute( Attribute.STRENGTH, v );
        return this;
    }

    public CustomEntityBuilder assignLootTable( CustomLootTable v )
    {
        this.lootTable = v;
        return this;
    }

    public CustomEntityBuilder antiKB()
    {
        entity.getAttribute( org.bukkit.attribute.Attribute.GENERIC_KNOCKBACK_RESISTANCE ).setBaseValue( 1.0 );
        return this;
    }

    public EntityMask build()
    {
        nbt.addNBTTag( "CUSTOM", "true" );
        entity = nbt.parseAllEntityNBTTags( entity );

        entity.getEquipment().clear();
        entity.setCustomName( "§c" + nameReplacement + ChatColor.of( "#18ff03" ) + " " + health + "§f/" +  ChatColor.of( "#18ff03" ) + max_health + " §c❤" );
        entity.setCustomNameVisible( true );

        EntityMask mask = new EntityMask( nameReplacement, entity, type, element,level, null, null, attributes, lootTable );
        connector.add( mask );
        return mask;
    }

    public ArmorStand getPlaceHolder()
    {
        ArmorStand placeHolder = ( ArmorStand ) entity;
        placeHolder.setGravity( false );
        placeHolder.setInvulnerable( true );
        placeHolder.setVisible( false );
        placeHolder.setCollidable( false );
        placeHolder.setMarker(true);

        if ( placeHolderName != null )
        {
            placeHolder.setCustomName( placeHolderName );
            placeHolder.setCustomNameVisible( true );
        }

        return placeHolder;
    }

}
