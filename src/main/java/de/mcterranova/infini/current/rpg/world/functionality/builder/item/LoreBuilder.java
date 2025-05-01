package de.mcterranova.infini.current.rpg.world.functionality.builder.item;

import de.mcterranova.infini.Infini;
import de.mcterranova.infini.current.rpg.world.functionality.items.components.ComponentType;
import de.mcterranova.infini.current.rpg.world.functionality.items.components.CustomComponent;
import de.mcterranova.infini.current.rpg.world.functionality.items.components.CustomComponentClass;
import de.mcterranova.infini.rpg.world.functionality.Attribute;
import de.mcterranova.infini.rpg.world.functionality.items.componentsold.CustomItemTemplate;
import de.mcterranova.infini.current.rpg.world.functionality.items.components.comps.basic.storage.item.ItemCategory;
import de.mcterranova.infini.current.rpg.world.functionality.items.components.comps.basic.storage.item.ItemClass;
import de.mcterranova.infini.current.rpg.world.functionality.items.components.comps.basic.storage.item.ItemTier;
import de.mcterranova.infini.current.rpg.world.functionality.items.control.ItemMask;
import de.mcterranova.infini.rpg.world.functionality.items.enchanting.CustomEnchantment;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentBuilder;
import net.kyori.adventure.text.TextComponent;

import java.util.*;

public class LoreBuilder {
    public final Map<CustomComponentClass, Object> data = new HashMap<>();
    public final Map<CustomComponentClass, Integer> attributes = new HashMap<>();

    private final Map<CustomComponentClass, Integer> runes = new HashMap<>();
    private final Map<CustomComponentClass, Integer> enchantments = new HashMap<>();
    private final Map<CustomComponentClass, Integer> basicAttributes = new HashMap<>();
    private final List<Component> newLore = new ArrayList<>();

    private final ItemCategory itemCategory;
    private final ItemClass itemClass;
    private final ItemTier itemTier;
    private final int runeSlots;
    private int enchantmentLength;
    private String description;

    private boolean compactEnchantments = true;
    private boolean addAttributes;
    private boolean addDescription = true;



    // rework with new item library and maybe add item masks like entity masks to get the attributes n shit for update purposes

    public LoreBuilder(ItemMask item, boolean addAttributes)
    {
        this.data.putAll(item.data);
        this.attributes.putAll(item.attributes);
        this.itemTier = (ItemTier) data.get(CustomComponent.ITEM_TIER);
        this.itemCategory = (ItemCategory) data.get(CustomComponent.ITEM_CATEGORY);
        this.itemClass = (ItemClass) data.get(CustomComponent.ITEM_CLASS);
        this.data.keySet().stream().filter(component -> component.equals(CustomComponent.RUNE)).forEach(component -> this.runes.put(component, this.attributes.get(component)));
        this.runeSlots = (Integer) data.get(CustomComponent.RUNE_SLOTS);
        this.data.keySet().stream().filter(component -> component.getType().equals(ComponentType.ENCHANTMENT)).forEach(component -> this.enchantments.put(component, this.attributes.get(component)));
        this.description = (String) this.data.get(CustomComponent.DESCRIPTION);
        this.addAttributes = addAttributes;
        if (description.isBlank())
            this.addDescription = false;
        if (enchantments.isEmpty())
            return;
        this.enchantmentLength = enchantments.size();
        if (enchantmentLength > 6)
            this.compactEnchantments = false;
    }
    
