package de.mcterranova.infini.rpg.world.functionality.items.control;

import de.mcterranova.infini.rpg.database.content.templates.TemplateHelper;
import de.mcterranova.infini.rpg.world.functionality.builder.item.CustomItemBuilder;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponent;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponentClass;
import de.mcterranova.infini.rpg.world.functionality.items.components.comps.advanced.runes.RuneWrapper;
import de.mcterranova.infini.rpg.world.functionality.items.item.ItemCategory;
import de.mcterranova.infini.rpg.world.functionality.items.item.ItemType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemManipulator {

    private final ItemArchive archive = ItemArchive.get();
    private final ItemMask itemMask;

    public ItemManipulator(ItemMask mask) {
        this.itemMask = mask;
    }

    public ItemManipulator(Material material, String id) {
        this.itemMask = new ItemMask(material, id);
    }

    public ItemManipulator(String templateID) {
        this.itemMask = TemplateHelper.get().getTemplate(templateID);
    }

    public ItemManipulator configureData(CustomComponentClass component, String data) {
        this.itemMask.data.replace(component, data);
        return this;
    }

    public ItemManipulator addData(CustomComponentClass component, String data) {
        this.itemMask.data.put(component, data);
        return this;
    }

    public ItemManipulator removeData(CustomComponentClass component) {
        this.itemMask.data.remove(component);
        return this;
    }

    public ItemManipulator configureAttribute(CustomComponentClass attribute, int v) {
        this.itemMask.attributes.replace(attribute, v);
        return this;
    }

    public ItemManipulator addAttribute(CustomComponentClass attribute, int v) {
        this.itemMask.attributes.put(attribute, v);
        return this;
    }

    public ItemManipulator removeAttribute(CustomComponentClass attribute) {
        this.itemMask.attributes.remove(attribute);
        return this;
    }

    public ItemManipulator configureRune(RuneWrapper rune) {
        this.itemMask.runes.add(this.itemMask.runes.indexOf(rune), rune);
        return this;
    }

    public ItemManipulator addRune(RuneWrapper rune) {
        this.itemMask.runes.add(rune);
        return this;
    }

    public ItemManipulator removeRune(RuneWrapper rune) {
        this.itemMask.runes.remove(rune);
        return this;
    }

    public ItemStack getBlank() {
        return new CustomItemBuilder(itemMask).buildBlank();
    }

    public ItemStack manifest(boolean attributes, boolean glow, short amount, boolean newUUID) {
        return new CustomItemBuilder(itemMask).itemGlow(glow).addAttributes(attributes).setAmount(amount).newUUID(newUUID).build();
    }

    public ItemMask getItemMask() {
        if (!itemMask.data.get(CustomComponent.ITEM_CATEGORY).equals(ItemCategory.MATERIAL.name()) && itemMask.data.get(CustomComponent.UUID) != null)
            archive.update(itemMask);
        return itemMask;
    }

    public void queue() {
        archive.update(itemMask);
    }


    //update itemstack
}
