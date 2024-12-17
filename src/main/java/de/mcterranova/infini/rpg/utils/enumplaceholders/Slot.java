package de.mcterranova.infini.rpg.utils.enumplaceholders;

public enum Slot {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9);

    private int v;

    Slot(int v){
        this.v = v;
    }

    public int getValue() { return v; }
}
