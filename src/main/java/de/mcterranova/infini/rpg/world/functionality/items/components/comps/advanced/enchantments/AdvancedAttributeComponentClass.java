package de.mcterranova.infini.rpg.world.functionality.items.components.comps.advanced.enchantments;

import de.mcterranova.infini.Infini;
import de.mcterranova.infini.rpg.world.functionality.items.components.Attribute;
import de.mcterranova.infini.rpg.world.functionality.items.components.ComponentType;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponentClass;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.UUID;

public class AdvancedAttributeComponentClass extends CustomComponentClass {

    private final String serialized;
    private final Attribute attribute;

    public AdvancedAttributeComponentClass(String serialized, Attribute attribute, EnchantmentCategory... categories) {
        super(ComponentType.ENCHANTMENT, categories);
        this.attribute = attribute;
        this.serialized = serialized;
    }

    @Override
    public int getAttributeBonus(int value, Attribute attribute )
    {
        return value * 15;
    }

    @Override
    public Attribute getAttribute()
    {
        return this.attribute;
    }

    @Override
    public void run( UUID uuid )
    {
        Player p = Infini.getInstance().getServer().getPlayer(uuid);
        World world = p.getWorld();
        Location location = p.getLocation();
        world.spawnEntity(location, EntityType.LIGHTNING_BOLT);
        Infini.getInstance().getServer().broadcastMessage( "FOUND ENCHANTMENT ATTRIBUTE LOLZ");
    }

    @Override
    public String getDisplayName()
    {
        String v = "NULL";
        switch ( attribute )
        {
            case STRENGTH -> v = "Stärke";
            case CRITICAL_DAMAGE -> v =  "Kritisch";
        }
        return v;
    }

    @Override
    public String getColor() {
        return "§9";
    }

    @Override
    public String getSerialized() {
        return serialized;
    }
}
