package de.mcterranova.infini.rpg.world.functionality.builder.mob.control;

import org.bukkit.entity.LivingEntity;
import de.mcterranova.infini.Infini;

import java.util.HashMap;
import java.util.UUID;

public class EntityConnector {

    private final HashMap< UUID, EntityMask > cache;

    public EntityConnector()
    {
        this.cache = new HashMap<>();
    }

    public void add( EntityMask mask )
    {
        cache.put( mask.getUuid(), mask );
    }

    public void remove( EntityMask mask )
    {
        UUID uuid = mask.getUuid();
        if ( cache.get( uuid ) != null )
            cache.remove( uuid );
    }

    public void update( EntityMask mask )
    {
        UUID uuid = mask.getUuid();
        if ( cache.get( uuid ) != null )
            cache.replace( uuid, mask );
    }

    public boolean contains( EntityMask mask )
    {
        return ( cache.get( mask.getUuid() ) != null );
    }

    public EntityMask get( LivingEntity entity )
    {
        return cache.get( entity.getUniqueId() );
    }

    public EntityMask get( UUID uuid )
    {
        return cache.get( uuid );
    }

    public static EntityConnector get() { return Infini.getInstance().getEntityConnector(); }
}
