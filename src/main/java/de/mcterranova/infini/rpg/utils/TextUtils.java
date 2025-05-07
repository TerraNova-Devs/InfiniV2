package de.mcterranova.infini.rpg.utils;

import net.kyori.adventure.text.Component;

import java.util.ArrayList;
import java.util.List;

public class TextUtils {

    public static List<Component> prepEnchantments(String input, int length, String color) {
        List<Component> temporary = new ArrayList<>();
        for (int i = 0; true; i++) {
            if (input.length() > length)
            {
                String split = input.substring(0, length).strip();
                String remaining = input.substring(0, split.lastIndexOf(","));
                temporary.add (Component.text(color + remaining.replace("%", " ")));
                input = input.substring (split.lastIndexOf(",")).strip();
            } else {
                temporary.add(Component.text(color + input.strip()));
                break;
            }
        }
        return temporary;
    }

    public static List<Component> prepDescription(String input, int length, String color) {
        List<Component> temporary = new ArrayList<>();
        for (int i = 0; true; i++) {
            if (input.length() > length)
            {
                String split = input.substring(0, length).strip();
                String remaining = input.substring(0, split.lastIndexOf(" "));
                temporary.add (Component.text(color + remaining));
                input = input.substring (split.lastIndexOf(" ")).strip();
            } else {
                temporary.add(Component.text(color + input.strip()));
                break;
            }
        }
        return temporary;
    }

}
