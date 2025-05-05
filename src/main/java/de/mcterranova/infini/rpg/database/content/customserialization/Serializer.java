package de.mcterranova.infini.rpg.database.content.customserialization;

import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponentClass;
import de.mcterranova.infini.rpg.world.functionality.items.control.ItemMask;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Serializer {

    public static byte[] serializeItemMask(ItemMask mask) {
        Map<CustomComponentClass, Integer> attributes = mask.attributes;
        Map<CustomComponentClass, String> data = mask.data;
        StringBuilder builder = new StringBuilder();
        attributes.keySet().forEach(attribute -> builder.append(attribute.getSerialized()).append(",").append(attributes.get(attribute)).append("$"));
        builder.append("%");
        data.keySet().forEach(component -> builder.append(component.getSerialized()).append(",").append(data.get(component)).append("$"));
        builder.append("%");
        byte[] array = builder.toString().getBytes(StandardCharsets.UTF_8);
        byte[] item = mask.getItemStack().serializeAsBytes();
        byte[] result = new byte[array.length + item.length];
        System.arraycopy(array, 0, result, 0, array.length);
        System.arraycopy(item, 0 , result, array.length, item.length);
        return result;
    }

    public static ItemMask deserialize(byte[] serialized) {
        Map<CustomComponentClass, Integer> attributes = new HashMap<>();
        Map<CustomComponentClass, String> data = new HashMap<>();
        String bytes = new String(serialized, StandardCharsets.UTF_8);
        String[] array = bytes.split("%");
        String[] attributesArray = array[1].split("\\$");
        String[] dataArray = array[0].split("\\$");
        ItemStack itemStack = ItemStack.deserializeBytes(array[2].getBytes(StandardCharsets.UTF_8));
        Arrays.stream(attributesArray).forEach(string -> {
            String[] substring = string.split(",");
            attributes.put(CustomComponentClass.deSerialize(substring[0]), Integer.valueOf(substring[1]));
        });
        Arrays.stream(attributesArray).forEach(string -> {
            String[] substring = string.split(",");
            data.put(CustomComponentClass.deSerialize(substring[0]), substring[1]);
        });
        CustomComponentClass id = data.keySet().stream().filter(comp -> comp.getDeclaration().equals("ID")).findFirst().get();
        ItemMask result = new ItemMask(itemStack, data.get(id));
        result.attributes.putAll(attributes);
        result.data.putAll(data);
        return result;
    }
}
