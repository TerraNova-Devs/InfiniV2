package de.mcterranova.infini.rpg.world.functionality.inventory;

import java.util.Arrays;

public enum GUITitle {
    PLAYER_ACCESSORIES("Accessories"),
    PLAYER_MAIN("Main");

    public final String display;

    GUITitle(String display) {
        this.display = display;
    }

    public String getDisplay() { return this.display; }
    public static GUITitle getByDisplay(String v) { return Arrays.stream(GUITitle.values()).filter(attribute -> attribute.getDisplay().equals(v)).findFirst().get();}
}


