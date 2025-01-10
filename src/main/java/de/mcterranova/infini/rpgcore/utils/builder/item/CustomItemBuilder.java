package de.mcterranova.infini.rpgcore.utils.builder.item;

import de.mcterranova.infini.Infini;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponent;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponentClass;
import de.mcterranova.infini.rpg.world.functionality.items.control.ItemMask;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;

public class CustomItemBuilder {

    private Map<CustomComponentClass, Integer> components = new HashMap<>();
    private final ItemStack itemStack;
    private Component displayName;
    private short amount;
    private boolean glowing = false;
    private final NamespacedKey attributeKey = new NamespacedKey(Infini.getInstance(), "attributemodifier");

    public CustomItemBuilder(ItemMask itemMask) {
        itemMask.data.keySet().forEach(component -> this.components.put(component, (Integer) itemMask.data.get(component)));
        this.itemStack = itemMask.getItemStack();
        this.displayName = this.itemStack.getItemMeta().displayName();
    }

    public CustomItemBuilder(Material material) {
        this.itemStack = new ItemStack(material);
    }

    public CustomItemBuilder(Material material, short amount) {
        this.itemStack = new ItemStack(material);
        this.amount = amount;
    }

    public CustomItemBuilder(Material material, Component displayName) {
        this.itemStack = new ItemStack(material);
        this.displayName = displayName;
    }

    public CustomItemBuilder(Material material, short amount, Component displayName) {
        this.itemStack = new ItemStack(material);
        this.amount = amount;
        this.displayName = displayName;
    }

    public CustomItemBuilder setComponents(Map<CustomComponentClass, Integer> data) {
        this.components = data;
        return this;
    }

    public CustomItemBuilder addComponent(CustomComponentClass component, int v) {
        this.components.putIfAbsent(component, v);
        return this;
    }

    public CustomItemBuilder setAmount(short amount) {
        this.amount = amount;
        return this;
    }

    public CustomItemBuilder itemGlow(boolean v) {
        this.glowing = v;
        return this;
    }

    public ItemStack build()
    {
        ItemStack item = new ItemStack(itemStack.getType());
        ItemMeta meta = item.getItemMeta();

        meta.displayName(displayName);

        meta.addAttributeModifier(org.bukkit.attribute.Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(attributeKey, 0d, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND));
        meta.addAttributeModifier(org.bukkit.attribute.Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(attributeKey, 100d, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND));
        meta.addItemFlags( ItemFlag.HIDE_ENCHANTS );
        meta.addItemFlags( ItemFlag.HIDE_ATTRIBUTES );
        meta.addItemFlags( ItemFlag.HIDE_ARMOR_TRIM );
        meta.addItemFlags( ItemFlag.HIDE_ADDITIONAL_TOOLTIP );
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        item.setItemMeta(meta);
        item.setAmount(amount);
        return item;
    }
}
