package de.mcterranova.infini.rpg.test.commands.subcommands;

import de.mcterranova.infini.rpg.database.content.templates.TemplateHelper;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponent;
import de.mcterranova.infini.rpg.world.functionality.items.components.comps.advanced.runes.RuneWrapper;
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
                TemplateHelper.get().saveTemplate(args[2], new ItemManipulator(Material.POTATO, "TEST_POTATO")
                        .addAttribute(CustomComponent.BASE_DAMAGE, 80)
                        .addAttribute(CustomComponent.BASE_STRENGTH, 60)
                        .addAttribute(CustomComponent.BASE_INTELLIGENCE, 50)
                        .addRune(new RuneWrapper(CustomComponent.ADVANCED_RUNE_INTELLIGENCE, 2))
                        .addRune(new RuneWrapper(CustomComponent.ADVANCED_RUNE_INTELLIGENCE, 1))
                        .addAttribute(CustomComponent.ADVANCED_DAMAGE_ALL, 10)
                        .addAttribute(CustomComponent.ADVANCED_DAMAGE_ARACHNID, 10)
                        .addAttribute(CustomComponent.ADVANCED_DAMAGE_CUBOID, 10)
                        .addAttribute(CustomComponent.ADVANCED_ATTRIBUTE_STRENGTH, 10)
                        .addAttribute(CustomComponent.ADVANCED_DAMAGE_UNDEAD, 10)
                        .addData(CustomComponent.DESCRIPTION, "dustin ist eine kartoffel")
                        .addData(CustomComponent.RUNE_SLOTS, "4")
                        .addData(CustomComponent.DISPLAY_NAME, "der dusterino 3000")
                        .addData(CustomComponent.ITEM_CLASS, ItemClass.GENERIC.name())
                        .addData(CustomComponent.ITEM_TIER, ItemTier.IV.name())
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
