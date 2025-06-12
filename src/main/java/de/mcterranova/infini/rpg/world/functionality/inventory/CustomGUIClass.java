package de.mcterranova.infini.rpg.world.functionality.inventory;

import de.mcterranova.infini.rpg.database.content.TableHandler;
import de.mcterranova.infini.rpg.database.TableID;
import de.mcterranova.infini.rpg.database.content.customserialization.CustomSerializable;
import de.mcterranova.infini.rpg.database.content.DatabaseHelper;
import de.mcterranova.infini.rpg.utils.NBTUtils;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponent;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponentClass;
import de.mcterranova.infini.rpg.world.functionality.items.components.comps.ClickAction;
import de.mcterranova.infini.rpg.world.functionality.items.control.ItemManipulator;
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

public abstract class CustomGUIClass implements CustomSerializable{

    private final Map<UUID, GUITitle> opened = new HashMap<>();
    private final int size;
    private final GUITitle title;

    protected CustomGUIClass(int rows, GUITitle title) {
        this.title = title;
        this.size = rows;
    }

    public GUITitle getTitle() {
        return this.title;
    }

    protected boolean isStatic(ItemMask mask) {
        return mask.data.get(CustomComponent.STATIC) != null;
    }

    protected ClickAction getAction(ItemMask mask) {
        if (mask.data.keySet().stream().anyMatch(comp -> comp.getAction() != null))
            return mask.data.keySet().stream().filter(comp -> comp.getAction() != null).findFirst().get().getAction();
        return ClickAction.NONE;
    }

    protected String secretSerialize(InventoryWrapper wrapper) {
        StringBuilder builder = new StringBuilder();
        builder.append(wrapper.contents().size()).append("@").append(wrapper.id()).append("@");
        wrapper.contents().keySet().forEach(pos -> builder.append(pos).append(",").append(wrapper.contents().get(pos).serialize()).append("&"));
        return builder.toString();
    }

    public void saveToDatabase(Inventory inventory, GUITitle title) {
        Map<Integer, ItemMask> contents = new HashMap<>();
        for (int i = 0; i < size; i++) {
            Bukkit.getServer().broadcast(Component.text("CYCLE" + i));
            ItemStack item = inventory.getItem(i);
            if (item == null) {
                contents.put(i, null);
                continue;
            }
            contents.put(i, DatabaseHelper.getItemTemplate(NBTUtils.getNBTTag(item, "ID")));
        }
        TableHandler.insertValue(TableID.INVENTORY_TEMPLATES, title.name(), this.secretSerialize(new InventoryWrapper(title, contents)));
    }

    public static Inventory deserialize(String v) {
        if (v.equals("NULL"))
            return null;
        String[] split = v.split("@");
        Inventory temp = Bukkit.createInventory(null, Integer.parseInt(split[0]), Component.text(split[1]));
        Map<Integer, ItemMask> contents = new HashMap<>();
        Arrays.stream(split[2].split("&")).forEach(string -> {
            String[] split1 = string.split(",");
            contents.put(Integer.parseInt(split1[0]), ItemMask.deserialize(split1[1]));
        });
        contents.keySet().forEach(key -> {
            if (contents.get(key) == null) {
                temp.setItem(key, new ItemStack(Material.AIR));
            } else {
                temp.setItem(key, new ItemManipulator(contents.get(key)).manifest(true, false, (short)1, false));
            }
        });
        return temp;
    }

    public String serialize() {
        return "NULL";
    }

    public String serializeContents() {
        return "NULL";
    }

    public void open(Player player) {
        if (this.opened.containsKey(player.getUniqueId())) {
            Bukkit.getPluginManager().callEvent(new InventoryCloseEvent(player.getOpenInventory()));
            this.opened.remove(player.getUniqueId());
        }
        player.openInventory(Bukkit.createInventory(null, size, Component.text("NO CLASS")));
    }

    public void close(Player player) {
        UUID uuid = player.getUniqueId();
        if (this.opened.containsKey(uuid)) {
            Bukkit.getPluginManager().callEvent(new InventoryCloseEvent(player.getOpenInventory()));
            this.opened.remove(uuid);
        }
    }

    protected Inventory copyInventory(Inventory inventory) {
        Inventory inv = Bukkit.createInventory(null, inventory.getSize());
        inv.setContents(inventory.getContents());
        return inv;
    }

    public void processClick(InventoryClickEvent event) {}

    protected void listPlayer(UUID uuid, GUITitle id) {
        this.opened.put(uuid, id);
    }

    protected void unListPlayer(UUID uuid) { this.opened.remove(uuid); }

    public boolean hasOpened(UUID uuid) {
        return this.opened.containsKey(uuid);
    }

    public GUITitle getOpened(UUID uuid) { return this.opened.get(uuid); }

    private static final HashMap<GUITitle, CustomGUIClass> inventories = new HashMap<>();

    public static void registerComponent( GUITitle title, CustomGUIClass customComponentClass) {
        if ( !inventories.containsKey( title ) )
        {
            inventories.put( title, customComponentClass);
        } else {
            throw new IllegalArgumentException( "Cannot set already-set inventory" );
        }
    }

    public static CustomGUIClass register(GUITitle title, CustomGUIClass inventory) {
        CustomGUIClass.registerComponent(title, inventory);
        return inventory;
    }

    public static CustomGUIClass fromTitle(GUITitle title) {
        return inventories.get(title);
    }
}
