package de.mcterranova.infini.rpg.world.functionality.inventory.under;

import com.sk89q.worldedit.regions.selector.EllipsoidRegionSelector;
import de.mcterranova.infini.rpg.database.content.customserialization.CustomSerializable;
import de.mcterranova.infini.rpg.database.content.DatabaseHelper;
import de.mcterranova.infini.rpg.utils.NBTUtils;
import de.mcterranova.infini.rpg.world.functionality.inventory.CustomGUIClass;
import de.mcterranova.infini.rpg.world.functionality.inventory.GUITitle;
import de.mcterranova.infini.rpg.world.functionality.inventory.InventoryWrapper;
import de.mcterranova.infini.rpg.utils.LoadingStage;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponent;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponentClass;
import de.mcterranova.infini.rpg.world.functionality.items.components.comps.ClickAction;
import de.mcterranova.infini.rpg.world.functionality.items.control.ItemArchive;
import de.mcterranova.infini.rpg.world.functionality.items.control.ItemManipulator;
import de.mcterranova.infini.rpg.world.functionality.items.control.ItemMask;
import de.mcterranova.infini.rpg.world.functionality.items.item.ItemCategory;
import de.mcterranova.infini.rpg.world.functionality.items.item.ItemType;
import net.kyori.adventure.text.Component;
import net.minecraft.util.AbortableIterationConsumer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.xml.crypto.Data;
import java.util.*;

public class PlayerAccessories extends CustomGUIClass implements CustomSerializable {

    private final Inventory inventory;
    private final GUITitle title;
    private final int size;
    private final int[] playerContentSlots = {12, 13, 14, 15, 16, 21, 22, 23, 24, 25, 30, 31, 32, 33, 34, 39, 40, 41, 42, 43};

    public PlayerAccessories(int rows, GUITitle title) {
        super(rows, title);
        this.size = Math.min((rows * 9), 54);
        this.inventory = Bukkit.createInventory(null, size, Component.text(title.display));
        this.title = title;
    }

    @Override
    public void processClick(InventoryClickEvent event) {
        event.setCancelled(true);
        Inventory inventory = event.getClickedInventory();
        if (inventory == null)
            return;
        Player player = (Player) event.getWhoClicked();
        ItemStack itemStack = event.getCurrentItem();
        ItemMask itemMask = DatabaseHelper.getSavedItemOrTemplate(itemStack);
        int slot = event.getRawSlot();
        switch (this.getAction(itemMask)) {
            case ClickAction.NONE -> {}
            case ClickAction.INVENTORY_CLOSE -> close(player);
            case ClickAction.INVENTORY_REPLACE_ITSELF -> {
                ItemStack item = event.getCursor();
                if (item.getType().equals(Material.AIR))
                    return;
                if (!itemMask.data.get(CustomComponent.INVENTORY_REPLACEABLE_BY).equals(DatabaseHelper.getSavedItemOrTemplate(item).data.get(CustomComponent.ITEM_TYPE)))
                    return;
                inventory.setItem(slot, item);
            }
            case ClickAction.INVENTORY_LINK -> CustomGUIClass.fromTitle(GUITitle.valueOf(itemMask.data.get(CustomComponent.INVENTORY_LINK))).open(player);
            case ClickAction.INVENTORY_REVERT_TO_DEFAULT -> {
                Inventory inv = prepareInventory(player, LoadingStage.O);
                ItemStack item = inv.getItem(slot);
                inventory.setItem(slot, item);
            }
        }
    }

    private Inventory prepareInventory(Player player, LoadingStage stage) {
        Inventory inv = DatabaseHelper.getInventoryTemplate(title);
        ItemStack green = new ItemManipulator(DatabaseHelper.getInventoryItemTemplate("GREEN_0")).addData(CustomComponent.INVENTORY_REPLACEABLE_BY, ItemType.NECKLACE.name()).manifest(false, false, 1, false);
        ItemStack green1 = new ItemManipulator(DatabaseHelper.getInventoryItemTemplate("GREEN_0")).addData(CustomComponent.INVENTORY_REPLACEABLE_BY, ItemType.BRACELET.name()).manifest(false, false, 1, false);
        ItemStack green2 = new ItemManipulator(DatabaseHelper.getInventoryItemTemplate("GREEN_0")).addData(CustomComponent.INVENTORY_REPLACEABLE_BY, ItemType.RING.name()).manifest(false, false, 1, false);
        ItemStack red = DatabaseHelper.getInventoryItemTemplate("RED_0").build(false, false, 1, false);
        Arrays.stream(playerContentSlots).forEach(slot -> {
            if (slot > 11 && slot < 17) {
                if (true)
                    inv.setItem(slot, green);
                else
                    inv.setItem(slot, red);
            }
            if (slot > 29 && slot < 35) {
                if (true)
                    inv.setItem(slot, green1);
                else
                    inv.setItem(slot, red);
            }
            if (slot > 38 && slot < 44) {
                if (true)
                    inv.setItem(slot, green2);
                else
                    inv.setItem(slot, red);
            }
        });
        if (stage.equals(LoadingStage.O))
            return inv;
        return inv;
    }

    @Override
    public String serializeContents() {
        Map<Integer, ItemMask> contents = new HashMap<>();
        Arrays.stream(playerContentSlots).forEach(slot -> {
            ItemStack item = this.inventory.getItem(slot);
            if (item == null)
                contents.put(slot, null);
            else {
                String uuid = NBTUtils.getNBTTag(item, "UUID");
                ItemMask mask = uuid != null ? ItemArchive.get().get(UUID.fromString(uuid)) : DatabaseHelper.getItemTemplate(NBTUtils.getNBTTag(item, "ID"));
                if (mask.data.get(CustomComponent.ITEM_CATEGORY).contains(ItemCategory.MENU_ITEM.name()))
                    contents.put(slot, null);
                else
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
