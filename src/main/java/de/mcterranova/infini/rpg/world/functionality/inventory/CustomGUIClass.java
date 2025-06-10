package de.mcterranova.infini.rpg.world.functionality.inventory;

import de.mcterranova.infini.Infini;
import de.mcterranova.infini.rpg.database.TableHandler;
import de.mcterranova.infini.rpg.database.TableID;
import de.mcterranova.infini.rpg.database.content.customserialization.CustomSerializable;
import de.mcterranova.infini.rpg.database.content.templates.TemplateHelper;
import de.mcterranova.infini.rpg.utils.NBTUtils;
import de.mcterranova.infini.rpg.world.functionality.items.control.ItemMask;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import javax.swing.text.StyledEditorKit;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class CustomGUIClass implements CustomSerializable{

    private final Map<Player, String> opened = new HashMap<>();
    private final int size;
    private final String id;

    protected CustomGUIClass(int rows, String id) {
        this.id = id;
        this.size = rows;
    }

    public String getID() {
        return this.id;
    }

    protected String secretSerialize(InventoryWrapper wrapper) {
        StringBuilder builder = new StringBuilder();
        builder.append(wrapper.contents().size()).append("%").append(wrapper.id()).append("%");
        wrapper.contents().keySet().forEach(pos -> {
            builder.append(pos).append(",").append(wrapper.contents().get(pos)).append("$");
        });
        return builder.toString();
    }

    public void saveToDatabase(Inventory inventory, String id) {
    }

    public static Inventory deserialize(String v) {
        String[] split = v.split("%");
        Inventory temp = Bukkit.createInventory(null, Integer.parseInt(split[0]), Component.text(split[1]));
        List<ItemMask> contents;
        Arrays.stream(split[2].split("\\$")).forEach(string -> {
        });
        return null;
    }

    public String serialize() {
        return "NULL";
    }

    public String serializeContents() {
        return "NULL";
    }

    public void open(Player player) {
        if (this.opened.containsKey(player)) {
            Bukkit.getPluginManager().callEvent(new InventoryCloseEvent(player.getOpenInventory()));
            this.opened.remove(player);
        }
        player.openInventory(Bukkit.createInventory(null, size, Component.text("NO CLASS")));
    }

    public void close(Player player) {
        if (this.opened.containsKey(player)) {
            Bukkit.getPluginManager().callEvent(new InventoryCloseEvent(player.getOpenInventory()));
            this.opened.remove(player);
        }
    }

    protected Inventory copyInventory(Inventory inventory) {
        Inventory inv = Bukkit.createInventory(null, inventory.getSize());
        inv.setContents(inventory.getContents());
        return inv;
    }

    public void processClick(InventoryClickEvent event) {}

    protected void listPlayer(Player player, String id) {
        this.opened.put(player, id);
    }

    protected void unListPlayer(Player player) {
        this.opened.remove(player);
    }

    protected boolean hasOpened(Player player) {
        return this.opened.containsKey(player);
    }

    private static NamespacedKey key(String key ) { return new NamespacedKey( Infini.getInstance(), key ); }
    private static final HashMap<NamespacedKey, CustomGUIClass> inventories = new HashMap<>();

    public static void registerComponent( NamespacedKey id, CustomGUIClass customComponentClass) {
        if ( !inventories.containsKey( id ) )
        {
            inventories.put( id, customComponentClass);
        } else {
            throw new IllegalArgumentException( "Cannot set already-set inventory" );
        }
    }

    public static CustomGUIClass register(String id, CustomGUIClass inventory) {
        CustomGUIClass.registerComponent( key( id ), inventory);
        return inventory;
    }

    public static CustomGUIClass fromID(String id) {
        return inventories.get(key(id));
    }
}
