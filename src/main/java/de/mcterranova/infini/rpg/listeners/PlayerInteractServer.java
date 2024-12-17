package de.mcterranova.infini.rpg.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import de.mcterranova.infini.rpg.world.entities.mob.control.EntityConnector;
import de.mcterranova.infini.rpg.world.entities.mob.control.Saving;

import java.util.UUID;

public class PlayerInteractServer implements Listener {

    Saving saving = new Saving();

    @EventHandler
    public void onServerJoin( PlayerJoinEvent e )
    {
        UUID uuid = e.getPlayer().getUniqueId();
        if ( saving.loadFromConfig( uuid ) )
            saving.addNew( uuid );
    }

    @EventHandler
    public void onPlayerLeave( PlayerQuitEvent e )
    {
        EntityConnector connector = EntityConnector.get();

        saving.saveToConfig( e.getPlayer().getUniqueId() );
        connector.remove( connector.get( e.getPlayer().getUniqueId() ) );
    }
}
