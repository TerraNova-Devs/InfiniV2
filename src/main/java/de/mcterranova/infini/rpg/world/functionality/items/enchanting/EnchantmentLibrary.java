package de.mcterranova.infini.rpg.world.functionality.items.enchanting;

import de.mcterranova.infini.rpg.world.functionality.Attribute;
import de.mcterranova.infini.rpg.world.functionality.items.enchanting.enchants.group.AttributeEnchantment;
import de.mcterranova.infini.rpg.world.functionality.items.enchanting.enchants.group.DamageEnchantment;
import de.mcterranova.infini.rpg.world.functionality.items.enchanting.enchants.group.ProtectionEnchantment;
import de.mcterranova.infini.rpg.world.functionality.spells.Element;

public class EnchantmentLibrary {
    private static final String e = "ENCHANTMENT_";
    public static final CustomEnchantment DAMAGE_ALL;
    public static final CustomEnchantment DAMAGE_UNDEAD;
    public static final CustomEnchantment DAMAGE_ARACHNID;
    public static final CustomEnchantment PROTECTION_ALL;
    public static final CustomEnchantment PROTECTION_UNDEAD;
    public static final CustomEnchantment PROTECTION_ARACHNID;
    public static final CustomEnchantment ATTRIBUTE_STRENGTH;
    public static final CustomEnchantment ATTRIBUTE_CRITICAL_DAMAGE;
    public static final CustomEnchantment DAMAGE_CUBOID;

    static {
        DAMAGE_CUBOID = EnchantmentManager.register(e + "DAMAGE_CUBOID", new DamageEnchantment(Element.CUBOID));
        DAMAGE_ALL = EnchantmentManager.register( e + "DAMAGE_ALL", new DamageEnchantment( Element.NONE ) );
        DAMAGE_UNDEAD = EnchantmentManager.register( e + "DAMAGE_UNDEAD", new DamageEnchantment( Element.UNDEAD ) );
        DAMAGE_ARACHNID = EnchantmentManager.register( e + "DAMAGE_ARACHNID", new DamageEnchantment( Element.ARACHNID ) );
        PROTECTION_ALL = EnchantmentManager.register( e + "PROTECTION_ALL", new ProtectionEnchantment( Element.NONE ) );
        PROTECTION_UNDEAD = EnchantmentManager.register( e + "PROTECTION_UNDEAD", new ProtectionEnchantment( Element.UNDEAD ) );
        PROTECTION_ARACHNID = EnchantmentManager.register( e + "PROTECTION_ARACHNID", new ProtectionEnchantment( Element.ARACHNID ) );
        ATTRIBUTE_STRENGTH = EnchantmentManager.register( e + "ATTRIBUTE_STRENGTH", new AttributeEnchantment( Attribute.STRENGTH ) );
        ATTRIBUTE_CRITICAL_DAMAGE = EnchantmentManager.register( e + "ATTRIBUTE_CRITICAL_DAMAGE", new AttributeEnchantment( Attribute.CRITICAL_DAMAGE ) );
    }
}
