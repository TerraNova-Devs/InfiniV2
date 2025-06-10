package de.mcterranova.infini;

import de.mcterranova.infini.rpg.database.HikariCP;
import de.mcterranova.infini.rpg.listeners.*;
import de.mcterranova.infini.rpg.test.commands.TestCommand;
import de.mcterranova.infini.rpg.world.functionality.builder.mob.control.EntityConnector;
import de.mcterranova.infini.rpg.world.functionality.crafting.Crafting;
import de.mcterranova.infini.rpg.world.functionality.crafting.RecipeConfig;
import de.mcterranova.infini.rpg.world.functionality.crafting.stations.LocationConfig;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import de.mcterranova.infini.rpg.world.functionality.builder.mob.CustomEntityBuilderUtils;
import de.mcterranova.infini.rpg.world.functionality.items.control.ItemArchive;

import java.sql.SQLException;
import java.util.Random;

public class Infini extends JavaPlugin {

    private static Infini instance;

    public static HikariCP hikari;
    private Random random;
    private CustomEntityBuilderUtils builderUtils;
    private ItemArchive itemArchive;
    private LocationConfig locationConfig;
    private RecipeConfig recipeConfig;
    private Crafting crafting;
    private EntityConnector entityConnector;
    // private ItemCooldowns vanillaCoolDowns;

    public void onEnable() {
        instance = this;

        initDatabase();

        System.setProperty("de.tr7zw.nbtapi.fallback", "true");
        //register listeners
        Bukkit.getPluginManager().registerEvents( new EntityKill(), this );
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

        TestCommand testCommand = new TestCommand();
        this.getCommand("testCommand").setExecutor(testCommand);
        this.getCommand("testCommand").setTabCompleter(testCommand);
        // this.vanillaCoolDowns = new ItemCooldowns();

        // register stuff

        instance.getServer().broadcastMessage("§dInfinitum §7wurde §aeingeschaltet!");
    }

    private void initDatabase() {
        try {
            hikari = new HikariCP(this);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void onDisable() {

        //saving

        instance.getServer().broadcastMessage("§dInfinitum §7wurde §causgeschaltet!");
    }

    public static Infini getInstance() { return instance; }
    public Random getRandomGenerator() { return random; }
    public CustomEntityBuilderUtils getBuilderUtils() { return builderUtils; }
    public ItemArchive getItemArchive() { return itemArchive; }
    public LocationConfig getLocationConfig() { return locationConfig; }
    public RecipeConfig getRecipeConfig() { return recipeConfig; }
    public Crafting getCrafting() { return crafting; }
    public EntityConnector getEntityConnector() { return entityConnector; }
    // public ItemCooldowns getVanillaCoolDowns() { return vanillaCoolDowns; }
}