package de.mcterranova.infini.rpg.nms;

import org.bukkit.Bukkit;

public class NMSFactory {
    private static NMSHelper helper;

    public static void init() {
        String version = getVersion();
        try {
            Class<?> clazz = Class.forName("de.terranova.infini.rpg.nms." + version + ".NMSHelperImplementation");
            helper = (NMSHelper) clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getVersion() {
        String packageName = Bukkit.getServer().getClass().getPackage().getName();
        return packageName.substring(packageName.lastIndexOf('.') + 1);
    }

    public static NMSHelper getHelper() {
        return helper;
    }

}
