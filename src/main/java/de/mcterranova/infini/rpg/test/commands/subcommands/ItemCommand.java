package de.mcterranova.infini.rpg.test.commands.subcommands;

import de.mcterranova.infini.Infini;
import de.mcterranova.infini.rpg.database.content.DatabaseHelper;
import de.mcterranova.infini.rpg.nms.NMSHelper;
import de.mcterranova.infini.rpg.world.functionality.inventory.CustomGUI;
import de.mcterranova.infini.rpg.world.functionality.inventory.GUITitle;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponent;
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
                DatabaseHelper.saveItemTemplate(new ItemManipulator(Material.BLACK_STAINED_GLASS_PANE, "BLANK_2")
                                .addData(CustomComponent.DISPLAY_NAME, "NULL")
                                .addData(CustomComponent.ITEM_TIER, ItemTier.O.name())
                                .addData(CustomComponent.ITEM_CLASS, ItemClass.GENERIC.name())
                                .addData(CustomComponent.ITEM_CATEGORY, ItemCategory.MENU_ITEM_EMPTY.name())
                                .addData(CustomComponent.INVENTORY_LINK, "PLAYER_ACCESSORIES")
                        .getItemMask());
                p.sendMessage(args[2]);
                return true;
            }
            case "load" -> {
                p.getInventory().addItem(new ItemManipulator(DatabaseHelper.getItemTemplate(args[2])).manifest(true, false, (short) 1, true));
                p.sendMessage("abc");
                return true;
            }
            case "saveinv" -> {
                Bukkit.getScheduler().runTaskLater(Infini.getInstance(), test -> {
                    CustomGUI.PLAYER_MAIN.saveToDatabase(p.getOpenInventory().getTopInventory(), GUITitle.PLAYER_MAIN);
                    p.sendMessage("DONE");
                }, 40L);
                p.sendMessage(args[2]);
                return true;
            }
            case "loadinv" -> {
                CustomGUI.PLAYER_MAIN.open(p);
                p.sendMessage(args[2]);
                return true;
            }
            case "convert" -> {
                p.sendMessage(args[2]);
                return true;
            }
        }
        return true;
    }
}
