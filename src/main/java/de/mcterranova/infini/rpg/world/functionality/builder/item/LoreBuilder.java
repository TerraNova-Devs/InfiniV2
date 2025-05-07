package de.mcterranova.infini.rpg.world.functionality.builder.item;

import de.mcterranova.infini.rpg.utils.TextUtils;
import de.mcterranova.infini.rpg.world.functionality.items.components.Attribute;
import de.mcterranova.infini.rpg.world.functionality.items.components.ComponentType;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponent;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponentClass;
import de.mcterranova.infini.rpg.world.functionality.items.item.ItemCategory;
import de.mcterranova.infini.rpg.world.functionality.items.item.ItemClass;
import de.mcterranova.infini.rpg.world.functionality.items.item.ItemTier;
import de.mcterranova.infini.rpg.world.functionality.items.control.ItemMask;
import net.kyori.adventure.text.Component;

import java.util.*;

public class LoreBuilder {
    public final Map<CustomComponentClass, String> data = new HashMap<>();
    public final Map<CustomComponentClass, Integer> attributes = new HashMap<>();

    private final Map<CustomComponentClass, Integer> runeSlots = new HashMap<>();
    private final Map<CustomComponentClass, Integer> runes = new HashMap<>();
    private final Map<CustomComponentClass, Integer> enchantments = new HashMap<>();
    private final Map<CustomComponentClass, Integer> basicAttributes = new HashMap<>();

    private final ItemCategory itemCategory;
    private final ItemClass itemClass;
    private final ItemTier itemTier;
    private final String description;

    private boolean compactEnchantments = true;
    private boolean addAttributes;
    private boolean addDescription = true;



    // rework with new item library and maybe add item masks like entity masks to get the attributes n shit for update purposes

    public LoreBuilder(ItemMask item, boolean addAttributes)
    {
        this.data.putAll(item.data);
        this.attributes.putAll(item.attributes);
        this.attributes.keySet().stream().filter(component -> component.getType().equals(ComponentType.ATTRIBUTE)).forEach(component -> this.basicAttributes.put(component, this.attributes.get(component)));
        this.itemTier = ItemTier.valueOf(data.get(CustomComponent.ITEM_TIER));
        this.itemCategory = ItemCategory.valueOf(data.get(CustomComponent.ITEM_CATEGORY));
        this.itemClass = ItemClass.valueOf(data.get(CustomComponent.ITEM_CLASS));
        this.attributes.keySet().stream().filter(component -> component.getType().equals(ComponentType.RUNE)).forEach(component -> this.runes.put(component, this.attributes.get(component)));
        this.data.keySet().stream().filter(component -> component.getType().equals(ComponentType.STORAGE)).filter(component -> component.getDeclaration().equals("RUNE_SLOTS")).forEach(component -> this.runeSlots.put(component, Integer.valueOf(this.data.get(component))));
        this.attributes.keySet().stream().filter(component -> component.getType().equals(ComponentType.ENCHANTMENT)).forEach(component -> this.enchantments.put(component, this.attributes.get(component)));
        this.description = this.data.get(CustomComponent.DESCRIPTION);
        this.addAttributes = addAttributes;
        if (description.isBlank())
            this.addDescription = false;
        if (enchantments.isEmpty())
            return;
        if (enchantments.size() > 6)
            this.compactEnchantments = false;
    }



    public List<Component> build(int paragraphSize) {

        List<Component> newLore = new ArrayList<>();
        Component blank = Component.text("§0");

        if (this.basicAttributes.isEmpty())
            this.addAttributes = false;

        if (addAttributes) {
            List<CustomComponentClass> attributes = new ArrayList<>();
            List<Integer> values = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                attributes.add(null);
                values.add(null);
            }
            this.basicAttributes.keySet().forEach(attribute -> attributes.add(attribute.getAttribute().getPosition(), attribute));
            this.basicAttributes.keySet().forEach(attribute -> values.add(attribute.getAttribute().getPosition(), this.basicAttributes.get(attribute)));
            for (int i = 0; i < attributes.size(); i++) {
                CustomComponentClass attribute = attributes.get(i);
                if (attribute == null)
                    continue;
                newLore.add(Component.text("§7" + attribute.getAttribute().getTranslation() + ": " + attribute.getColor() + "+"+ values.get(i)));
            }
            newLore.add(blank);

            if (!runeSlots.isEmpty()) {
                ArrayList<CustomComponentClass> temporary = new ArrayList<>();
                for (int i = 0; i <= runeSlots.get(CustomComponent.RUNE_SLOTS); i++) {
                    temporary.add(null);
                }
                runes.keySet().forEach(rune -> temporary.add(rune.getRune().getPos(), rune));
                temporary.removeIf(Objects::isNull);
                for (int i = 0; i <= runeSlots.get(CustomComponent.RUNE_SLOTS) - runes.size(); i++) {
                    temporary.add(null);
                }
                //add them in the order, remove all empty ones, add empty ones back
                temporary.forEach(component -> {
                    if (component != null)
                        newLore.add(Component.text("§7[ "+ component.getColor() + "Rune der " + component.getDisplayName() + " §7]"));
                    else
                        newLore.add(Component.text("§7[ - ]"));
                });
                newLore.add(blank);
            }
        }
        /*
           "§8[ §f- §8]" placeholder
           this.newLore.add("§8[ " + rune.getTranslation() + " §8]");
        */

        if (!enchantments.isEmpty()) {
            if (compactEnchantments) {
                this.enchantments.keySet().forEach(enchantment -> {
                    String temp = enchantment.getColor() + enchantment.getDisplayName() + " " + this.enchantments.get(enchantment);
                    newLore.add(Component.text(temp.replace("$", "")));
                });

                //newLore.add( "§9" + single.replace("$", " ") );
            } else {
                String enchantmentNames = "";
                StringBuilder builder = new StringBuilder(enchantmentNames);
                this.enchantments.keySet().forEach(enchantment -> builder.append(enchantment.getDisplayName()).append("$").append(this.enchantments.get(enchantment)).append(","));
                newLore.addAll(TextUtils.prepEnchantments(builder.toString(), paragraphSize, "§9"));
            }
            newLore.add(blank);
        }

        if (addDescription) {
            newLore.addAll(TextUtils.prepDescription(this.description, paragraphSize, "§7"));
            newLore.add(blank);
        }


        //TODO change color if you can use it

        if (addAttributes && itemClass != null)
            newLore.add(Component.text("§a" + itemClass.getTranslation()));

        // show how well it was crafted

        if (itemTier != null && itemCategory != null)
            newLore.add(Component.text("§f§lSTUFE " + itemTier.getColor() + "§l" + itemTier.name() + " §f§l" + itemCategory.getTranslation().toUpperCase()));

        return newLore;
    }
}
