package de.mcterranova.infini.rpg.test.commands.subcommands;

import de.mcterranova.infini.Infini;
import de.mcterranova.infini.rpg.database.content.templates.TemplateHelper;
import de.mcterranova.infini.rpg.nms.NMSHelper;
import de.mcterranova.infini.rpg.world.functionality.inventory.CustomGUI;
import de.mcterranova.infini.rpg.world.functionality.inventory.CustomGUIClass;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponent;
import de.mcterranova.infini.rpg.world.functionality.items.components.comps.advanced.runes.RuneWrapper;
import de.mcterranova.infini.rpg.world.functionality.items.control.ItemManipulator;
import de.mcterranova.infini.rpg.world.functionality.items.item.ItemCategory;
import de.mcterranova.infini.rpg.world.functionality.items.item.ItemClass;
import de.mcterranova.infini.rpg.world.functionality.items.item.ItemTier;
import de.mcterranova.terranovaLib.commands.CommandAnnotation;
import org.bukkit.Bukkit;
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
                TemplateHelper.saveItemTemplate(new ItemManipulator(Material.NETHERITE_SWORD, "ORPHAN_SLAYER")
                                .addAttribute(CustomComponent.BASE_DAMAGE, 800)
                                .addAttribute(CustomComponent.BASE_STRENGTH, 375)
                                .addAttribute(CustomComponent.BASE_ATTACK_SPEED, 4)
                                .addAttribute(CustomComponent.BASE_CRITICAL_CHANCE, 100)
                                .addAttribute(CustomComponent.BASE_CRITICAL_DAMAGE, 3000)
                                .addData(CustomComponent.DESCRIPTION, "Erklärt sich von allein wofür das Schwert benutzt wird")
                                .addData(CustomComponent.DISPLAY_NAME, "Orphan Slayer")
                                .addData(CustomComponent.ITEM_TIER, ItemTier.VI.name())
                                .addData(CustomComponent.ITEM_CLASS, ItemClass.BERSERK.name())
                                .addData(CustomComponent.ITEM_CATEGORY, ItemCategory.WEAPON.name())
                                .addAttribute(CustomComponent.ADVANCED_ATTRIBUTE_STRENGTH, 10)
                                .addAttribute(CustomComponent.ADVANCED_DAMAGE_CUBOID, 10)
                                .addAttribute(CustomComponent.ADVANCED_DAMAGE_ARACHNID, 10)
                                .addAttribute(CustomComponent.ADVANCED_DAMAGE_ALL, 10)
                                .addAttribute(CustomComponent.ADVANCED_DAMAGE_UNDEAD, 10)
                                .addData(CustomComponent.RUNE_SLOTS, "5")
                                .addRune(new RuneWrapper(CustomComponent.RUNE_INTELLIGENCE, 10))
                                .addRune(new RuneWrapper(CustomComponent.RUNE_INTELLIGENCE, 10))
                                .addRune(new RuneWrapper(CustomComponent.RUNE_INTELLIGENCE, 10))
                                .addRune(new RuneWrapper(CustomComponent.RUNE_INTELLIGENCE, 10))
                                .addRune(new RuneWrapper(CustomComponent.RUNE_INTELLIGENCE, 10))
                        .getItemMask());
                p.sendMessage(args[2]);
                return true;
            }
            case "loadb" -> {
                p.getInventory().addItem(NMSHelper.getEmptyItem(Material.GRAY_STAINED_GLASS_PANE));
                p.sendMessage("abc");
                return true;
            }
            case "load" -> {
                p.getInventory().addItem(new ItemManipulator(TemplateHelper.getItemTemplate(args[2])).manifest(true, false, (short) 1, true));
                p.sendMessage("abc");
                return true;
            }
            case "saveinv" -> {
                Bukkit.getScheduler().runTaskLater(Infini.getInstance(), test -> {
                    CustomGUI.PLAYER_MAIN.saveToDatabase(p.getOpenInventory().getTopInventory(), "PLAYER_MAIN");
                    p.sendMessage("DONE");
                }, 5L);
                p.sendMessage(args[2]);
                return true;
            }
            case "loadinv" -> {
                CustomGUI.PLAYER_MAIN.open(p);
                p.sendMessage(args[2]);
                return true;
            }
            case "convert" -> {
                CustomGUI.PLAYER_MAIN.open(p);
                p.sendMessage(args[2]);
                return true;
            }
        }
        return true;
    }
}
