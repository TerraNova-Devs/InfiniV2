package de.mcterranova.infini.rpg.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import de.mcterranova.infini.rpg.utils.oldNBTUtils;

public class InventoryListener implements Listener {

    @EventHandler
    public void onInventoryInteract( InventoryClickEvent e )
    {
        oldNBTUtils nbt = new oldNBTUtils();

        Player p = ( Player ) e.getWhoClicked();
        ItemStack item = e.getCursor();

        if ( nbt.isItemProtected( item ) )
            e.setCancelled( true );
        // implement GUI working with custom NBT tags out of
    }
}
