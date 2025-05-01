package de.mcterranova.infini.current.rpg.world.functionality.builder.item;

import de.mcterranova.infini.Infini;
import de.mcterranova.infini.current.rpg.world.functionality.items.components.CustomComponentClass;
import de.mcterranova.infini.current.rpg.world.functionality.items.control.ItemMask;
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

    private Map<CustomComponentClass, Object> data = new HashMap<>();
    private Map<CustomComponentClass, Integer> attributes = new HashMap<>();
    private final ItemStack itemStack;
    private Component displayName;
    private short amount;
    private boolean glowing = false;
    private final NamespacedKey attributeKey = new NamespacedKey(Infini.getInstance(), "attributemodifier");

    public CustomItemBuilder(ItemMask itemMask) {
        this.data.putAll(itemMask.data);
        this.attributes.putAll(itemMask.attributes);
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

    public CustomItemBuilder setData(Map<CustomComponentClass, Object> data) {
        this.data = data;
        return this;
    }

    public CustomItemBuilder setAttributes(Map<CustomComponentClass, Integer> attributes) {
        this.attributes = attributes;
        return this;
    }

    public CustomItemBuilder addData(CustomComponentClass component, Object data) {
        this.data.putIfAbsent(component, data);
        return this;
    }

    public CustomItemBuilder addAttribute(CustomComponentClass attribute, int v) {
        this.data.putIfAbsent(attribute, v);
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

        meta.setLore();
        item.setItemMeta(meta);
        item.setAmount(amount);
        return item;
    }
}
