package de.mcterranova.infini.rpg.test.commands;

import de.mcterranova.infini.rpg.database.content.customserialization.Serializer;
import de.mcterranova.infini.rpg.database.content.templates.TemplateHelper;
import de.mcterranova.infini.rpg.world.functionality.builder.item.CustomItemBuilder;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponent;
import de.mcterranova.infini.rpg.world.functionality.items.control.ItemManipulator;
import de.mcterranova.infini.rpg.world.functionality.items.control.ItemMask;
import de.mcterranova.infini.rpg.world.functionality.items.item.ItemCategory;
import de.mcterranova.infini.rpg.world.functionality.items.item.ItemClass;
import de.mcterranova.infini.rpg.world.functionality.items.item.ItemTier;
import de.mcterranova.terranovaLib.commands.CommandAnnotation;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ItemCommand {

    @CommandAnnotation(
            domain = "item.$A.$B",
            description = "test command for items",
            usage = "/Testcommand item <spawn|save>"
    )

    public static boolean a(Player p, String[] args) {
        switch (args[1]) {
            case "spawn" -> {
                p.getInventory().addItem(new ItemManipulator(Material.BAKED_POTATO, "TEST_POTATO")
                        .addAttribute(CustomComponent.BASE_DAMAGE, 100)
                        .addAttribute(CustomComponent.BASE_CRITICAL_CHANCE, 200)
                        .addAttribute(CustomComponent.BASE_DEFENSE, 300)
                        .addAttribute(CustomComponent.BASE_INTELLIGENCE, 400)
                        .addData(CustomComponent.DESCRIPTION, "THIS IS A TEST DESCRIPTION")
                        .addData(CustomComponent.ID, "TEST_POTATO")
                        .addData(CustomComponent.ITEM_CLASS, ItemClass.GENERIC.name())
                        .addData(CustomComponent.ITEM_TIER, ItemTier.O.name())
                        .addData(CustomComponent.ITEM_CATEGORY, ItemCategory.MATERIAL.name())
                        .manifest(true, true, (short) 50)
                        .getItemStack());
                p.sendMessage(args[2]);
                return true;
            }
            case "save" -> {
                TemplateHelper.get().saveTemplate(args[2], new ItemMask(p.getEquipment().getItemInMainHand(), args[2]));
                p.sendMessage("abc");
                return true;
            }
            case "load" -> {
                p.getInventory().addItem(new ItemManipulator(TemplateHelper.get().getTemplate(args[2])).manifest(true, true, (short) 40).getItemStack());
                p.sendMessage("abc");
                return true;
            }
        }
        return true;
    }
}
