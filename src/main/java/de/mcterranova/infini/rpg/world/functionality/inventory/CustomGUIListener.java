package de.mcterranova.infini.rpg.world.functionality.inventory;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class CustomGUIListener implements Listener {

    @EventHandler
    public void onInteract(InventoryClickEvent e) {
        CustomGUIClass gui = CustomGUIClass.fromTitle(GUITitle.getByDisplay(e.getView().title().toString()));
        if (gui == null)
            return;
        gui.processClick(e);
    }
}
