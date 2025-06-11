package de.mcterranova.infini.rpg.world.functionality.inventory.under;

import de.mcterranova.infini.rpg.database.content.DatabaseHelper;
import de.mcterranova.infini.rpg.utils.NBTUtils;
import de.mcterranova.infini.rpg.world.functionality.inventory.CustomGUIClass;
import de.mcterranova.infini.rpg.world.functionality.inventory.GUITitle;
import de.mcterranova.infini.rpg.world.functionality.inventory.InventoryWrapper;
import de.mcterranova.infini.rpg.world.functionality.items.control.ItemMask;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class PlayerMain extends CustomGUIClass implements Listener {

    private final Inventory inventory;
    private final GUITitle title;
    private final int size;

    public PlayerMain(int rows, GUITitle title) {
        super(rows, title);
        this.size = Math.min((rows * 9), 54);
        this.inventory = Bukkit.createInventory(null, size, Component.text(title.display));
        this.title = title;
        this.inventory.setContents(DatabaseHelper.getInventoryTemplate(title).getContents());
    }

    /*
    @Override
    public void processClick(InventoryClickEvent event) {
        Inventory inventory1 = event.getClickedInventory();
        NBTUtils.getNBTTag(inventory1.getItem(event.getRawSlot()), );
    }

     */

    @Override
    public String serialize() {
        Map<Integer, ItemMask> contents = new HashMap<>();
        for (int i = 0; i <= size; i++) {
            ItemStack item = this.inventory.getItem(i);
            if (item == null) {
                contents.put(i, null);
                continue;
            }
            contents.put(i, DatabaseHelper.getItemTemplate(NBTUtils.getNBTTag(item, "ID")));
        }
        return this.secretSerialize(new InventoryWrapper(this.title, contents));
    }

    @Override
    public void open(Player player) {
        UUID uuid = player.getUniqueId();
        if (this.hasOpened(uuid)) {
            Bukkit.getPluginManager().callEvent(new InventoryCloseEvent(player.getOpenInventory()));
            this.unListPlayer(uuid);
        }
        this.listPlayer(uuid, title);
        if (DatabaseHelper.getPlayerInventory(uuid) == null)
            player.openInventory(this.copyInventory(this.inventory));
        else
            player.openInventory(DatabaseHelper.getPlayerInventory(uuid));
    }
}
