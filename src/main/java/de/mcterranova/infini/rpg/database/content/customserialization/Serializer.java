package de.mcterranova.infini.rpg.database.content.customserialization;

import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponent;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponentClass;
import de.mcterranova.infini.rpg.world.functionality.items.components.comps.advanced.runes.RuneWrapper;
import de.mcterranova.infini.rpg.world.functionality.items.control.ItemMask;
import org.bukkit.Material;
import org.checkerframework.common.value.qual.ArrayLen;

import java.util.*;

public class Serializer {

    public static String serializeItemMask(ItemMask mask) {
        Map<CustomComponentClass, Integer> attributes = mask.attributes;
        Map<CustomComponentClass, String> data = mask.data;
        List<RuneWrapper> runes = mask.runes;
        StringBuilder builder = new StringBuilder();
        attributes.keySet().forEach(attribute -> builder.append(attribute.getSerialized()).append(",").append(attributes.get(attribute)).append("$"));
        builder.append("%");
        data.keySet().forEach(component -> builder.append(component.getSerialized()).append(",").append(data.get(component)).append("$"));
        builder.append("%");
        runes.forEach(rune -> builder.append(rune.rune().getSerialized()).append(",").append(rune.level()).append("$"));
        return builder.toString();
    }

    public static ItemMask deserializeBlank(String serialized) {
        Map<CustomComponentClass, String> data = new HashMap<>();
        String[] array = serialized.split("%");
        Arrays.stream(array[1].split("\\$")).forEach(string -> {
            String[] substring = string.split(",");
            data.put(CustomComponentClass.deSerialize(substring[0]), substring[1]);
        });
        return new ItemMask(Material.valueOf(data.get(CustomComponent.MATERIAL)), data.get(CustomComponent.ID));
    }

    public static ItemMask deserialize(String serialized) {
        Map<CustomComponentClass, Integer> attributes = new HashMap<>();
        Map<CustomComponentClass, String> data = new HashMap<>();
        List<RuneWrapper> runes = new ArrayList<>();
        String[] array = serialized.split("%");
        Arrays.stream(array[0].split("\\$")).forEach(string -> {
            String[] substring = string.split(",");
            attributes.put(CustomComponentClass.deSerialize(substring[0]), Integer.valueOf(substring[1]));
        });
        Arrays.stream(array[1].split("\\$")).forEach(string -> {
            String[] substring = string.split(",");
            data.put(CustomComponentClass.deSerialize(substring[0]), substring[1]);
        });
        Arrays.stream(array[2].split("\\$")).forEach(string -> {
            String[] substring = string.split(",");
            runes.add(new RuneWrapper(CustomComponentClass.deSerialize(substring[0]), Integer.parseInt(substring[1])));
        });
        ItemMask result = new ItemMask(Material.valueOf(data.get(CustomComponent.MATERIAL)), data.get(CustomComponent.ID));
        result.attributes.putAll(attributes);
        result.data.putAll(data);
        result.runes.addAll(runes);
        return result;
    }
}
