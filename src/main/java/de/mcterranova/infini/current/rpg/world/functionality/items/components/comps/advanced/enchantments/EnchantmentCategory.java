package de.mcterranova.infini.current.rpg.world.functionality.items.components.comps.advanced.enchantments;

import org.bukkit.inventory.ItemStack;
import de.mcterranova.infini.rpg.utils.NBTUtils;
import de.mcterranova.infini.current.rpg.world.functionality.items.components.comps.basic.storage.item.ItemCategory;
import de.mcterranova.infini.current.rpg.world.functionality.items.components.comps.basic.storage.item.ItemType;

public enum EnchantmentCategory {
    ARMOR {
        public boolean canEnchant( ItemStack itemStack ) { return nbt.getItemCategory( itemStack ).equals( ItemCategory.ARMOR ) ; }
    },
    ARMOR_HEAD {
        public boolean canEnchant( ItemStack itemStack ) { return nbt.getItemType( itemStack ).equals( ItemType.HELMET ) ; }
    },
    ARMOR_CHEST {
        public boolean canEnchant( ItemStack itemStack ) { return nbt.getItemType( itemStack ).equals( ItemType.CHESTPLATE ) ; }
    },
    ARMOR_LEGGINGS {
        public boolean canEnchant( ItemStack itemStack ) { return nbt.getItemType( itemStack ).equals( ItemType.LEGGINGS ) ; }
    },
    ARMOR_FEET {
        public boolean canEnchant( ItemStack itemStack ) { return nbt.getItemType( itemStack ).equals( ItemType.BOOTS ) ; }
    },
    TOOL {
        public boolean canEnchant( ItemStack itemStack ) { return nbt.getItemCategory( itemStack ).equals( ItemCategory.TOOL ) ; }
    },
    WEAPON {
        public boolean canEnchant( ItemStack itemStack ) { return nbt.getItemCategory( itemStack ).equals( ItemCategory.WEAPON ) ; }
    },
    ALL {
        public boolean canEnchant( ItemStack itemStack ) { return nbt.getItemCategory( itemStack ).equals( ItemCategory.WEAPON ) ; }
    },
    SHIELD {
        public boolean canEnchant( ItemStack itemStack ) { return nbt.getItemType( itemStack ).equals( ItemType.SHIELD ) ; }
    };

final NBTUtils nbt = new NBTUtils();

EnchantmentCategory() {}

public abstract boolean canEnchant( ItemStack itemStack );
}
