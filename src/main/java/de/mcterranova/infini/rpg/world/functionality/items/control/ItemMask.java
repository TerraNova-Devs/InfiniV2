package de.mcterranova.infini.rpg.world.functionality.items.control;

import de.mcterranova.infini.rpg.world.functionality.items.components.Component;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class ItemMask {

    // declare place where it is later
    private ItemStack itemStack;
    public final Map<CustomComponent, Object> data = new HashMap<>();

    public ItemMask(ItemStack itemStack, UUID uuid, String id, String displayName) {
        this.itemStack = itemStack;
        this.data.put(Component.ID, id);
        this.data.put(Component.UUID, uuid);
        this.data.put(Component.DISPLAYNAME, displayName);
    }

    public ItemStack getItemStack() { return this.itemStack; }
    public UUID getUUID() { return (UUID) this.data.get(Component.UUID); }
    public String getDisplayName() { return (String) this.data.get(Component.DISPLAYNAME); }

    public void setItemStack(ItemStack itemStack) { this.itemStack = itemStack; }
}
