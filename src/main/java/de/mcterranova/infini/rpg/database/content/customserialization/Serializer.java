package de.mcterranova.infini.rpg.database.content.customserialization;

import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponentClass;
import de.mcterranova.infini.rpg.world.functionality.items.control.ItemMask;
import de.mcterranova.terranovaLib.commands.CommandAnnotation;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Serializer {

    public static String serializeItemMask(ItemMask mask) {
        Map<CustomComponentClass, Integer> attributes = mask.attributes;
        Map<CustomComponentClass, String> data = mask.data;
        StringBuilder builder = new StringBuilder();
        attributes.keySet().forEach(attribute -> builder.append(attribute.getSerialized()).append(",").append(attributes.get(attribute)).append("$"));
        builder.append("%");
        data.keySet().forEach(component -> builder.append(component.getSerialized()).append(",").append(data.get(component)).append("$"));
        builder.append("%");
        Bukkit.getServer().broadcast(Component.text(builder.toString()));
        String item = null;
        try{
            item = serializeItemStack(mask.getItemStack());
        } catch (Exception e) {
            e.printStackTrace();
        }
        builder.append(item);
        return builder.toString();
    }

    public static ItemMask deserialize(String serialized) {
        Map<CustomComponentClass, Integer> attributes = new HashMap<>();
        Map<CustomComponentClass, String> data = new HashMap<>();
        Bukkit.getServer().broadcast(Component.text(serialized));
        String[] array = serialized.split("%");
        String[] attributesArray = array[0].split("\\$");
        String[] dataArray = array[1].split("\\$");
        ItemStack itemStack = null;
        try{
             itemStack = deserializeItemStack(array[2]);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        Arrays.stream(attributesArray).forEach(string -> {
            String[] substring = string.split(",");
            attributes.put(CustomComponentClass.deSerialize(substring[0]), Integer.valueOf(substring[1]));
        });
        Arrays.stream(dataArray).forEach(string -> {
            String[] substring = string.split(",");
            data.put(CustomComponentClass.deSerialize(substring[0]), substring[1]);
        });
        CustomComponentClass id = data.keySet().stream().filter(comp -> comp.getDeclaration().equals("ID")).findFirst().get();
        ItemMask result = new ItemMask(itemStack, data.get(id));
        result.attributes.putAll(attributes);
        result.data.putAll(data);
        return result;
    }

    public static String serializeItemStack(ItemStack item) throws Exception {
        YamlConfiguration config = new YamlConfiguration();
        config.set("item", item);

        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(byteStream);
        OutputStreamWriter writer = new OutputStreamWriter(gzip, StandardCharsets.UTF_8);

        writer.write(config.saveToString());
        writer.close();

        return Base64.getEncoder().encodeToString(byteStream.toByteArray());
    }

    public static ItemStack deserializeItemStack(String base64) throws Exception {
        byte[] bytes = Base64.getDecoder().decode(base64);
        GZIPInputStream gzip = new GZIPInputStream(new ByteArrayInputStream(bytes));
        InputStreamReader reader = new InputStreamReader(gzip, StandardCharsets.UTF_8);

        YamlConfiguration config = YamlConfiguration.loadConfiguration(reader);
        return config.getItemStack("item");
    }

}
