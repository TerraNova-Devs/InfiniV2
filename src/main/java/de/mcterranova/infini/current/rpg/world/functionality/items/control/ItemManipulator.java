package de.mcterranova.infini.current.rpg.world.functionality.items.control;

import de.mcterranova.infini.current.rpg.world.functionality.items.components.CustomComponentClass;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class ItemManipulator {

    private final ItemArchive archive = ItemArchive.get();
    private final ItemMask itemMask;

    public ItemManipulator(ItemMask mask) {
        this.itemMask = mask;
    }

    public ItemManipulator(Material material, String id) {
        this.itemMask = new ItemMask(new ItemStack(material), id);
    }

    public ItemManipulator configureComponent(CustomComponentClass component, Object data) {
        this.itemMask.data.replace(component, data);
        return this;
    }

    public ItemManipulator addComponent(CustomComponentClass component, Object data) {
        this.itemMask.data.put(component, data);
        return this;
    }

    public ItemManipulator removeComponent(CustomComponentClass component) {
        this.itemMask.data.remove(component);
        return this;
    }

    /*
    public ItemManipulator updateItemExternal(Player player, int v) {
        ItemStack item = new CustomItemBuilder(itemMask).build();
        if (type.equals(InventoryType.PLAYER)) {
            if (!player.getItemOnCursor().isEmpty()) {
                player.setItemOnCursor(item);
                return this;
            }
            player.getInventory().setItem(v, item);
        }
        return this;
    }

    public ItemManipulator updateItem(Player player, InventoryType type) {
        int v = (Integer) this.itemMask.data.get(CustomComponent.LOCATION);
        ItemStack item = new CustomItemBuilder(itemMask).build();
        if (type.equals(InventoryType.PLAYER)) {
            if (!player.getItemOnCursor().isEmpty()) {
                player.setItemOnCursor(item);
                return this;
            }
            player.getInventory().setItem(v, item);
        }
        return this;
    }

     */
    public ItemMask manifest() {
        archive.update( itemMask );
        return this.itemMask;
    }

    public void queue() {
        archive.update( itemMask );
    }


    //update itemstack
}
