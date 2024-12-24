package de.mcterranova.infini.rpg.world.functionality.items.components.comps.basic.storage.item;

public enum ItemCategory {
    LOOT( "Loot"),
    MATERIAL( "Material"),
    TOOL("Werkzeug"),
    WEAPON( "Waffe"),
    ARMOR("Rüstung"),
    QUEST_ITEM( "Quest Item" );

    private final String translation;

    ItemCategory( String translation )
    {
        this.translation = translation;
    }

    public String getTranslation() { return translation; }
}
