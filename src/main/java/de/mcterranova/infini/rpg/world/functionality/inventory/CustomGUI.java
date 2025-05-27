package de.mcterranova.infini.rpg.world.functionality.inventory;

import de.mcterranova.infini.rpg.world.functionality.inventory.under.PlayerInfo;

public class CustomGUI {

    public static final CustomGUIClass PLAYER_INFO;

    static {
        PLAYER_INFO = CustomGUIClass.register("PLAYER_INFO", new PlayerInfo(6, "PLAYER_INFO"));
    }
}
