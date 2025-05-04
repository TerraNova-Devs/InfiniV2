package de.mcterranova.infini.rpg.test.commands;

import de.mcterranova.infini.rpg.world.functionality.builder.item.CustomItemBuilder;
import de.mcterranova.terranovaLib.commands.CommandAnnotation;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ItemCommand {

    @CommandAnnotation(
            domain = "item.$A",
            description = "test command for items",
            usage = "/Testcommand item <spawn|disable>"
    )

    public static boolean a(Player p, String[] args) {
        switch (args[1]) {
            case "spawn" -> {
                p.getInventory().addItem(new CustomItemBuilder(Material.DIAMOND).build());
                return true;
            }
            case "disable" -> {
                p.sendMessage("abc");
                return true;
            }
        }
        return true;
    }
}
