package de.mcterranova.infini.rpg.test.commands;

import de.mcterranova.infini.rpg.database.content.templates.TemplateHelper;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponent;
import de.mcterranova.infini.rpg.world.functionality.items.control.ItemManipulator;
import de.mcterranova.infini.rpg.world.functionality.items.item.ItemCategory;
import de.mcterranova.infini.rpg.world.functionality.items.item.ItemClass;
import de.mcterranova.infini.rpg.world.functionality.items.item.ItemTier;
import de.mcterranova.terranovaLib.commands.CommandAnnotation;
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
                TemplateHelper.get().saveTemplate(args[2], new ItemManipulator(Material.DIAMOND_SWORD, "TEST_FUCKER")
                        .addAttribute(CustomComponent.BASE_DAMAGE, 100)
                        .addAttribute(CustomComponent.BASE_CRITICAL_CHANCE, 200)
                        .addAttribute(CustomComponent.BASE_DEFENSE, 300)
                        .addAttribute(CustomComponent.BASE_INTELLIGENCE, 400)
                        .addAttribute(CustomComponent.ADVANCED_RUNE_STRENGTH, 1)
                        .addAttribute(CustomComponent.ADVANCED_RUNE_INTELLIGENCE, 2)
                        .addData(CustomComponent.DESCRIPTION, "THIS IS A TEST DESCRIPTION")
                        .addData(CustomComponent.RUNE_SLOTS, "5")
                        .addData(CustomComponent.DISPLAY_NAME, "fucker 3000")
                        .addData(CustomComponent.ITEM_CLASS, ItemClass.GENERIC.name())
                        .addData(CustomComponent.ITEM_TIER, ItemTier.VI.name())
                        .addData(CustomComponent.ITEM_CATEGORY, ItemCategory.WEAPON.name())
                        .getItemMask());
                p.sendMessage(args[2]);
                return true;
            }
            case "save" -> {

                p.sendMessage("abc");
                return true;
            }
            case "load" -> {
                p.getInventory().addItem(new ItemManipulator(TemplateHelper.get().getTemplate(args[2])).manifest(true, true, (short) 1));
                p.sendMessage("abc");
                return true;
            }
        }
        return true;
    }
}
