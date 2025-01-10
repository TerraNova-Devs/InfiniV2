package de.mcterranova.infini.rpg.world.functionality.items.control;

import org.bukkit.inventory.ItemStack;
import de.mcterranova.infini.Infini;
import de.mcterranova.infini.rpg.utils.NBTUtils;

import java.util.HashMap;
import java.util.UUID;

public class ItemArchive {

    private final HashMap< UUID, ItemMask > cache;
    private final NBTUtils nbt = new NBTUtils();

    public ItemArchive()
    {
        this.cache = new HashMap<>();
    }

    public void add( ItemMask mask )
    {
        cache.put( mask.getUUID(), mask );
    }

    public void remove( ItemMask mask )
    {
        cache.remove( mask.getUUID() );
    }

    public void update( ItemMask mask ) {
        cache.putIfAbsent(mask.getUUID(), mask);
        UUID uuid = mask.getUUID();
        cache.replace( uuid, mask );
    }

    public boolean contains( ItemMask mask ) { return ( cache.get( mask.getUUID() ) != null ); }

    public ItemMask get( ItemStack itemStack ) { return cache.get( UUID.fromString( nbt.getItemNBTString( itemStack, "UUID" ) ) ); }

    public ItemMask get( UUID uuid )
    {
        return cache.get( uuid );
    }

    public static ItemArchive get() { return Infini.getInstance().getItemArchive(); }
}
