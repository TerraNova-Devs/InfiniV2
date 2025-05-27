package de.mcterranova.infini.rpg.world.functionality.builder.item;

import com.sk89q.worldedit.antlr.ExpressionParser;
import de.mcterranova.infini.rpg.utils.TextUtils;
import de.mcterranova.infini.rpg.world.functionality.items.components.Attribute;
import de.mcterranova.infini.rpg.world.functionality.items.components.ComponentType;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponent;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponentClass;
import de.mcterranova.infini.rpg.world.functionality.items.components.comps.advanced.runes.RuneWrapper;
import de.mcterranova.infini.rpg.world.functionality.items.item.ItemCategory;
import de.mcterranova.infini.rpg.world.functionality.items.item.ItemClass;
import de.mcterranova.infini.rpg.world.functionality.items.item.ItemTier;
import de.mcterranova.infini.rpg.world.functionality.items.control.ItemMask;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;

import java.util.*;

public class LoreBuilder {
    public final Map<CustomComponentClass, String> data = new HashMap<>();
    public final Map<CustomComponentClass, Double> attributes = new HashMap<>();
    public List<RuneWrapper> runes;
    private final Map<Attribute, Integer> additionalAttributes = new HashMap<>();

    private final Map<CustomComponentClass, Integer> runeSlots = new HashMap<>();
    private final Map<CustomComponentClass, Double> enchantments = new HashMap<>();
    private final Map<CustomComponentClass, Double> basicAttributes = new HashMap<>();

    private final ItemCategory itemCategory;
    private final ItemClass itemClass;
    private final ItemTier itemTier;
    private final String description;

    private boolean addAttributes;



    // rework with new item library and maybe add item masks like entity masks to get the attributes n shit for update purposes

    public LoreBuilder(ItemMask item, boolean addAttributes)
    {
        this.data.putAll(item.data);
        this.attributes.putAll(item.attributes);
        this.attributes.keySet().stream().filter(component -> component.getType().equals(ComponentType.ATTRIBUTE)).forEach(component -> this.basicAttributes.put(component, this.attributes.get(component)));
        this.itemTier = ItemTier.valueOf(data.get(CustomComponent.ITEM_TIER));
        this.itemCategory = ItemCategory.valueOf(data.get(CustomComponent.ITEM_CATEGORY));
        this.itemClass = ItemClass.valueOf(data.get(CustomComponent.ITEM_CLASS));
        this.runes = item.runes;
        this.data.keySet().stream().filter(component -> component.getType().equals(ComponentType.STORAGE)).filter(component -> component.getDeclaration().equals("RUNE_SLOTS")).forEach(component -> this.runeSlots.put(component, Integer.valueOf(this.data.get(component))));
        this.attributes.keySet().stream().filter(component -> component.getType().equals(ComponentType.ENCHANTMENT)).forEach(component -> this.enchantments.put(component, this.attributes.get(component)));
        this.description = this.data.get(CustomComponent.DESCRIPTION);
        this.addAttributes = addAttributes;
    }

    public List<Component> buildBlank() {
        List<Component> newLore = new ArrayList<>();
        return newLore;
    }

    public List<Component> build(int paragraphSize) {
        List<Component> newLore = new ArrayList<>();
        Component blank = Component.text("§0");
        for (int i = 0; i < Attribute.values().length; i++)
            additionalAttributes.put(Attribute.getByPosition(i), 0);
        if (!enchantments.isEmpty()) {
            this.enchantments.keySet().stream().filter(enchant -> enchant.getAttribute() != null).forEach(enchant -> additionalAttributes.put(enchant.getAttribute(), enchant.getAttributeBonus((int) Math.round(this.enchantments.get(enchant)), enchant.getAttribute()) + additionalAttributes.get(enchant.getAttribute())));
        }
        if (!runes.isEmpty()) { 
            this.runes.forEach(wrapper -> additionalAttributes.put(wrapper.rune().getAttribute(), wrapper.rune().getAttributeBonus(wrapper.level(), wrapper.rune().getAttribute()) + additionalAttributes.get(wrapper.rune().getAttribute())));
        }

        if (this.basicAttributes.isEmpty())
            this.addAttributes = false;

        if (addAttributes) {
            List<Attribute> attributes = new ArrayList<>(Collections.nCopies(15, null));
            List<Double> values = new ArrayList<>(Collections.nCopies(15, null));

            this.basicAttributes.keySet().forEach(attribute -> {
                attributes.set(attribute.getAttribute().getPosition(), attribute.getAttribute());
                values.set(attribute.getAttribute().getPosition(), this.basicAttributes.get(attribute));
            });
            additionalAttributes.keySet().forEach(attribute -> {
                if (!attributes.contains(attribute) && additionalAttributes.get(attribute) != 0) {
                    attributes.set(attribute.getPosition(), attribute);
                    values.set(attributes.indexOf(attribute), 0d);
                }
            });
            attributes.forEach(attribute -> {
                String additional = "";
                int v;
                String v2;
                if (attribute != null) {
                    v = this.additionalAttributes.get(attribute);
                    v2 = String.valueOf(values.get(attributes.indexOf(attribute)) + v);
                    if (!attribute.equals(Attribute.ATTACK_SPEED)) {
                        v2 = v2.substring(0, v2.length() - 2);
                    }
                    if (v != 0)
                        additional = " §9(" + this.additionalAttributes.get(attribute) + ")";
                    newLore.add(Component.text("§7" + attribute.getTranslation() + ": " + attribute.getColor() + "+"+ v2 + additional));
                }
            });
            newLore.add(blank);

            if (!runeSlots.isEmpty()) {
                ArrayList<CustomComponentClass> temporary = new ArrayList<>();
                int slots = runeSlots.get(CustomComponent.RUNE_SLOTS);
                runes.forEach(rune -> temporary.add(rune.rune()));
                for (int i = 0; i < slots - runes.size(); i++) {
                    temporary.add(null);
                }
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
            if (enchantments.size() < 6) {
                this.enchantments.keySet().forEach(enchantment -> {
                    String v = this.enchantments.get(enchantment).toString().substring(0, this.enchantments.get(enchantment).toString().length() - 2);
                    String temp = enchantment.getColor() + enchantment.getDisplayName() + " " + v;
                    newLore.add(Component.text(temp.replace("$", "").replace("%", " ")));
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

        if (!description.isBlank()) {
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