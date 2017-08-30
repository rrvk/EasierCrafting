package de.guntram.mcmod.easiercrafting;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import java.io.File;
import net.minecraftforge.common.config.Configuration;

public class ConfigurationHandler {

    private static ConfigurationHandler instance;

    private Configuration config;
    private String configFileName;
    
    private boolean autoFocusSearch;
    private int autoUpdateRecipeTimer;
    private boolean allowRecipeBook;
    private boolean showGuiRight;

    public static ConfigurationHandler getInstance() {
        if (instance==null)
            instance=new ConfigurationHandler();
        return instance;
    }

    public void load(final File configFile) {
        if (config == null) {
            config = new Configuration(configFile);
            configFileName=configFile.getPath();
            loadConfig();
        }
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equalsIgnoreCase(EasierCrafting.MODID)) {
            loadConfig();
        }
    }
    
    private void loadConfig() {
        autoUpdateRecipeTimer=config.getInt("Auto update recipe timer", Configuration.CATEGORY_CLIENT, 5, 0, 300, "Update recipe list after this many seconds after last click");
        autoFocusSearch=config.getBoolean("Auto focus search text", Configuration.CATEGORY_CLIENT, false, "Automatically focus the search box when opening craft GUI");
        allowRecipeBook=config.getBoolean("Allow MC internal recipe book", Configuration.CATEGORY_CLIENT, true, "Allow opening the MC internal recipe book (since 1.12)");
        showGuiRight=config.getBoolean("Show GUI right of inventory", Configuration.CATEGORY_CLIENT, true, "Show the GUI right of the inventory, when it could conflict with Just Enough Items, instead of left, where it conflicts with active buffs");
        
        if (config.hasChanged())
            config.save();
    }
    
    public static Configuration getConfig() {
        return getInstance().config;
    }
    
    public static String getConfigFileName() {
        return getInstance().configFileName;
    }
    
    public static int getAutoUpdateRecipeTimer() {
        return getInstance().autoUpdateRecipeTimer;
    }

    public static boolean getAutoFocusSearch() {
        return getInstance().autoFocusSearch;
    }

    public static boolean getAllowMinecraftRecipeBook() {
        return getInstance().allowRecipeBook;
    }
    
    public static boolean getShowGuiRight() {
        return getInstance().showGuiRight;
    }

}
