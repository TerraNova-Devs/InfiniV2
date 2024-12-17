package de.mcterranova.infini.rpg.world.functionality.items.reforging;

public enum Rune {
    NONE("§4NULL"),
    MAGIC("§dRune der Magie"),
    STRENGTH("§cRune der Stärke"),
    SWIFTNESS("§eRune der Macht"),
    INTELLIGENCE("§bRune der Intelligenz");

    private final String translation;

    Rune(String translation )
    {
        this.translation = translation;
    }

    public String getTranslation() { return translation; }
}
