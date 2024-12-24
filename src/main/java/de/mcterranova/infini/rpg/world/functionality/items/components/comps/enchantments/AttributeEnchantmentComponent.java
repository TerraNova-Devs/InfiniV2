package de.mcterranova.infini.rpg.world.functionality.items.components.comps.enchantments;

import de.mcterranova.infini.rpg.world.functionality.items.components.ComponentType;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponent;
import de.mcterranova.infini.rpg.world.functionality.items.enchanting.EnchantmentCategory;

public class AttributeEnchantmentComponent extends CustomComponent {

    public AttributeEnchantmentComponent(EnchantmentCategory category) {
        super(ComponentType.ENCHANTMENT, category);
    }


}
