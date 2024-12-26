package de.mcterranova.infini.rpg.world.functionality.items.control;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import de.mcterranova.infini.rpgcore.utils.builder.item.ItemBuilder;
import de.mcterranova.infini.rpg.world.functionality.Attribute;
import de.mcterranova.infini.rpg.world.functionality.items.components.comps.basic.storage.item.ItemTier;
import de.mcterranova.infini.rpg.world.functionality.items.enchanting.CustomEnchantment;

import java.util.HashMap;

public class OldItemManipulator {

    private final ItemMask itemMask;
    private final ItemArchive archive = ItemArchive.get();
    private final HashMap< ItemMask, Boolean > queue = new HashMap<>();
    private Player player;
    private boolean destroy = false;
    private boolean isGlowing = false;

    public OldItemManipulator(ItemMask itemMask )
    {
        this.itemMask = itemMask;
    }

    public OldItemManipulator(ItemStack itemStack )
    {
        this.itemMask = archive.get( itemStack );
    }

    public OldItemManipulator(ItemStack itemStack, Player player )
    {
        this.player = player;
        this.itemMask = archive.get( itemStack );
    }

    public OldItemManipulator addRune(Rune rune)
    {
        itemMask.getRunes().add(rune);
        queue.put(itemMask, false);
        return this;
    }

    public OldItemManipulator setTier(ItemTier tier )
    {
        itemMask.setTier( tier );
        queue.put(itemMask, false);
        return this;
    }

    public OldItemManipulator removeRune(Rune rune )
    {
        itemMask.getRunes().remove(rune);
        queue.put(itemMask, false);
        return this;
    }

    public OldItemManipulator addEnchant(CustomEnchantment enchantment, int v )
    {
        if ( !itemMask.getEnchantments().containsKey( enchantment ) )
        {
            itemMask.getEnchantments().put( enchantment, v );
            this.isGlowing = true;
        }
        queue.put(itemMask, false);
        return this;
    }

    public OldItemManipulator levelUpEnchant(CustomEnchantment enchantment, int v )
    {
        if ( !itemMask.getEnchantments().containsKey( enchantment ) )
            return this;
        int v1 = v + itemMask.getEnchantments().get( enchantment );
        itemMask.getEnchantments().put( enchantment, v1 );
        queue.put(itemMask, false);
        return this;
    }

    public OldItemManipulator removeEnchant(CustomEnchantment enchantment )
    {
        itemMask.getEnchantments().remove( enchantment );
        queue.put(itemMask, false);
        return this;
    }

    public OldItemManipulator addAttribute(Attribute attribute, int v )
    {
        itemMask.getAttributes().put( attribute, v );
        queue.put(itemMask, false);
        return this;
    }

    public OldItemManipulator setAttribute(Attribute attribute, int v )
    {
        itemMask.getAttributes().replace( attribute, v );
        queue.put(itemMask, false);
        return this;
    }

    public OldItemManipulator destroy()
    {
        this.destroy = true;
        this.player.getInventory().getItemInMainHand().setAmount( 0 );
        queue.put( itemMask, true );
        return this;
    }

    public void queue()
    {
        if ( !destroy ) {
            player.getServer().broadcastMessage(itemMask.getDescription());
            player.getInventory().setItemInMainHand( new ItemBuilder( itemMask ).setGlowing( isGlowing ).build() );
        }

        for ( ItemMask mask : queue.keySet() )
        {
            if ( archive.contains( mask ) )
                archive.update( mask );

            if ( queue.get( mask ) )
                archive.remove( mask );
        }
    }
}
