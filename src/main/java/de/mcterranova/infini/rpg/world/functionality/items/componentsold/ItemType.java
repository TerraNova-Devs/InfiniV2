package de.mcterranova.infini.rpg.world.functionality.items.componentsold;

public enum ItemType
{
    HELMET( "Helm" ),
    CHESTPLATE( "Brustpanzer" ),
    LEGGINGS( "Gamaschen" ),
    BOOTS( "Stiefel" ),
    CEPTRE("Zepter" ),
    TOME("Wälzer" ),
    WAND("Zauberstab" ),
    SWORD("Schwert" ),
    BOW("Bogen" ),
    PICKAXE("Spitzhacke" ),
    AXE("Axt" ),
    SHIELD("Schild" ),
    WAR_AXE("Streitaxt" ),
    MACE("Streitkolben" ),
    QUEST_ITEM( "Quest Item" );

    private final String translation;

    ItemType( String translation )
    {
        this.translation = translation;
    }

    public String getTranslation() { return translation; }
}
