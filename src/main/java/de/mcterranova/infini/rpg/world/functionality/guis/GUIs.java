package de.mcterranova.infini.rpg.world.functionality.guis;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import de.mcterranova.infini.rpgcore.utils.builder.inventory.InventoryBuilder;
import de.mcterranova.infini.rpgcore.utils.builder.item.oldItemBuilder;

public class GUIs {

    public Inventory getInventory()
    {
        return new InventoryBuilder( 6 )
                .setItem( new oldItemBuilder( Material.WRITABLE_BOOK, "§0ABC" ).addNBTTag( "link", "second" ).build(), 22 )
                .fillEmpty( new oldItemBuilder( Material.LIGHT_GRAY_STAINED_GLASS_PANE ).addNBTTag( "protected", "true" ).build() )
                .buildBottomRow()
                .build();
    }
}
