package de.mcterranova.infini.rpg.world.functionality.items.components.comps.advanced.runes;

public enum RuneType {
    STRENGTH(1),
    INTELLIGENCE(4),
    MIGHT(3),
    PRECISION(2);

    private final int pos;

    RuneType(int v) {
        this.pos = v;
    }

    public int getPos() { return pos; }
}
