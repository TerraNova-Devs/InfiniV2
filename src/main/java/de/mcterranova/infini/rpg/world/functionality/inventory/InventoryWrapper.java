package de.mcterranova.infini.rpg.world.functionality.inventory;

import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponentClass;
import de.mcterranova.infini.rpg.world.functionality.items.control.ItemMask;

import java.util.Map;

public record InventoryWrapper(String id, Map<Integer, ItemMask> contents) {}
