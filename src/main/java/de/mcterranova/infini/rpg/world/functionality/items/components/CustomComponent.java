package de.mcterranova.infini.rpg.world.functionality.items.components;

import de.mcterranova.infini.rpg.world.entities.Element;
import de.mcterranova.infini.rpg.world.functionality.items.components.comps.advanced.enchantments.AdvancedAttributeComponentClass;
import de.mcterranova.infini.rpg.world.functionality.items.components.comps.advanced.enchantments.AdvancedDamageComponentClass;
import de.mcterranova.infini.rpg.world.functionality.items.components.comps.basic.BasicAttributeComponentClass;
import de.mcterranova.infini.rpg.world.functionality.items.components.comps.basic.storage.StorageComponentClass;
import de.mcterranova.infini.rpg.world.functionality.items.components.comps.advanced.enchantments.EnchantmentCategory;

public class CustomComponent {
    // public static final CustomComponentClass AAAAA;
    public static final CustomComponentClass BASE_DAMAGE;
    public static final CustomComponentClass BASE_STRENGTH;
    public static final CustomComponentClass BASE_HEALTH;
    public static final CustomComponentClass BASE_DEFENSE;
    public static final CustomComponentClass BASE_CRITICAL_DAMAGE;
    public static final CustomComponentClass BASE_CRITICAL_CHANCE;
    public static final CustomComponentClass BASE_INTELLIGENCE;

    public static final CustomComponentClass UUID;
    public static final CustomComponentClass ID;
    public static final CustomComponentClass DESCRIPTION;
    public static final CustomComponentClass RUNE_SLOTS;
    public static final CustomComponentClass ITEM_CLASS;
    public static final CustomComponentClass ITEM_TIER;
    public static final CustomComponentClass ITEM_TYPE;
    public static final CustomComponentClass ITEM_CATEGORY;
    public static final CustomComponentClass MATERIAL;
    public static final CustomComponentClass DISPLAY_NAME;

    public static final CustomComponentClass ADVANCED_DAMAGE_ARACHNID;
    public static final CustomComponentClass ADVANCED_DAMAGE_CUBOID;
    public static final CustomComponentClass ADVANCED_DAMAGE_ALL;
    public static final CustomComponentClass ADVANCED_DAMAGE_UNDEAD;
    public static final CustomComponentClass ADVANCED_ATTRIBUTE_STRENGTH;

    static {
        // AAAAA = CustomComponentClass.register("AAAAA", new AAAAA );
        BASE_DAMAGE = CustomComponentClass.register("BASE_DAMAGE", new BasicAttributeComponentClass("AAA", Attribute.DAMAGE, "§c"));
        BASE_STRENGTH = CustomComponentClass.register("BASE_STRENGTH", new BasicAttributeComponentClass("AAB", Attribute.STRENGTH, "§c"));
        BASE_HEALTH = CustomComponentClass.register("BASE_HEALTH", new BasicAttributeComponentClass("AAC", Attribute.HEALTH, "§a"));
        BASE_DEFENSE = CustomComponentClass.register("BASE_DEFENSE", new BasicAttributeComponentClass("AAD", Attribute.DEFENSE, "§a"));
        BASE_CRITICAL_DAMAGE = CustomComponentClass.register("BASE_CRITICAL_DAMAGE", new BasicAttributeComponentClass("AAE", Attribute.CRITICAL_DAMAGE, "§c"));
        BASE_CRITICAL_CHANCE = CustomComponentClass.register("BASE_CRITICAL_CHANCE", new BasicAttributeComponentClass("AAF", Attribute.CRITICAL_CHANCE, "§c"));
        BASE_INTELLIGENCE = CustomComponentClass.register("BASE_INTELLIGENCE", new BasicAttributeComponentClass("AAG", Attribute.INTELLIGENCE, "§a"));

        UUID = CustomComponentClass.register("UUID", new StorageComponentClass("BAA", "UUID"));
        ID = CustomComponentClass.register("ID", new StorageComponentClass("BAC", "ID"));
        DESCRIPTION = CustomComponentClass.register("DESCRIPTION", new StorageComponentClass("BAD", "DESCRIPTION"));
        RUNE_SLOTS = CustomComponentClass.register("RUNE_SLOTS", new StorageComponentClass("BAE", "RUNE_SLOTS"));
        ITEM_CLASS = CustomComponentClass.register("ITEM_CLASS", new StorageComponentClass("BAF", "ITEM_CLASS"));
        ITEM_TIER = CustomComponentClass.register("ITEM_TIER", new StorageComponentClass("BAG", "ITEM_TIER"));
        ITEM_TYPE = CustomComponentClass.register("ITEM_TYPE", new StorageComponentClass("BAH", "ITEM_TYPE"));
        ITEM_CATEGORY = CustomComponentClass.register("ITEM_CATEGORY", new StorageComponentClass("BAI", "ITEM_CATEGORY"));
        MATERIAL = CustomComponentClass.register("MATERIAL", new StorageComponentClass("BAJ", "MATERIAL"));
        DISPLAY_NAME = CustomComponentClass.register("DISPLAY_NAME", new StorageComponentClass("BAK", "DISPLAY_NAME"));

        ADVANCED_DAMAGE_ARACHNID = CustomComponentClass.register("ADVANCED_DAMAGE_ARACHNID", new AdvancedDamageComponentClass("CAA", Element.ARACHNID, EnchantmentCategory.WEAPON));
        ADVANCED_DAMAGE_CUBOID = CustomComponentClass.register("ADVANCED_DAMAGE_CUBOID", new AdvancedDamageComponentClass("CAB", Element.CUBOID, EnchantmentCategory.WEAPON) );
        ADVANCED_DAMAGE_ALL = CustomComponentClass.register("ADVANCED_DAMAGE_ALL", new AdvancedDamageComponentClass("CAC", Element.NONE, EnchantmentCategory.WEAPON) );
        ADVANCED_DAMAGE_UNDEAD = CustomComponentClass.register("ADVANCED_DAMAGE_UNDEAD", new AdvancedDamageComponentClass("CAD", Element.UNDEAD, EnchantmentCategory.WEAPON) );
        ADVANCED_ATTRIBUTE_STRENGTH = CustomComponentClass.register("ADVANCED_ATTRIBUTE_STRENGTH", new AdvancedAttributeComponentClass("CAE", Attribute.STRENGTH, EnchantmentCategory.WEAPON));
    }
}
