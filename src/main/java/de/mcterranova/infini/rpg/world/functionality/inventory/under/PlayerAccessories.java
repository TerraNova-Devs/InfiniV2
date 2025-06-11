package de.mcterranova.infini.rpg.world.functionality.inventory.under;

import de.mcterranova.infini.rpg.database.content.customserialization.CustomSerializable;
import de.mcterranova.infini.rpg.database.content.templates.TemplateHelper;
import de.mcterranova.infini.rpg.utils.NBTUtils;
import de.mcterranova.infini.rpg.world.functionality.inventory.CustomGUIClass;
import de.mcterranova.infini.rpg.world.functionality.inventory.InventoryWrapper;
import de.mcterranova.infini.rpg.world.functionality.items.control.ItemArchive;
import de.mcterranova.infini.rpg.world.functionality.items.control.ItemMask;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class PlayerAccessories extends CustomGUIClass implements CustomSerializable {

    private final Inventory inventory;
    private final String id;
    private final int size;

    public PlayerAccessories(int rows, String id) {
        super(rows, id);
        this.size = Math.min((rows * 9), 54);
        this.inventory = Bukkit.createInventory(null, size, Component.text(id));
        this.id = id;
        //this.inventory.setContents(TemplateHelper.getInventoryTemplate(id).getContents());
    }

    /*
    @Override
    public void processClick(InventoryClickEvent event) {
        Inventory inventory1 = event.getClickedInventory();
        NBTUtils.getNBTTag(inventory1.getItem(event.getRawSlot()), );
    }
    */

    @Override
    public String serializeContents() {
        int[] slots = {12, 13, 14, 15, 16, 21, 22, 23, 24, 25, 30, 31, 32, 33, 34, 39, 40, 41, 42, 43};
        Map<Integer, ItemMask> contents = new HashMap<>();
        Arrays.stream(slots).forEach(slot -> {
            ItemStack item = this.inventory.getItem(slot);
            if (item == null) {
                contents.put(slot, null);
            } else {
                String uuid = NBTUtils.getNBTTag(item, "UUID");
                ItemMask mask = uuid != null ? ItemArchive.get().get(UUID.fromString(uuid)) : TemplateHelper.getItemTemplate(NBTUtils.getNBTTag(item, "ID"));
                contents.put(slot, mask);
            }
        });
        return this.secretSerialize(new InventoryWrapper(this.id, contents));
    }

    @Override
    public void open(Player player) {
        if (this.hasOpened(player.getUniqueId())) {
            Bukkit.getPluginManager().callEvent(new InventoryCloseEvent(player.getOpenInventory()));
            this.unListPlayer(player.getUniqueId());
        }
        this.listPlayer(player.getUniqueId(), id);
        player.openInventory();
    }
}
