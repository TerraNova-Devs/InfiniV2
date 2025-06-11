package de.mcterranova.infini.rpg.world.functionality.inventory;

import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponentClass;
import de.mcterranova.infini.rpg.world.functionality.items.control.ItemMask;
import org.bukkit.inventory.Inventory;

import java.util.Map;

public record InventoryWrapper(GUITitle id, Map<Integer, ItemMask> contents) {}
