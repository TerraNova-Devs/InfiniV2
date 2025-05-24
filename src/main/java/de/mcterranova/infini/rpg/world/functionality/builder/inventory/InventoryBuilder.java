package de.mcterranova.infini.rpg.world.functionality.builder.inventory;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class InventoryBuilder {

    private final Inventory inventory;

    public InventoryBuilder(int rows) {
        this.inventory = Bukkit.createInventory(null, rowMath(rows));
    }

    private int rowMath(int math) {
        if ((math * 9)> 54) math = 54;
        return math * 9;
    }
}
