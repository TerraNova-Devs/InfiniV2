package de.mcterranova.infini.rpgcore.utils.builder.mob;

import de.mcterranova.infini.rpg.world.entities.mob.control.CustomType;
import de.mcterranova.infini.rpg.world.entities.mob.control.EntityConnector;
import de.mcterranova.infini.rpg.world.entities.mob.control.EntityManipulator;
import de.mcterranova.infini.rpg.world.entities.mob.control.EntityMask;
import de.mcterranova.infini.rpg.world.functionality.spells.Element;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import de.mcterranova.infini.Infini;

import java.util.ArrayList;
import java.util.Random;

public class CustomEntityBuilderUtils {

    ArrayList< String > colorList;

    public CustomEntityBuilderUtils()
    {
        colorList = new ArrayList<>();
        colorList.add( "§f");
        colorList.add( "§f");
        colorList.add( "§e");
        colorList.add( "§6");
        colorList.add( "§c");
        colorList.add( "§c");
        colorList.add( "§f");
        colorList.add( "§f");
        colorList.add( "§e");
        colorList.add( "§6");
        colorList.add( "§c");
        colorList.add( "§c");
        colorList.add( "§f");
        colorList.add( "§f");
        colorList.add( "§e");
        colorList.add( "§6");
        colorList.add( "§c");
        colorList.add( "§c");
    }

    public ArmorStand createPlaceholderAt( World world, Location location )
    {
        return new CustomEntityBuilder( EntityType.ARMOR_STAND, world, location )
                .getPlaceHolder();
    }

    public void createDamageTag( boolean critical, int v, World world, Location location )
    {
        Random random = Infini.getInstance().getRandomGenerator();
        double newX = ( location.getX() - 0.5 ) + ( ( double ) random.nextInt( 10 ) / 10 );
        double newY = ( location.getY() + ( 0.35 + ( ( double ) random.nextInt( 8 ) / 10 )));
        double newZ = ( location.getZ() - 0.5 ) + ( ( double ) random.nextInt( 10 ) / 10 );

        String uncolored = "✵" + v + "✵";
        StringBuilder criticalString = new StringBuilder();
        String damageString = "§7" + v;
        if ( critical )
        {
            int i = 0;

            for ( char character : uncolored.toCharArray() )
            {
                criticalString.append( colorList.get( i ) ).append( character );
                i++;
            }
            damageString = criticalString.toString();
        }

        ArmorStand armorStand = new CustomEntityBuilder( EntityType.ARMOR_STAND, world, new Location( world, 0, 200, 0 ) )
                .placeHolderName( damageString )
                .getPlaceHolder();
        EntityConnector.get().add(new EntityMask("damage_tag", armorStand, CustomType.SYSTEM, Element.NONE, 0, null, null, null, null));
        new EntityManipulator( armorStand )
                .teleport(new Location(world, newX, newY, newZ))
                .queue();
        Bukkit.getScheduler().runTaskLater( Infini.getInstance(), armorStand::remove, 10 );
    }

    public static CustomEntityBuilderUtils get() { return Infini.getInstance().getBuilderUtils(); }
}
