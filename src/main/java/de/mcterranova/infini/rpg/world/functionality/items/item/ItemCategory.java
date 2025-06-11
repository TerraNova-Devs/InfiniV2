package de.mcterranova.infini.rpg.world.functionality.items.item;

public enum ItemCategory {
    LOOT( "Loot"),
    MATERIAL( "Material"),
    TOOL("Werkzeug"),
    WEAPON( "Waffe"),
    ARMOR("Rüstung"),
    QUEST_ITEM( "Quest Item" ),
    MENU_ITEM("Menü Item"),
    MENU_ITEM_EMPTY("NULL");

    private final String translation;

    ItemCategory( String translation )
    {
        this.translation = translation;
    }

    public String getTranslation() { return translation; }
}
