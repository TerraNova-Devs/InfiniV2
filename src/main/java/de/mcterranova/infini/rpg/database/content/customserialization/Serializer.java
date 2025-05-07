package de.mcterranova.infini.rpg.database.content.customserialization;

import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponent;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponentClass;
import de.mcterranova.infini.rpg.world.functionality.items.control.ItemMask;
import org.bukkit.Material;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Serializer {

    public static String serializeItemMask(ItemMask mask) {
        Map<CustomComponentClass, Integer> attributes = mask.attributes;
        Map<CustomComponentClass, String> data = mask.data;
        StringBuilder builder = new StringBuilder();
        attributes.keySet().forEach(attribute -> builder.append(attribute.getSerialized()).append(",").append(attributes.get(attribute)).append("$"));
        builder.append("%");
        data.keySet().forEach(component -> builder.append(component.getSerialized()).append(",").append(data.get(component)).append("$"));
        return builder.toString();
    }

    public static ItemMask deserialize(String serialized) {
        Map<CustomComponentClass, Integer> attributes = new HashMap<>();
        Map<CustomComponentClass, String> data = new HashMap<>();
        String[] array = serialized.split("%");
        String[] attributesArray = array[0].split("\\$");
        String[] dataArray = array[1].split("\\$");
        Arrays.stream(attributesArray).forEach(string -> {
            String[] substring = string.split(",");
            attributes.put(CustomComponentClass.deSerialize(substring[0]), Integer.valueOf(substring[1]));
        });
        Arrays.stream(dataArray).forEach(string -> {
            String[] substring = string.split(",");
            data.put(CustomComponentClass.deSerialize(substring[0]), substring[1]);
        });
        ItemMask result = new ItemMask(Material.valueOf(data.get(CustomComponent.MATERIAL)), data.get(CustomComponent.ID));
        result.attributes.putAll(attributes);
        result.data.putAll(data);
        return result;
    }
}
