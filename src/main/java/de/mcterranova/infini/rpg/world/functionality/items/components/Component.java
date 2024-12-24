package de.mcterranova.infini.rpg.world.functionality.items.components;

import de.mcterranova.infini.rpg.world.functionality.Attribute;
import de.mcterranova.infini.rpg.world.functionality.items.components.comps.advanced.enchantments.AdvancedAttributeComponent;
import de.mcterranova.infini.rpg.world.functionality.items.components.comps.advanced.enchantments.AdvancedDamageComponent;
import de.mcterranova.infini.rpg.world.functionality.items.components.comps.basic.BasicAttributeComponent;
import de.mcterranova.infini.rpg.world.functionality.items.components.comps.basic.storage.StorageComponent;
import de.mcterranova.infini.rpg.world.functionality.items.components.comps.basic.storage.StorageDeclaration;
import de.mcterranova.infini.rpg.world.functionality.items.enchanting.EnchantmentCategory;
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
    public static final CustomComponent RUNE_SLOTS;

    public static final CustomComponent DAMAGE_ARACHNID;
    public static final CustomComponent DAMAGE_CUBOID;
    public static final CustomComponent DAMAGE_ALL;
    public static final CustomComponent DAMAGE_UNDEAD;
    public static final CustomComponent ATTRIBUTE_STRENGTH;

    static {
        // AAAAA = ComponentManager.register("AAAAA", new AAAAA );
        BASE_DAMAGE = ComponentManager.register("BASE_DAMAGE", new BasicAttributeComponent(Attribute.DAMAGE));
        BASE_STRENGTH = ComponentManager.register("BASE_STRENGTH", new BasicAttributeComponent(Attribute.STRENGTH));
        BASE_HEALTH = ComponentManager.register("BASE_HEALTH", new BasicAttributeComponent(Attribute.HEALTH));
        BASE_DEFENSE = ComponentManager.register("BASE_DEFENSE", new BasicAttributeComponent(Attribute.DEFENSE));
        BASE_CRITICAL_DAMAGE = ComponentManager.register("BASE_CRITICAL_DAMAGE", new BasicAttributeComponent(Attribute.CRITICAL_DAMAGE));
        BASE_CRITICAL_CHANCE = ComponentManager.register("BASE_CRITICAL_CHANCE", new BasicAttributeComponent(Attribute.CRITICAL_CHANCE));
        BASE_INTELLIGENCE = ComponentManager.register("BASE_INTELLIGENCE", new BasicAttributeComponent(Attribute.INTELLIGENCE));

        UUID = ComponentManager.register("UUID", new StorageComponent(StorageDeclaration.UUID));
        RUNE_SLOTS = ComponentManager.register("RUNE_SLOTS", new StorageComponent(StorageDeclaration.RUNE_SLOTS));

        DAMAGE_ARACHNID = ComponentManager.register("DAMAGE_ARACHNID", new AdvancedDamageComponent(Element.ARACHNID));
        DAMAGE_CUBOID = ComponentManager.register("DAMAGE_CUBOID", new AdvancedDamageComponent(Element.CUBOID) );
        DAMAGE_ALL = ComponentManager.register("DAMAGE_ALL", new AdvancedDamageComponent(Element.NONE) );
        DAMAGE_UNDEAD = ComponentManager.register("DAMAGE_UNDEAD", new AdvancedDamageComponent(Element.UNDEAD) );
        ATTRIBUTE_STRENGTH = ComponentManager.register("ATTRIBUTE_STRENGTH", new AdvancedAttributeComponent(Attribute.STRENGTH, EnchantmentCategory.WEAPON));
    }
}
