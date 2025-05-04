package de.mcterranova.infini.rpg.listeners;

import de.mcterranova.infini.rpg.utils.NBTUtils;
import de.mcterranova.infini.rpg.world.functionality.builder.mob.control.EntityConnector;
import de.mcterranova.infini.rpg.world.functionality.crafting.Crafting;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustByBlockEvent;
import org.bukkit.event.entity.EntityCombustByEntityEvent;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

public class Combust implements Listener {

    @EventHandler
    public void onCombust( EntityDamageEvent e )
    {

        NBTUtils nbt = new NBTUtils();
        Location location = e.getEntity().getLocation();
        World world = location.getWorld();
        Entity entity = e.getEntity();

        if ( !e.getCause().equals( EntityDamageEvent.DamageCause.LAVA ) )
            return;

        if ( !entity.getType().equals( EntityType.ITEM ) )
            return;

        if ( world.getBlockAt( location ).getType().equals( Material.LAVA_CAULDRON ) )
        {
            String id;
            ItemStack item = ( ( Item ) e.getEntity() ).getItemStack();
            if ( nbt.isCustomItem( item ) )
            {
                id = nbt.getItemID( item ) + "/" + item.getAmount();
            } else
            {
                id = item.getType().name() + "/" + item.getAmount();
            }

            world.playSound( location, Sound.ITEM_BUCKET_FILL_LAVA, 1 , 4 );
            Crafting.get().queryCauldronCraft( location , id );
        }
    }

    @EventHandler
    public void onCombust( EntityCombustEvent e )
    {
        EntityConnector connector = EntityConnector.get();
        if ( e instanceof EntityCombustByBlockEvent || e instanceof EntityCombustByEntityEvent )
            return;

        if ( connector.get( e.getEntity().getUniqueId() ) != null )
            e.setCancelled( true );
    }
}
