package de.mcterranova.infini.rpg.listeners;

import de.mcterranova.infini.rpg.utils.NBTUtils;
import de.mcterranova.infini.rpg.world.entities.mob.control.CustomType;
import de.mcterranova.infini.current.rpg.world.functionality.items.control.ItemManipulator;
import de.mcterranova.infini.rpg.world.functionality.items.enchanting.EnchantmentLibrary;
import de.mcterranova.infini.rpg.world.functionality.spells.Element;
import net.leonardo_dgs.interactivebooks.IBook;
import net.leonardo_dgs.interactivebooks.InteractiveBooks;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import de.mcterranova.infini.Infini;
import de.mcterranova.infini.rpg.utils.InteractHelper;
import de.mcterranova.infini.current.rpg.world.functionality.builder.mob.CustomEntityBuilder;
import de.mcterranova.infini.rpg.utils.worldedit.InfinitumSchematic;
import de.mcterranova.infini.rpg.utils.worldedit.WorldEditHelper;
import de.mcterranova.infini.rpg.world.entities.mob.control.EntityManipulator;
import de.mcterranova.infini.rpg.world.entities.loot.CustomLootTable;
import de.mcterranova.infini.rpg.world.functionality.crafting.Crafting;
import de.mcterranova.infini.rpg.world.functionality.crafting.stations.Primer;
import de.mcterranova.infini.rpg.world.functionality.items.componentsold.CustomItemTemplate;

public class Interact implements Listener {

    Crafting crafting = Crafting.get();

    @EventHandler
    public void onInteract( PlayerInteractEvent e )
    {
        InteractHelper helper = new InteractHelper();
        Player player = e.getPlayer();

        if ( helper.rightClickBlockWithItem( e, Material.LAVA_CAULDRON, e.getItem(), "RUNE_OF_POWER" ) )
        {
            Location loc = e.getClickedBlock().getLocation();
            Crafting.get().initiateCauldronCrafting( loc, Primer.RUNE, player, true );
        }

        if ( helper.rightClickBlockWithItem( e, Material.LAVA_CAULDRON, e.getItem(), "INFINITA_SCIENTIA" ) )
        {
            Location loc = e.getClickedBlock().getLocation();
            Crafting.get().initiateCauldronCrafting( loc, Primer.BOOK, player, false );
        }

        if ( helper.rightClickBlockWithItem( e, Material.CARTOGRAPHY_TABLE, e.getItem(), "FRAGMENT_OF_REALITY" ) )
        {
            e.setCancelled( true );

            player.playSound( player.getLocation(), Sound.BLOCK_BELL_RESONATE, 1000, 3);
            player.playSound( player.getLocation(), Sound.BLOCK_BELL_RESONATE, 1000, 2);

            final Location location = WorldEditHelper.fixTableLocation( e.getClickedBlock().getLocation(), player.getFacing() );

            Bukkit.getScheduler().scheduleSyncDelayedTask( Infini.getInstance(), () ->
                    WorldEditHelper.Paste( location , InfinitumSchematic.TABLE.getPath(), player ), 19 );
        }

        if ( helper.rightClickBlock( e, Material.DIAMOND_BLOCK ) )
        {
            player.sendMessage( "testing" );
            new EntityManipulator( new CustomEntityBuilder( EntityType.ZOMBIE, player.getWorld(), player.getLocation() )
                        .health( 250000 )
                        .maxHealth( 250000 )
                        .name( "§6Golden Ghoul")
                        .assignLootTable( CustomLootTable.ENDER_DRAGON )
                        .type(CustomType.MOB)
                        .element(Element.UNDEAD)
                        .build() )
                    .equip( new ItemStack( Material.GOLDEN_CHESTPLATE ), EquipmentSlot.CHEST )
                    .equip( new ItemStack( Material.GOLDEN_LEGGINGS ), EquipmentSlot.LEGS )
                    .equip( new ItemStack( Material.GOLDEN_BOOTS ), EquipmentSlot.FEET )
                    .equip( new ItemStack( Material.GOLDEN_SWORD ), EquipmentSlot.HAND )
                    .queue();
            for ( int i = 0; i < 10; i++ ) {

            }

            // Infinitum.getInstance().getVanillaCoolDowns().addCooldown( Item.byId( 0 ), 60 );


        }

        if (helper.rightClickBlock(e, Material.CLAY)) {
            new ItemManipulator(Material.BAKED_POTATO, "test_item")
                    .updateItem(e.getPlayer())
                    .queue();
        }

        if ( helper.rightClickBlock( e, Material.IRON_BLOCK ) )
        {
            new CustomEntityBuilder(EntityType.CREEPER, player.getWorld(), player.getLocation())
                    .health(999999999)
                    .maxHealth(999999999)
                    .name("§8Reaper")
                    .assignLootTable(CustomLootTable.WITHER)
                    .type(CustomType.MOB)
                    .element(Element.CUBOID)
                    .build();
            NBTUtils nbt = new NBTUtils();
            player.getServer().broadcastMessage(""+ nbt.getItemNBTTags(player.getEquipment().getItemInMainHand()));
        }

        if ( helper.rightClickBlock( e, Material.GOLD_BLOCK ) )
        {
            player.getInventory().setItemInMainHand( new oldItemBuilder( CustomItemTemplate.HYPERION )
                    .setRuneSlots(3)
                    .setRune(Rune.STRENGTH)
                    .setRune(Rune.MAGIC)
                    .setRune(Rune.SWIFTNESS)
                    .setGlowing()
                    //abasdasd
                    .build()
            );
        }

        if ( helper.rightClickBlock( e, Material.NETHERITE_BLOCK ) )
        {
            new OldItemManipulator( e.getItem(), e.getPlayer() )
                    .addEnchant(EnchantmentLibrary.DAMAGE_CUBOID, 3)
                    .addEnchant(EnchantmentLibrary.DAMAGE_ALL, 7)
                    .addEnchant(EnchantmentLibrary.DAMAGE_UNDEAD, 8)
                    .addEnchant(EnchantmentLibrary.DAMAGE_ARACHNID, 3)
                    .addEnchant(EnchantmentLibrary.PROTECTION_ALL, 5)
                    .addEnchant(EnchantmentLibrary.ATTRIBUTE_STRENGTH, 10)
                    .addEnchant(EnchantmentLibrary.ATTRIBUTE_CRITICAL_DAMAGE, 2)
                    .queue();
            Infini.getInstance().getServer().broadcastMessage( " clicked lol " );
        }

        if ( helper.rightClickWithItem( e, e.getItem(), "INFINITA_SCIENTIA" ) )
        {
            IBook iBook = InteractiveBooks.getBook( "start" );
            iBook.open( player );
        }
    }
}
