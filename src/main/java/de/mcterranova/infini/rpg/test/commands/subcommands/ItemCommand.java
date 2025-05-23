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
                        .addAttribute(CustomComponent.BASE_DAMAGE, 100)
                        .addAttribute(CustomComponent.BASE_INTELLIGENCE, 60)
                        .addAttribute(CustomComponent.BASE_HEALTH, 50)
                        .addAttribute(CustomComponent.BASE_DEFENSE, 40)
                        .addRune(new RuneWrapper(CustomComponent.ADVANCED_RUNE_STRENGTH, 1))
                        .addRune(new RuneWrapper(CustomComponent.ADVANCED_RUNE_INTELLIGENCE, 1))
                        .addRune(new RuneWrapper(CustomComponent.ADVANCED_RUNE_STRENGTH, 2))
                        .addData(CustomComponent.ADVANCED_DAMAGE_ALL, "5")
                        .addData(CustomComponent.ADVANCED_ATTRIBUTE_STRENGTH, "10")
                        .addData(CustomComponent.DESCRIPTION, "es bringt dich sicher ins heim und jetzt schreibe ich noch ganz viel zeug dazu um zu schauen ob mein alter zeilenumbruchscode noch geht _lol")
                        .addData(CustomComponent.RUNE_SLOTS, "4")
                        .addData(CustomComponent.DISPLAY_NAME, "Der heimverläufer 3000")
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
