package de.mcterranova.infini.rpg.world.functionality.items.componentsold;

import de.mcterranova.infini.rpg.world.functionality.items.components.comps.basic.storage.item.ItemCategory;
import de.mcterranova.infini.rpg.world.functionality.items.components.comps.basic.storage.item.ItemClass;
import de.mcterranova.infini.rpg.world.functionality.items.components.comps.basic.storage.item.ItemTier;
import org.bukkit.Material;
import de.mcterranova.infini.rpg.world.functionality.Attribute;

import java.util.HashMap;

public enum CustomItemTemplate
{
    NULL(
            Material.POPPED_CHORUS_FRUIT,
            ItemCategory.MATERIAL,
            ItemAttributes.NONE,
            0,
            ItemTier.O,
            "Verfluchte Frucht",
            "&o&7Erschaffen aus einem Fehlgeschlagenen Ritual" ),

    INFINITA_SCIENTIA(
            Material.WRITABLE_BOOK,
            ItemCategory.QUEST_ITEM,
            ItemAttributes.INFINITA_SCIENTIA,
            0,
            ItemTier.II,
            "Infinita Scientia",
            "&o&7Eine starke Ausstrahlung geht von diesem Wälzer aus..." ),

    HEART_OF_THE_END(
            Material.PITCHER_PLANT,
            ItemCategory.MATERIAL,
            ItemAttributes.NONE,
            0,
            ItemTier.O,
            "Herz des Endes",
            "&o&7Es pulsiert mit &o&7Dunkler Energie..." ),

    RUNE_OF_POWER(
            Material.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE,
            ItemCategory.MATERIAL,
            ItemAttributes.NONE,
            0,
            ItemTier.O,
            "Rune der Macht",
            "das ist ein test string ich hoffe das das ich frage mich was passiert wenn ich einfach weiter schreibe sio lol sollte eigenltihch nicht viel passieren?? §c§lfunktioniert§7 so wie es soll §c§laber§7 ich habe ehrlich gesagt keine ahnung ob ja oder nein lolz\""),

    END_CRYSTAL(
            Material.END_CRYSTAL,
            ItemCategory.MATERIAL,
            ItemAttributes.NONE,
            0,
            ItemTier.O,
            "End Crystal",
            "null" ),

    NETHER_STAR(
            Material.NETHER_STAR,
            ItemCategory.MATERIAL,
            ItemAttributes.NONE,
            0,
            ItemTier.O,
            "Nether Star",
            "das ist ein test string ich hoffe das das ich frage mich was passiert wenn ich einfach weiter schreibe sio lol sollte eigenltihch nicht viel passieren?? §c§lfunktioniert§7 so wie es soll §c§laber§7 ich habe ehrlich gesagt keine ahnung ob ja oder nein lolz"),

    FRAGMENT_OF_REALITY(
            Material.ECHO_SHARD,
            ItemCategory.MATERIAL,
            ItemAttributes.NONE,
            0,
            ItemTier.II,
            "Fragment der Realität",
            "&7Stufe &6II" ),

    STICK_OF_GOD(
            Material.BLAZE_ROD,
            ItemCategory.WEAPON,
            ItemAttributes.ADMIN,
            0,
            ItemTier.V,
            "§5§kA §cStab Gottes §5§kA",
            "Nur wahre götter sind würdig diesen Stab zu schwingen" ),

    HYPERION(
            Material.DIAMOND_SWORD,
            ItemCategory.WEAPON,
            ItemAttributes.HYPERION,
            3,
            ItemTier.IV,
            "Finsternis",
            "Ein machtvolles Schwert" );

    private final Material material;
    private final String name;
    private final String lore;
    private final ItemTier itemTier;
    private final ItemAttributes attributes;
    private final ItemClass itemClass;
    private final ItemCategory itemCategory;
    private final int runeSlots;

    CustomItemTemplate( Material material, ItemCategory itemCategory, ItemAttributes attributes, int runeSlots, ItemTier itemTier, String name, String lore )
    {
        this.runeSlots = runeSlots;
        this.material = material;
        this.name = name;
        this.lore = lore;
        this.itemTier = itemTier;
        this.attributes = attributes;
        this.itemClass = attributes.getItemClass();
        this.itemCategory = itemCategory;
    }

    public int getRuneSlots() { return runeSlots; }
    public Material getMaterial() { return material; }
    public String getName() { return name; }
    public String getLore() { return lore; }
    public ItemTier getTier() { return itemTier; }
    public HashMap<Attribute, Integer> getAttributes() { return attributes.getAttributes(); }
    public ItemClass getItemClass() { return itemClass; }
    public ItemCategory getItemCategory() { return itemCategory; }
}
