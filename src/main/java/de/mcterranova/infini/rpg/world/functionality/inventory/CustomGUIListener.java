package de.mcterranova.infini.rpg.world.functionality.inventory;

import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;

public class CustomGUIListener implements Listener {

    @EventHandler
    public void onInteract(InventoryClickEvent e) {
        CustomGUIClass gui = CustomGUIClass.fromID(e.getView().title().toString().toUpperCase());
        if (gui == null)
            return;
        gui.processClick(e);
    }
}
