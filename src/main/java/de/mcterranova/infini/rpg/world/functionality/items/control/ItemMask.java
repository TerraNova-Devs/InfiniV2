package de.mcterranova.infini.rpg.world.functionality.items.control;

import de.mcterranova.infini.Infini;
import de.mcterranova.infini.rpg.database.content.customserialization.CustomSerializable;
import de.mcterranova.infini.rpg.nms.NMSHelper;
import de.mcterranova.infini.rpg.utils.NBTUtils;
import de.mcterranova.infini.rpg.world.functionality.builder.item.LoreBuilder;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponent;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponentClass;
import de.mcterranova.infini.rpg.world.functionality.items.components.comps.advanced.runes.RuneWrapper;
import de.mcterranova.infini.rpg.world.functionality.items.item.ItemTier;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.*;

public class ItemMask implements CustomSerializable {

    public final Map<CustomComponentClass, String> data = new HashMap<>();
    public final Map<CustomComponentClass, Double> attributes = new HashMap<>();
    public final List<RuneWrapper> runes = new ArrayList<>();

    public ItemMask(Material material, String id) {
        this.data.put(CustomComponent.ID, id);
        this.data.put(CustomComponent.MATERIAL, material.name());
    }

    public UUID getUUID() { return UUID.fromString(this.data.get(CustomComponent.UUID)); }
    public void newUUID() { this.data.put(CustomComponent.UUID, UUID.randomUUID().toString()); }

    @Override
    public String serialize() {
        List<String> v = new ArrayList<>();
        attributes.keySet().forEach(attribute -> v.add(attribute.getSerialized() + "," + attributes.get(attribute).toString()));
        data.keySet().forEach(component -> v.add(component.getSerialized() + "," + data.get(component)));
        runes.forEach(rune -> v.add(rune.rune().getSerialized() + "," + rune.level().toString()));
        StringBuilder builder = new StringBuilder();
        v.forEach(string -> builder.append(string).append("$"));
        return builder.toString();
    }

    public static ItemMask deserialize(String v) {
        String[] split = v.split("\\$");
        List<String> list = new ArrayList<>(Arrays.asList(split));
        Map<CustomComponentClass, Double> a = new HashMap<>();
        Map<CustomComponentClass, String> b = new HashMap<>();
        List<RuneWrapper> c = new ArrayList<>();
        list.forEach(v2 -> {
            switch (v2.substring(0, 1)) {
                case "A", "C":
                    String[] split2 = v2.split(",");
                    a.put(CustomComponentClass.deSerialize(split2[0]), Double.valueOf(split2[1]));
                    break;
                case "B":
                    String[] split3 = v2.split(",");
                    b.put(CustomComponentClass.deSerialize(split3[0]), split3[1]);
                    break;
                case "D":
                    String[] split4 = v2.split(",");
                    c.add(new RuneWrapper(CustomComponentClass.deSerialize(split4[0]), Integer.valueOf(split4[1])));
                    break;
            }});
        ItemMask result = new ItemMask(Material.valueOf(b.get(CustomComponent.MATERIAL)), b.get(CustomComponent.ID));
        result.attributes.putAll(a);
        result.data.putAll(b);
        result.runes.addAll(c);
        return result;
    }

    public ItemStack build(boolean attributes, boolean glow, int amount, boolean newUUID) {
        return new MaskBuilder(this).itemGlow(glow).addAttributes(attributes).setAmount((short)amount).newUUID(newUUID).build();
    }

    public static ItemStack getPlayerHead(Player player) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        meta.setOwningPlayer(player);
        head.setItemMeta(meta);
        return head;
    }

    private class MaskBuilder {
        private final Material material;
        private boolean newUUID;
        private short amount;
        private boolean glowing = false;
        private final NamespacedKey attributeKey = new NamespacedKey(Infini.getInstance(), "attributemodifier");
        private final ItemMask mask;
        private boolean addAttributes = false;
        private final ItemArchive archive = ItemArchive.get();

        public MaskBuilder(ItemMask itemMask) {
            this.material = Material.valueOf(itemMask.data.get(CustomComponent.MATERIAL));
            this.mask = itemMask;
        }

        public MaskBuilder addAttributes(boolean v) {
            this.addAttributes = v;
            return this;
        }

        public MaskBuilder setAmount(short amount) {
            this.amount = amount;
            return this;
        }

        public MaskBuilder newUUID(boolean v) {
            this.newUUID = v;
            return this;
        }

        public MaskBuilder itemGlow(boolean v) {
            this.glowing = v;
            return this;
        }

        public ItemStack build()
        {
            if (mask.data.get(CustomComponent.ITEM_CATEGORY).equals("MENU_ITEM_EMPTY")) {
                return NMSHelper.getEmptyItem(this.material, mask.data.get(CustomComponent.ID));
            }


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
            meta = NBTUtils.addNBTTag(meta, "ID", mask.data.get(CustomComponent.ID));
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

}
