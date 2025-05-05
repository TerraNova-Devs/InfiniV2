package de.mcterranova.infini.rpg.world.functionality.items.components;

import java.io.PipedOutputStream;

public enum Attribute
{
    DAMAGE("Schaden", 0),
    STRENGTH( "Stärke",1),
    CRITICAL_CHANCE("Krit. Chance", 2),
    CRITICAL_DAMAGE("Krit. Schaden", 3),
    ATTACK_SPEED("Bonus Angriffstempo", 4),
    INTELLIGENCE("Intelligenz", 5),
    HEALTH("Vitalität", 6),
    DEFENSE("Rüstung", 7),
    MAX_HEALTH("Maximale Vitalität", 8),
    MULTIPLICATIVE( "",9),
    ADDITIVE( "",10),
    NONE("", 11);

    private final String translation;
    private final int position;

    Attribute(String translation, int position) {
        this.translation = translation;
        this.position = position;
    }

    public String getTranslation() { return translation; }
    public int getPosition() { return position; }
}
