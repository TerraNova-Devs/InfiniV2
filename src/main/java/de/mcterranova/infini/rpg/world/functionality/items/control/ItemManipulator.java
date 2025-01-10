package de.mcterranova.infini.rpg.world.functionality.items.control;

import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponentClass;
import de.mcterranova.infini.rpgcore.utils.builder.item.CustomItemBuilder;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class ItemManipulator {

    private final ItemArchive archive = ItemArchive.get();
    private final ItemMask itemMask;

    public ItemManipulator(ItemMask mask) {
        this.itemMask = mask;
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

    public ItemManipulator updateItem(Player player, int v) {
        ItemStack item = new CustomItemBuilder(itemMask).build();
        if (!player.getItemOnCursor().isEmpty())
            player.setItemOnCursor(item);
        player.getInventory().setItem(v, item);
        return this;
    }

    public void queue() {
        archive.update( itemMask );
    }


    //update itemstack
}
