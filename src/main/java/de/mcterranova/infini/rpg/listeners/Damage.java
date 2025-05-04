package de.mcterranova.infini.rpg.listeners;

import de.mcterranova.infini.rpg.utils.InteractHelper;
import de.mcterranova.infini.rpg.utils.NBTUtils;
import de.mcterranova.infini.rpg.world.functionality.builder.mob.control.EntityConnector;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import de.mcterranova.infini.Infini;
import de.mcterranova.infini.rpg.world.functionality.builder.mob.control.EntityManipulator;
import de.mcterranova.infini.rpg.world.entities.damaging.Attack;

public class Damage implements Listener {

    InteractHelper helper = new InteractHelper();
    NBTUtils nbt = new NBTUtils();

    @EventHandler
    public void onInteract( PlayerInteractEntityEvent e )
    {
        if ( helper.rightClickCustomEntity( e ) )
        {
            LivingEntity entity = ( LivingEntity) e.getRightClicked();

            new EntityManipulator( entity )
                    .equip( new ItemStack( Material.DIAMOND_CHESTPLATE ), EquipmentSlot.CHEST )
                    .kill()
                    .queue();
        }
    }

    @EventHandler
    public void onDamage( EntityDamageByEntityEvent e )
    {
        EntityConnector connector = EntityConnector.get();
        LivingEntity entity = ( LivingEntity ) e.getEntity();

        if ( nbt.checkForEntityNBTData( entity ) )
        {
            // more checks
            e.setDamage( 0 );
            new Attack( 500, connector.get( ( LivingEntity ) e.getDamager() ), connector.get( entity ) )
                    .setAOE(5, e.getEntity().getLocation())
                    .attack();
            Infini.getInstance().getServer().broadcastMessage( "this mob has some nbt data");
            Infini.getInstance().getServer().broadcastMessage( "" + nbt.getEntityNBTTags( entity ) );
        } else
        {
            Infini.getInstance().getServer().broadcastMessage( "this mob dont has nbt data" );
        }
    }
}
