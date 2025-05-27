package de.mcterranova.infini.rpg.world.functionality.inventory.under;

import de.mcterranova.infini.rpg.database.content.customserialization.CustomSerializable;
import de.mcterranova.infini.rpg.database.content.templates.TemplateHelper;
import de.mcterranova.infini.rpg.utils.NBTUtils;
import de.mcterranova.infini.rpg.world.functionality.inventory.CustomGUIClass;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

public class PlayerInfo extends CustomGUIClass implements CustomSerializable, Listener {

    private final Inventory inventory;
    private final String id;
    private final int size;

    public PlayerInfo(int rows, String id) {
        super(rows, id);
        this.size = Math.min((rows * 9), 54);
        this.inventory = Bukkit.createInventory(null, size, Component.text(id));
        this.id = id;
        this.inventory.setContents(prepInventory().getContents());
    }

    private Inventory prepInventory() {
        return TemplateHelper.get().getInventoryTemplate(id);
    }

    @Override
    public String serialize() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.size).append("%").append(id).append("%");

        return "";
    }

    @Override
    public void processClick(InventoryClickEvent event) {
        NBTUtils.getNBTTag(event.getRawSlot())
    }

    @Override
    public void open(Player player) {
        if (this.hasOpened(player)) {
            Bukkit.getPluginManager().callEvent(new InventoryCloseEvent(player.getOpenInventory()));
            this.unListPlayer(player);
        }
        this.listPlayer(player, id);
        player.openInventory(inventory);
    }
}
