package de.mcterranova.infini.rpg.world.functionality.items.control;

import de.mcterranova.infini.rpg.database.content.DatabaseHelper;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponent;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponentClass;
import de.mcterranova.infini.rpg.world.functionality.items.components.comps.advanced.runes.RuneWrapper;
import de.mcterranova.infini.rpg.world.functionality.items.item.ItemCategory;
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
        this.itemMask = DatabaseHelper.getItemTemplate(templateID);
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

    public ItemManipulator configureAttribute(CustomComponentClass attribute, double v) {
        this.itemMask.attributes.replace(attribute, v);
        return this;
    }

    public ItemManipulator addAttribute(CustomComponentClass attribute, double v) {
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

    public ItemStack manifest(boolean attributes, boolean glow, int amount, boolean newUUID) {
        return itemMask.build(attributes, glow, (short)amount, newUUID);
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
