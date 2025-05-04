package de.mcterranova.infini.rpg.world.entities.loot;

import org.bukkit.Location;
import org.bukkit.World;
import de.mcterranova.infini.Infini;

import java.util.Map;

public class LootController{

    /*
    public void generateItem( Location location, CustomItemTemplate item )
    {
        World world = location.getWorld();

        world.dropItem( location, new oldItemBuilder( item ).setGlowing().build() );


    }*/

    public void generateLoot( Location location, CustomLootTable lootTable )
    {
        if ( lootTable == null )
            return;
/*
        Map<CustomItemTemplate, Integer > contents = lootTable.get();

        for ( CustomItemTemplate item : contents.keySet() )
            if ( contents.get( item ) > Infini.getInstance().getRandomGenerator().nextInt( 100 ) )
                generateItem( location, item );

 */
    }
}
