package de.mcterranova.infini.rpg.world.functionality.items.components.comps.advanced.enchantments;

import de.mcterranova.infini.rpg.world.functionality.items.components.ComponentType;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponentClass;

import java.util.UUID;

public class AdvancedAttackComponentClass extends CustomComponentClass {

    private final String serialized;

    protected AdvancedAttackComponentClass(String serialized, EnchantmentCategory... categories) {
        super(ComponentType.ENCHANTMENT, categories);
        this.serialized = serialized;
    }

    @Override
    public void run(UUID uuid) {
        //first strike or sth
    }

    @Override
    public String getColor() {
        return "§9";
    }

    @Override
    public String getSerialized() {
        return serialized;
    }
}
