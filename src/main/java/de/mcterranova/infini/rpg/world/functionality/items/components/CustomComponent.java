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
    public static final CustomComponentClass LOCATION;
    public static final CustomComponentClass ID;
    public static final CustomComponentClass DESCRIPTION;
    public static final CustomComponentClass RUNE_SLOTS;
    public static final CustomComponentClass ITEM_CLASS;
    public static final CustomComponentClass ITEM_TIER;
    public static final CustomComponentClass ITEM_TYPE;
    public static final CustomComponentClass ITEM_CATEGORY;

    public static final CustomComponentClass ADVANCED_DAMAGE_ARACHNID;
    public static final CustomComponentClass ADVANCED_DAMAGE_CUBOID;
    public static final CustomComponentClass ADVANCED_DAMAGE_ALL;
    public static final CustomComponentClass ADVANCED_DAMAGE_UNDEAD;
    public static final CustomComponentClass ADVANCED_ATTRIBUTE_STRENGTH;

    static {
        // AAAAA = CustomComponentClass.register("AAAAA", new AAAAA );
        BASE_DAMAGE = CustomComponentClass.register("BASE_DAMAGE", new BasicAttributeComponentClass(Attribute.DAMAGE, "§c"));
        BASE_STRENGTH = CustomComponentClass.register("BASE_STRENGTH", new BasicAttributeComponentClass(Attribute.STRENGTH, "§c"));
        BASE_HEALTH = CustomComponentClass.register("BASE_HEALTH", new BasicAttributeComponentClass(Attribute.HEALTH, "§a"));
        BASE_DEFENSE = CustomComponentClass.register("BASE_DEFENSE", new BasicAttributeComponentClass(Attribute.DEFENSE, "§a"));
        BASE_CRITICAL_DAMAGE = CustomComponentClass.register("BASE_CRITICAL_DAMAGE", new BasicAttributeComponentClass(Attribute.CRITICAL_DAMAGE, "§c"));
        BASE_CRITICAL_CHANCE = CustomComponentClass.register("BASE_CRITICAL_CHANCE", new BasicAttributeComponentClass(Attribute.CRITICAL_CHANCE, "§c"));
        BASE_INTELLIGENCE = CustomComponentClass.register("BASE_INTELLIGENCE", new BasicAttributeComponentClass(Attribute.INTELLIGENCE, "§a"));

        UUID = CustomComponentClass.register("UUID", new StorageComponentClass("UUID"));
        LOCATION = CustomComponentClass.register("LOCATION", new StorageComponentClass("LOCATION") );
        ID = CustomComponentClass.register("ID", new StorageComponentClass("ID"));
        DESCRIPTION = CustomComponentClass.register("DESCRIPTION", new StorageComponentClass("DESCRIPTION"));
        RUNE_SLOTS = CustomComponentClass.register("RUNE_SLOTS", new StorageComponentClass("RUNE_SLOTS"));
        ITEM_CLASS = CustomComponentClass.register("ITEM_CLASS", new StorageComponentClass("ITEM_CLASS"));
        ITEM_TIER = CustomComponentClass.register("ITEM_TIER", new StorageComponentClass("ITEM_TIER"));
        ITEM_TYPE = CustomComponentClass.register("ITEM_TYPE", new StorageComponentClass("ITEM_TYPE"));
        ITEM_CATEGORY = CustomComponentClass.register("ITEM_CATEGORY", new StorageComponentClass("ITEM_CATEGORY"));

        ADVANCED_DAMAGE_ARACHNID = CustomComponentClass.register("ADVANCED_DAMAGE_ARACHNID", new AdvancedDamageComponentClass(Element.ARACHNID, EnchantmentCategory.WEAPON));
        ADVANCED_DAMAGE_CUBOID = CustomComponentClass.register("ADVANCED_DAMAGE_CUBOID", new AdvancedDamageComponentClass(Element.CUBOID, EnchantmentCategory.WEAPON) );
        ADVANCED_DAMAGE_ALL = CustomComponentClass.register("ADVANCED_DAMAGE_ALL", new AdvancedDamageComponentClass(Element.NONE, EnchantmentCategory.WEAPON) );
        ADVANCED_DAMAGE_UNDEAD = CustomComponentClass.register("ADVANCED_DAMAGE_UNDEAD", new AdvancedDamageComponentClass(Element.UNDEAD, EnchantmentCategory.WEAPON) );
        ADVANCED_ATTRIBUTE_STRENGTH = CustomComponentClass.register("ADVANCED_ATTRIBUTE_STRENGTH", new AdvancedAttributeComponentClass(Attribute.STRENGTH, EnchantmentCategory.WEAPON));
    }
}
