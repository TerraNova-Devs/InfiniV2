package de.mcterranova.infini.rpg.world.functionality.inventory;

import de.mcterranova.infini.rpg.world.functionality.inventory.under.PlayerAccessories;
import de.mcterranova.infini.rpg.world.functionality.inventory.under.PlayerMain;

public class CustomGUI {

    public static final CustomGUIClass PLAYER_MAIN;
    public static final CustomGUIClass PLAYER_ACCESSORIES;

    static {
        PLAYER_MAIN = CustomGUIClass.register("PLAYER_MAIN", new PlayerMain(6, "PLAYER_MAIN"));
        PLAYER_ACCESSORIES = CustomGUIClass.register("PLAYER_ACCESSORIES", new PlayerAccessories(6, "PLAYER_ACCESSORIES"));
    }
}
