package de.mcterranova.infini.current.rpg.world.functionality.items.components.comps.advanced.enchantments;

import de.mcterranova.infini.current.rpg.world.functionality.items.components.ComponentType;
import de.mcterranova.infini.current.rpg.world.functionality.items.components.CustomComponentClass;

import java.util.UUID;

public class AdvancedAttackComponentClass extends CustomComponentClass {

    protected AdvancedAttackComponentClass(EnchantmentCategory... categories) {
        super(ComponentType.ENCHANTMENT, categories);
    }

    @Override
    public void run(UUID uuid) {
        //first strike or sth
    }
}
