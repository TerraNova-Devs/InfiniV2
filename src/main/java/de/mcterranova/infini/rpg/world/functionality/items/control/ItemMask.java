package de.mcterranova.infini.rpg.world.functionality.items.control;

import de.mcterranova.infini.rpg.database.content.customserialization.CustomSerializable;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponent;
import de.mcterranova.infini.rpg.world.functionality.items.components.CustomComponentClass;
import de.mcterranova.infini.rpg.world.functionality.items.components.comps.advanced.runes.RuneWrapper;
import org.bukkit.Material;

import javax.management.monitor.StringMonitor;
import java.util.*;

public class ItemMask implements CustomSerializable {

    public final Map<CustomComponentClass, String> data = new HashMap<>();
    public final Map<CustomComponentClass, Double> attributes = new HashMap<>();
    public final List<RuneWrapper> runes = new ArrayList<>();

    public ItemMask(Material material, String id) {
        this.data.put(CustomComponent.ID, id);
        this.data.put(CustomComponent.MATERIAL, material.name());
    }

    public UUID getUUID() { return UUID.fromString(this.data.get(CustomComponent.UUID)); }
    public void newUUID() { this.data.put(CustomComponent.UUID, UUID.randomUUID().toString()); }

    @Override
    public String serialize() {
        List<String> v = new ArrayList<>();
        attributes.keySet().forEach(attribute -> v.add(attribute.getSerialized() + "," + attributes.get(attribute).toString()));
        data.keySet().forEach(component -> v.add(component.getSerialized() + "," + data.get(component)));
        runes.forEach(rune -> v.add(rune.rune().getSerialized() + "," + rune.level().toString()));
        StringBuilder builder = new StringBuilder();
        v.forEach(string -> builder.append(string).append("$"));
        return builder.toString();
    }

    public static ItemMask deserialize(String v) {
        String[] split = v.split("\\$");
        List<String> list = new ArrayList<>(Arrays.asList(split));
        Map<CustomComponentClass, Double> a = new HashMap<>();
        Map<CustomComponentClass, String> b = new HashMap<>();
        List<RuneWrapper> c = new ArrayList<>();
        list.forEach(v2 -> {
            switch (v2.substring(0, 1)) {
                case "A", "C":
                    String[] split2 = v2.split(",");
                    a.put(CustomComponentClass.deSerialize(split2[0]), Double.valueOf(split2[1]));
                    break;
                case "B":
                    String[] split3 = v2.split(",");
                    b.put(CustomComponentClass.deSerialize(split3[0]), split3[1]);
                    break;
                case "D":
                    String[] split4 = v2.split(",");
                    c.add(new RuneWrapper(CustomComponentClass.deSerialize(split4[0]), Integer.valueOf(split4[1])));
                    break;
            }});
        ItemMask result = new ItemMask(Material.valueOf(b.get(CustomComponent.MATERIAL)), b.get(CustomComponent.ID));
        result.attributes.putAll(a);
        result.data.putAll(b);
        result.runes.addAll(c);
        return result;
    }
}
