package de.mcterranova.infini.rpg.world.functionality.items.item;

public enum ItemTier {
    O( "§f" ),
    I( "§a" ),
    II( "§9" ),
    III( "§5" ),
    IV( "§6" ),
    V( "§b" ),
    VI("§c");

    private final String color;

    ItemTier( String color )
    {
        this.color = color;
    }

    public String getColor() { return color; }
}
