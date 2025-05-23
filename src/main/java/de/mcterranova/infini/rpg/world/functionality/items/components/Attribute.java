package de.mcterranova.infini.rpg.world.functionality.items.components;

import java.io.PipedOutputStream;
import java.util.Arrays;

public enum Attribute
{
    DAMAGE("Schaden", 0, "§c"),
    STRENGTH( "Stärke",1, "§c"),
    CRITICAL_CHANCE("Krit. Chance", 2, "§c"),
    CRITICAL_DAMAGE("Krit. Schaden", 3, "§c"),
    ATTACK_SPEED("Bonus Angriffstempo", 4, "§c"),
    INTELLIGENCE("Intelligenz", 5, "§a"),
    HEALTH("Vitalität", 6, "§a"),
    DEFENSE("Rüstung", 7, "§a"),
    MAX_HEALTH("Maximale Vitalität", 8, "§a"),
    MULTIPLICATIVE( "",9, "§a"),
    ADDITIVE( "",10, "§a"),
    NONE("", 11, "§a");

    private final String translation;
    private final int position;
    private final String color;

    Attribute(String translation, int position, String color) {
        this.translation = translation;
        this.position = position;
        this.color = color;
    }

    public String getTranslation() { return translation; }
    public int getPosition() { return position; }
    public static Attribute getByPosition(int position) { return Arrays.stream(Attribute.values()).filter(attribute -> attribute.getPosition() == position).findFirst().get();}
    public String getColor() { return this.color; }
}
