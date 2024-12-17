package de.mcterranova.infini;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import de.mcterranova.infini.rpg.listeners.*;
import de.mcterranova.infini.rpgcore.utils.builder.mob.CustomEntityBuilderUtils;
import de.mcterranova.infini.rpg.world.entities.mob.control.EntityConnector;
import de.mcterranova.infini.rpg.world.functionality.crafting.Crafting;
import de.mcterranova.infini.rpg.world.functionality.crafting.RecipeConfig;
import de.mcterranova.infini.rpg.world.functionality.crafting.stations.LocationConfig;
import de.mcterranova.infini.rpg.world.functionality.items.control.ItemArchive;

import java.util.Random;

public class Infini extends JavaPlugin {

    private static Infini instance;

    private LocationConfig locationConfig;
    private RecipeConfig recipeConfig;
    private Crafting crafting;
    private EntityConnector entityConnector;
    private Random random;
    private CustomEntityBuilderUtils builderUtils;
    private ItemArchive itemArchive;
    // private ItemCooldowns vanillaCoolDowns;

    public void onEnable() {
        instance = this;

        //register listeners
        Bukkit.getPluginManager().registerEvents( new EntityKill(), this );
        Bukkit.getPluginManager().registerEvents( new Interact(), this );
        Bukkit.getPluginManager().registerEvents( new Combust(), this );
        Bukkit.getPluginManager().registerEvents( new PlayerInteractServer(), this );
        Bukkit.getPluginManager().registerEvents( new Damage(), this );

        //load configs
        this.locationConfig = new LocationConfig();
        this.recipeConfig = new RecipeConfig();
        this.crafting = new Crafting();
        this.entityConnector = new EntityConnector();
        this.random = new Random();
        this.builderUtils = new CustomEntityBuilderUtils();
        this.itemArchive = new ItemArchive();
        // this.vanillaCoolDowns = new ItemCooldowns();

        // register stuff

        instance.getServer().broadcastMessage("§dInfinitum §7wurde §aeingeschaltet!");
    }

    public void onDisable() {

        //saving

        instance.getServer().broadcastMessage("§dInfinitum §7wurde §causgeschaltet!");
    }

    public static Infini getInstance() { return instance; }
    public LocationConfig getLocationConfig() { return locationConfig; }
    public RecipeConfig getRecipeConfig() { return recipeConfig; }
    public Crafting getCrafting() { return crafting; }
    public EntityConnector getEntityConnector() { return entityConnector; }
    public Random getRandomGenerator() { return random; }
    public CustomEntityBuilderUtils getBuilderUtils() { return builderUtils; }
    public ItemArchive getItemArchive() { return itemArchive; }
    // public ItemCooldowns getVanillaCoolDowns() { return vanillaCoolDowns; }
}