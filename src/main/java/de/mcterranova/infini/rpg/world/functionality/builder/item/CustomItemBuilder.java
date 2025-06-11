package de.mcterranova.infini.rpg.world.functionality.builder.item;

import de.mcterranova.infini.Infini;
import de.mcterranova.infini.rpg.nms.NMSHelper;
import de.mcterranova.infini.rpg.utils.NBTUtils;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponent;
import de.mcterranova.infini.rpg.world.functionality.items.control.ItemArchive;
import de.mcterranova.infini.rpg.world.functionality.items.control.ItemMask;
import de.mcterranova.infini.rpg.world.functionality.items.item.ItemTier;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomItemBuilder {

    private final Material material;
    private boolean newUUID;
    private short amount;
    private boolean glowing = false;
    private final NamespacedKey attributeKey = new NamespacedKey(Infini.getInstance(), "attributemodifier");
    private final ItemMask mask;
    private boolean addAttributes = false;
    private final ItemArchive archive = ItemArchive.get();

    public CustomItemBuilder(ItemMask itemMask) {
        this.material = Material.valueOf(itemMask.data.get(CustomComponent.MATERIAL));
        this.mask = itemMask;
    }

    public CustomItemBuilder addAttributes(boolean v) {
        this.addAttributes = v;
        return this;
    }

    public CustomItemBuilder setAmount(short amount) {
        this.amount = amount;
        return this;
    }

    public CustomItemBuilder newUUID(boolean v) {
        this.newUUID = v;
        return this;
    }

    public CustomItemBuilder itemGlow(boolean v) {
        this.glowing = v;
        return this;
    }

    public ItemStack build()
    {
        if (mask.data.get(CustomComponent.ITEM_CATEGORY).contains("MENU_ITEM"))
            return NMSHelper.getEmptyItem(this.material);

        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        meta.displayName(Component.text(ItemTier.valueOf(mask.data.get(CustomComponent.ITEM_TIER)).getColor() + mask.data.get(CustomComponent.DISPLAY_NAME)));

        if (glowing)
            meta.addEnchant(Enchantment.AQUA_AFFINITY, 1, false);
        if (!mask.data.get(CustomComponent.ITEM_CATEGORY).contains("MATERIAL")) {
            if (newUUID)
                mask.newUUID();
            meta = NBTUtils.addNBTTag(meta, "UUID", mask.getUUID().toString());
            archive.update(mask);
        }
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, new AttributeModifier(attributeKey, 0d, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND));
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, new AttributeModifier(attributeKey, 100d, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND));
        meta.addItemFlags( ItemFlag.HIDE_ENCHANTS );
        meta.addItemFlags( ItemFlag.HIDE_ATTRIBUTES );
        meta.addItemFlags( ItemFlag.HIDE_ARMOR_TRIM );
        meta.addItemFlags( ItemFlag.HIDE_ADDITIONAL_TOOLTIP );
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.lore(new LoreBuilder(mask, addAttributes).build(36));
        item.setItemMeta(meta);
        item.setAmount(amount);
        return item;
    }
}
