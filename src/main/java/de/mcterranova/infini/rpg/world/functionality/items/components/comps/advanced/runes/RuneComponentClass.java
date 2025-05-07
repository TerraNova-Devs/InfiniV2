package de.mcterranova.infini.rpg.world.functionality.items.components.comps.advanced.runes;

import de.mcterranova.infini.rpg.world.functionality.items.components.Attribute;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponentClass;
import de.mcterranova.infini.rpg.world.functionality.items.components.ComponentType;
import org.checkerframework.checker.units.qual.A;
import org.w3c.dom.Attr;

import java.util.concurrent.atomic.AtomicInteger;

public class RuneComponentClass extends CustomComponentClass {

    private final RuneType type;
    private final String serialized;

    public RuneComponentClass(String serialized, RuneType type) {
        super(ComponentType.RUNE);
        this.type = type;
        this.serialized = serialized;
    }

    @Override
    public String getDisplayName()
    {
        String v = "NULL";
        switch (type)
        {
            case STRENGTH -> v = "Stärke";
            case INTELLIGENCE -> v = "Intelligenz";
            case MIGHT -> v = "Macht";
            case PRECISION -> v = "Präzision";
        }
        return v;
    }

    @Override
    public Attribute getAttribute()
    {
        Attribute v = Attribute.NONE;
        switch (type)
        {
            case STRENGTH -> v = Attribute.STRENGTH;
            case INTELLIGENCE -> v = Attribute.INTELLIGENCE;
            case MIGHT -> v = Attribute.CRITICAL_DAMAGE;
            case PRECISION -> v = Attribute.CRITICAL_CHANCE;
        }
        return v;
    }

    @Override
    public RuneType getRune() {
        return type;
    }

    @Override
    public String getColor() {
        String v = "NULL";
        switch (type)
        {
            case STRENGTH -> v = "§c";
            case INTELLIGENCE -> v = "§b";
            case MIGHT -> v  = "§e";
            case PRECISION -> v = "§6";
        }
        return v;
    }

    @Override
    public int getAttributeBonus(int level, Attribute attribute) {
        return 5*level;
    }

    @Override
    public String getSerialized() {
        return serialized;
    }
}
