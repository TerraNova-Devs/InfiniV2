package de.mcterranova.infini.rpg.world.entities.player.skills.combat;

import org.bukkit.entity.Entity;
import de.mcterranova.infini.rpg.world.entities.player.skills.ExperienceType;
import de.mcterranova.infini.rpg.world.entities.player.skills.Leveling;

import java.util.UUID;

public class CombatExperience {

    Leveling leveling = new Leveling();

    public void gainExperience( UUID uuid, Entity entity )
    {
        int amount = 0;
        try {
            amount = EntityExperienceYield.valueOf( entity.getName().toUpperCase() ).getYield();
            leveling.gainExperience( uuid, ExperienceType.COMBAT, amount );
        } catch ( Exception ignored ) { }
    }
}
