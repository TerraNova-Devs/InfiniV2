package de.mcterranova.infini.rpg.world.functionality.items.control;

import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponent;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponentClass;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class ItemMask {

    // declare place where it is later
    private ItemStack itemStack;
    public final Map<CustomComponentClass, Object> data = new HashMap<>();

    public ItemMask(ItemStack itemStack, UUID uuid, String id) {
        this.itemStack = itemStack;
        this.data.put(CustomComponent.ID, id);
        this.data.put(CustomComponent.UUID, uuid);
    }

    public ItemStack getItemStack() { return this.itemStack; }
    public UUID getUUID() { return (UUID) this.data.get(CustomComponent.UUID); }

    public void setItemStack(ItemStack itemStack) { this.itemStack = itemStack; }
}
