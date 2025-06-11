package de.mcterranova.infini.rpg.world.functionality.inventory;

import de.mcterranova.infini.rpg.world.functionality.inventory.under.PlayerAccessories;
import de.mcterranova.infini.rpg.world.functionality.inventory.under.PlayerMain;

public class CustomGUI {

    public static final CustomGUIClass PLAYER_MAIN;
    public static final CustomGUIClass PLAYER_ACCESSORIES;

    static {
        PLAYER_MAIN = CustomGUIClass.register(GUITitle.PLAYER_MAIN, new PlayerMain(6, GUITitle.PLAYER_MAIN));
        PLAYER_ACCESSORIES = CustomGUIClass.register(GUITitle.PLAYER_ACCESSORIES, new PlayerAccessories(6, GUITitle.PLAYER_ACCESSORIES));
    }
}
