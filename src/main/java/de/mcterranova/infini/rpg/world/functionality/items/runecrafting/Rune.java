package de.mcterranova.infini.rpg.world.functionality.items.runecrafting;

import de.mcterranova.infini.rpg.world.functionality.Attribute;

public enum Rune {
    NONE("§4NULL", 0, Attribute.DEFENSE),
    MAGIC("§dRune der Magie", 10, Attribute.INTELLIGENCE),
    STRENGTH("§cRune der Stärke", 0, Attribute.DEFENSE),
    SWIFTNESS("§eRune der Macht", 0, Attribute.DEFENSE),
    INTELLIGENCE("§bRune der Intelligenz", 0, Attribute.DEFENSE);

    private final String translation;

    Rune(String translation, int value, Attribute attribute )
    {
        this.translation = translation;
    }

    public String getTranslation() { return translation; }
}
