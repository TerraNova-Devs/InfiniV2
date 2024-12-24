package de.mcterranova.infini.rpg.world.functionality.items.control;

import org.bukkit.inventory.ItemStack;
import de.mcterranova.infini.rpg.world.functionality.Attribute;
import de.mcterranova.infini.rpg.world.functionality.items.components.comps.basic.storage.item.ItemCategory;
import de.mcterranova.infini.rpg.world.functionality.items.components.comps.basic.storage.item.ItemClass;
import de.mcterranova.infini.rpg.world.functionality.items.components.comps.basic.storage.item.ItemTier;
import de.mcterranova.infini.rpg.world.functionality.items.enchanting.CustomEnchantment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ItemMask {

    // declare place where it is later

    private final String id;
    private final ItemStack itemStack;
    private final UUID uuid;
    private String displayName;
    private List<Rune> runes;
    private ItemTier tier;
    private final Map< CustomEnchantment, Integer > enchantments = new HashMap<>();
    private final Map< Attribute, Integer > attributes = new HashMap<>();
    private final String description;
    private final ItemClass itemClass;
    private final ItemCategory itemCategory;
    private final int runeSlots;

    public ItemMask(ItemStack itemStack, UUID uuid, String id, String displayname, ItemTier tier, int runeSlots, List<Rune> runes, Map< Attribute, Integer > attributes, Map< CustomEnchantment, Integer > enchantments, String description, ItemClass itemClass, ItemCategory itemCategory )
    {
        this.runeSlots = runeSlots;
        this.id = id;
        this.itemClass = itemClass;
        this.itemCategory = itemCategory;
        this.description = description;
        this.itemStack = itemStack;
        this.uuid = uuid;
        this.displayName = displayname;
        this.runes = runes;
        this.tier = tier;
        this.enchantments.putAll( enchantments );
        this.attributes.putAll( attributes );
    }

    public int getRuneSlots() { return runeSlots; }
    public String getId() { return id; }
    public ItemStack getItem() { return itemStack; }
    public UUID getUuid() { return uuid; }
    public String getDisplayName() { return displayName; }
    public List<Rune> getRunes() { return runes; }
    public ItemTier getTier() { return tier; }
    public Map< CustomEnchantment, Integer > getEnchantments() { return enchantments;}
    public Map< Attribute, Integer > getAttributes() { return attributes; }
    public String getDescription() { return description; }
    public ItemClass getItemClass() { return itemClass; }
    public ItemCategory getItemCategory() { return itemCategory; }

    public void setRunes( List<Rune> runes ) { this.runes = runes; }
    public void setDisplayName( String displayName ) { this.displayName = displayName; }
    public void setTier( ItemTier tier ) { this.tier = tier; }
}
