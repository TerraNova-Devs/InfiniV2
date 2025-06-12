package de.mcterranova.infini.rpg.world.functionality.inventory.under;

import de.mcterranova.infini.rpg.database.content.customserialization.CustomSerializable;
import de.mcterranova.infini.rpg.database.content.DatabaseHelper;
import de.mcterranova.infini.rpg.utils.NBTUtils;
import de.mcterranova.infini.rpg.world.functionality.inventory.CustomGUIClass;
import de.mcterranova.infini.rpg.world.functionality.inventory.GUITitle;
import de.mcterranova.infini.rpg.world.functionality.inventory.InventoryWrapper;
import de.mcterranova.infini.rpg.utils.LoadingStage;
import de.mcterranova.infini.rpg.world.functionality.items.components.comps.ClickAction;
import de.mcterranova.infini.rpg.world.functionality.items.control.ItemArchive;
import de.mcterranova.infini.rpg.world.functionality.items.control.ItemMask;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class PlayerAccessories extends CustomGUIClass implements CustomSerializable {

    private final Inventory inventory;
    private final GUITitle title;
    private final int size;

    public PlayerAccessories(int rows, GUITitle title) {
        super(rows, title);
        this.size = Math.min((rows * 9), 54);
        this.inventory = Bukkit.createInventory(null, size, Component.text(title.display));
        this.title = title;
    }

    @Override
    public void processClick(InventoryClickEvent event) {
        event.setCancelled(true);
        Inventory inv = event.getClickedInventory();
        if (inv == null)
            return;
        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();
        ItemMask mask = DatabaseHelper.getSavedItemOrTemplate(item);
        switch (this.getAction(mask)) {
            case ClickAction.NONE -> {}
            case ClickAction.INVENTORY_CLOSE -> close(player);
            case ClickAction.INVENTORY_REPLACE_ITSELF -> {
                int slot = event.getRawSlot();
                Inventory inv1 = prepareInventory(player, LoadingStage.I);
                ItemStack itemStack = inv1.getItem(slot);
                inv.setItem(slot, itemStack);
            }
            case ClickAction.INVENTORY_LINK -> {}
        }
    }

    private Inventory prepareInventory(Player player, LoadingStage stage) {
        Inventory inv = DatabaseHelper.getInventoryTemplate(title);
        if (stage.equals(LoadingStage.O))
            return inv;
        inv.setItem(13, new ItemStack(Material.POTATO));
        if (stage.equals(LoadingStage.I))
            return inv;
        inv.setItem(15, new ItemStack(Material.IRON_AXE));
        return inv;
    }

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
                ItemMask mask = uuid != null ? ItemArchive.get().get(UUID.fromString(uuid)) : DatabaseHelper.getItemTemplate(NBTUtils.getNBTTag(item, "ID"));
                contents.put(slot, mask);
            }
        });
        return this.secretSerialize(new InventoryWrapper(this.title, contents));
    }

    @Override
    public void open(Player player) {
        UUID uuid = player.getUniqueId();
        if (this.hasOpened(uuid)) {
            close(player);
        }
        this.listPlayer(uuid, title);
        player.openInventory(prepareInventory(player, LoadingStage.FINAL));
    }

    @Override
    public void close(Player player) {
        UUID uuid = player.getUniqueId();
        this.unListPlayer(uuid);
        Bukkit.getPluginManager().callEvent(new InventoryCloseEvent(player.getOpenInventory()));
    }
}
