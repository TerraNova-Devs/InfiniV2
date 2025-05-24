package de.mcterranova.infini.rpg.test.commands.subcommands;

import de.mcterranova.infini.rpg.database.content.templates.TemplateHelper;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponent;
import de.mcterranova.infini.rpg.world.functionality.items.components.comps.advanced.runes.RuneWrapper;
import de.mcterranova.infini.rpg.world.functionality.items.control.ItemManipulator;
import de.mcterranova.infini.rpg.world.functionality.items.control.ItemMask;
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
                TemplateHelper.get().saveTemplate(new ItemMask(Material.LIGHT_GRAY_STAINED_GLASS_PANE, "BLANK_0"));
                p.sendMessage(args[2]);
                return true;
            }
            case "loadb" -> {
                p.getInventory().addItem(new ItemManipulator(TemplateHelper.get().getBlank(args[2])).getBlank());
                p.sendMessage("abc");
                return true;
            }
            case "load" -> {
                p.getInventory().addItem(new ItemManipulator(TemplateHelper.get().getTemplate(args[2])).manifest(true, true, (short) 1, true));
                p.sendMessage("abc");
                return true;
            }
        }
        return true;
    }
}
