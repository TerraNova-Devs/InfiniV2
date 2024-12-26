package de.mcterranova.infini.rpg.world.functionality.items.components;

import de.mcterranova.infini.rpg.world.functionality.Attribute;
import de.mcterranova.infini.rpg.world.functionality.items.components.comps.advanced.enchantments.AdvancedAttributeComponent;
import de.mcterranova.infini.rpg.world.functionality.items.components.comps.advanced.enchantments.AdvancedDamageComponent;
import de.mcterranova.infini.rpg.world.functionality.items.components.comps.basic.BasicAttributeComponent;
import de.mcterranova.infini.rpg.world.functionality.items.components.comps.basic.storage.StorageComponent;
import de.mcterranova.infini.rpg.world.functionality.items.components.comps.advanced.enchantments.EnchantmentCategory;
import de.mcterranova.infini.rpg.world.functionality.spells.Element;

public class Component {
    // public static final CustomComponent AAAAA;
    public static final CustomComponent BASE_DAMAGE;
    public static final CustomComponent BASE_STRENGTH;
    public static final CustomComponent BASE_HEALTH;
    public static final CustomComponent BASE_DEFENSE;
    public static final CustomComponent BASE_CRITICAL_DAMAGE;
    public static final CustomComponent BASE_CRITICAL_CHANCE;
    public static final CustomComponent BASE_INTELLIGENCE;

    public static final CustomComponent UUID;
    public static final CustomComponent ID;
    public static final CustomComponent AMOUNT;
    public static final CustomComponent DISPLAYNAME;
    public static final CustomComponent DESCRIPTION;
    public static final CustomComponent RUNE_SLOTS;
    public static final CustomComponent ITEM_CLASS;
    public static final CustomComponent ITEM_TIER;
    public static final CustomComponent ITEM_TYPE;
    public static final CustomComponent ITEM_CATEGORY;

    public static final CustomComponent DAMAGE_ARACHNID;
    public static final CustomComponent DAMAGE_CUBOID;
    public static final CustomComponent DAMAGE_ALL;
    public static final CustomComponent DAMAGE_UNDEAD;
    public static final CustomComponent ATTRIBUTE_STRENGTH;

    static {
        // AAAAA = ComponentManager.register("AAAAA", new AAAAA );
        BASE_DAMAGE = CustomComponent.register("BASE_DAMAGE", new BasicAttributeComponent(Attribute.DAMAGE));
        BASE_STRENGTH = CustomComponent.register("BASE_STRENGTH", new BasicAttributeComponent(Attribute.STRENGTH));
        BASE_HEALTH = CustomComponent.register("BASE_HEALTH", new BasicAttributeComponent(Attribute.HEALTH));
        BASE_DEFENSE = CustomComponent.register("BASE_DEFENSE", new BasicAttributeComponent(Attribute.DEFENSE));
        BASE_CRITICAL_DAMAGE = CustomComponent.register("BASE_CRITICAL_DAMAGE", new BasicAttributeComponent(Attribute.CRITICAL_DAMAGE));
        BASE_CRITICAL_CHANCE = CustomComponent.register("BASE_CRITICAL_CHANCE", new BasicAttributeComponent(Attribute.CRITICAL_CHANCE));
        BASE_INTELLIGENCE = CustomComponent.register("BASE_INTELLIGENCE", new BasicAttributeComponent(Attribute.INTELLIGENCE));

        UUID = CustomComponent.register("UUID", new StorageComponent("UUID"));
        ID = CustomComponent.register("ID", new StorageComponent("ID"));
        AMOUNT = CustomComponent.register("AMOUNT", new StorageComponent("AMOUNT"));
        DISPLAYNAME = CustomComponent.register("DISPLAYNAME", new StorageComponent("DISPLAYNAME"));
        DESCRIPTION = CustomComponent.register("DESCRIPTION", new StorageComponent("DESCRIPTION"));
        RUNE_SLOTS = CustomComponent.register("RUNE_SLOTS", new StorageComponent("RUNE_SLOTS"));
        ITEM_CLASS = CustomComponent.register("ITEM_CLASS", new StorageComponent("ITEM_CLASS"));
        ITEM_TIER = CustomComponent.register("ITEM_TIER", new StorageComponent("ITEM_TIER"));
        ITEM_TYPE = CustomComponent.register("ITEM_TYPE", new StorageComponent("ITEM_TYPE"));
        ITEM_CATEGORY = CustomComponent.register("ITEM_CATEGORY", new StorageComponent("ITEM_CATEGORY"));


        DAMAGE_ARACHNID = CustomComponent.register("DAMAGE_ARACHNID", new AdvancedDamageComponent(Element.ARACHNID, EnchantmentCategory.WEAPON));
        DAMAGE_CUBOID = CustomComponent.register("DAMAGE_CUBOID", new AdvancedDamageComponent(Element.CUBOID, EnchantmentCategory.WEAPON) );
        DAMAGE_ALL = CustomComponent.register("DAMAGE_ALL", new AdvancedDamageComponent(Element.NONE, EnchantmentCategory.WEAPON) );
        DAMAGE_UNDEAD = CustomComponent.register("DAMAGE_UNDEAD", new AdvancedDamageComponent(Element.UNDEAD, EnchantmentCategory.WEAPON) );
        ATTRIBUTE_STRENGTH = CustomComponent.register("ATTRIBUTE_STRENGTH", new AdvancedAttributeComponent(Attribute.STRENGTH, EnchantmentCategory.WEAPON));
    }
}
