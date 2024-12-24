package de.mcterranova.infini.rpg.world.functionality.items.components.comps.advanced.enchantments;

import de.mcterranova.infini.rpg.world.functionality.items.components.ComponentType;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponent;

import java.util.UUID;

public class AdvancedAttackComponent extends CustomComponent {

    protected AdvancedAttackComponent(EnchantmentCategory... categories) {
        super(ComponentType.ENCHANTMENT, categories);
    }

    @Override
    public void run(UUID uuid) {
        //first strike or sth
    }
}
