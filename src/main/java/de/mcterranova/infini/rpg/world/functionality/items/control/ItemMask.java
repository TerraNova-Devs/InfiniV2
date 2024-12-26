package de.mcterranova.infini.rpg.world.functionality.items.control;

import de.mcterranova.infini.rpg.world.functionality.items.components.Component;
import de.mcterranova.infini.rpg.world.functionality.items.components.ComponentHelper;
import de.mcterranova.infini.rpg.world.functionality.items.components.ComponentType;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponent;
import org.bukkit.inventory.ItemStack;
import de.mcterranova.infini.rpg.world.functionality.Attribute;
import de.mcterranova.infini.rpg.world.functionality.items.components.comps.basic.storage.item.ItemCategory;
import de.mcterranova.infini.rpg.world.functionality.items.components.comps.basic.storage.item.ItemClass;
import de.mcterranova.infini.rpg.world.functionality.items.components.comps.basic.storage.item.ItemTier;
import de.mcterranova.infini.rpg.world.functionality.items.enchanting.CustomEnchantment;

import java.util.*;

public class ItemMask {

    // declare place where it is later
    private final ComponentHelper helper = new ComponentHelper();
    private final String id;
    private final ItemStack itemStack;
    private final UUID uuid;
    private String displayName;
    private ItemTier tier;
    private String description;
    private ItemClass itemClass;
    private ItemCategory itemCategory;
    private int runeSlots;
    private Map<CustomComponent, Object> data = new HashMap<>();
    private Map<CustomComponent, Integer> enchantments = new HashMap<>();
    private Map<CustomComponent, Integer> runes = new HashMap<>();
    private Map<CustomComponent, Integer> attributes = new HashMap<>();

    public ItemMask(ItemStack itemStack, UUID uuid, String id, String displayName)
    {
        this.itemStack = itemStack;
        this.id = id;
        this.uuid = uuid;
        this.displayName = displayName;
    }
    public String getId() { return id; }
    public ItemStack getItem() { return itemStack; }
    public UUID getUuid() { return uuid; }
    public String getDisplayName() { return displayName; }
    public Map<CustomComponent, Object> getData() { return data;}
    public Map<CustomComponent, Integer> getEnchantments() { return enchantments;}
    public Map<CustomComponent, Integer> getAttributes() { return attributes; }

    public void setDescription(String description) { this.description = description; }
    public void setDisplayName( String displayName ) { this.displayName = displayName; }
    public void setTier( ItemTier tier ) { this.tier = tier; }
    public void setRunes( Map<CustomComponent, Integer> runes ) { this.runes = runes; }
    public void setEnchantments( Map<CustomComponent, Integer> runes ) { this.runes = runes; }
    public void setAttributes( Map<CustomComponent, Integer> runes ) { this.runes = runes; }
}