    public List<String> build()
    {
        Component blank = Component.text("§0");

        if (this.attributes.isEmpty())
            this.addAttributes = false;

        if (addAttributes) {
            attributes.keySet().forEach(attribute -> this.newLore.add(Component.text("§7" + attribute.getAttribute().getTranslation() + ": " + attribute.getColor() + this.attributes.get(attribute))));
            this.newLore.add(blank);
        }
  /*
            for ( CustomComponentClass attributes : attributes.keySet())
            {

                String[] split = string.split( "/" );
                int v = Integer.parseInt( split[ 1 ] );
                Attribute attribute = Attribute.valueOf( split[ 0 ] );

                String component = "+" + v;

                if ( v < 0 )
                    component = String.valueOf( v );

                if ( v != 0 )
                {
                    this.newLore.add(Component.text("§7" + attribute.getTranslation() + ": " + color + component));
                    temp += 1;
                    if ( temp >= 4 ) {
                        color = "§a";
                        temp = 0;
                    }
                }
            }
             */



        if (runeSlots != 0) {
            int temp = runeSlots;
            Collections.sort(runes);
            for (Rune rune : runes) {
                this.newLore.add("§8[ " + rune.getTranslation() + " §8]");
                temp -= 1;
            }
            for ( int i = 0; i < temp; i++ ) {
                this.newLore.add("§8[ §f- §8]");
            }
            this.newLore.add( "§0" );
        }

        if (!enchantments.isEmpty())
        {
            if ( compactEnchantments )
            {
                for ( int i = 0; i < enchantmentLength; i++ )
                {
                    String single = enchantments.substring( 0, enchantments.indexOf( "," ) ).strip();
                    newLore.add( "§9" + single.replace("$", " ") );
                    enchantments = enchantments.substring( single.length() + 2 );

                    Infini.getInstance().getServer().broadcastMessage( "LOOP ONE -> " + i + single + enchantments );
                }
            } else
                for ( int i = 0; true ; i++ )
                {
                    if ( enchantments.size() > 38 )
                    {
                        String split = enchantments.substring( 0, 38 ).strip();
                        String remaining = enchantments.substring( 0, split.lastIndexOf( "," ) );
                        newLore.add ( "§9" + remaining.replace("$", " ") );
                        enchantments = enchantments.substring ( split.lastIndexOf( " " ) ).strip();

                        Infini.getInstance().getServer().broadcastMessage( "LOOP TWO -> " + i );
                    } else
                    {
                        newLore.add( "§9" + enchantments.substring( 0, enchantments.lastIndexOf( "," ) ).replace("$", " ").strip() );
                        break;
                    }
                }
            this.newLore.add( "§0" );
        }

        if (addDescription) {
            for ( int i = 0; true ; i++ )
            {
                if ( description.length() > 38 )
                {
                    String split = description.substring( 0, 38 ).strip();
                    String remaining = description.substring( 0, split.lastIndexOf( " " ) );
                    this.newLore.add (Component.text("§7" + remaining));
                    description = description.substring ( split.lastIndexOf( " " ) ).strip();
                } else
                {
                    this.newLore.add(Component.text("§7" + description.strip()));
                    break;
                }
            }

            this.newLore.add(Component.text("§0"));
        }


        //TODO change color if you can use it

        if ( addAttributes )
            newLore.add( "§a" + itemClass.getTranslation() );

        // show how well it was crafted

        newLore.add( "§f§lSTUFE " + itemTier.getColor() + "§l" + itemTier.name() + " §f§l" + itemCategory.getTranslation().toUpperCase() );

        return this.newLore;
    }

    private List< String > convertAttributes( Map< Attribute, Integer > attributes )
    {
        List< String > output = new ArrayList<>();
        if ( attributes.get( Attribute.DAMAGE ) != 0 )
            output.add( "DAMAGE" + "/" + attributes.get( Attribute.DAMAGE ) );
        if ( attributes.get( Attribute.STRENGTH ) != 0 )
            output.add( "STRENGTH" + "/" + attributes.get( Attribute.STRENGTH ) );
        if ( attributes.get( Attribute.CRITICAL_CHANCE ) != 0 )
            output.add( "CRITICAL_CHANCE" + "/" + attributes.get( Attribute.CRITICAL_CHANCE ) );
        if ( attributes.get( Attribute.CRITICAL_DAMAGE ) != 0 )
            output.add( "CRITICAL_DAMAGE" + "/" + attributes.get( Attribute.CRITICAL_DAMAGE ) );
        if ( attributes.get( Attribute.HEALTH ) != 0 )
            output.add( "HEALTH" + "/" + attributes.get( Attribute.HEALTH ) );
        if ( attributes.get( Attribute.DEFENSE ) != 0 )
            output.add( "DEFENSE" + "/" + attributes.get( Attribute.DEFENSE ) );
        if ( attributes.get( Attribute.INTELLIGENCE ) != 0 )
            output.add( "INTELLIGENCE" + "/" + attributes.get( Attribute.INTELLIGENCE ) );
        return output;
    }
}
