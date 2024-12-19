package de.mcterranova.infini.rpg.world.functionality.items.runecrafting;

import de.mcterranova.infini.rpg.world.functionality.Attribute;
import de.mcterranova.infini.rpg.world.functionality.items.enchanting.CustomEnchantment;
import de.mcterranova.infini.rpg.world.functionality.items.enchanting.EnchantmentCategory;
import de.mcterranova.infini.rpg.world.functionality.items.enchanting.EnchantmentLibrary;
import de.mcterranova.infini.rpg.world.functionality.items.enchanting.EnchantmentManager;
import de.mcterranova.infini.rpg.world.functionality.items.enchanting.enchants.group.AttributeEnchantment;
import de.mcterranova.infini.rpg.world.functionality.items.enchanting.enchants.group.DamageEnchantment;
import de.mcterranova.infini.rpg.world.functionality.items.enchanting.enchants.group.ProtectionEnchantment;
import de.mcterranova.infini.rpg.world.functionality.spells.Element;

public class RuneLibrary {
    private static final String e = "RUNE_";
    public static final Rune STRENGTH;

    static {
        STRENGTH = RuneManager.register(e + "DAMAGE_CUBOID", new DamageEnchantment(Element.CUBOID));
    }
}
