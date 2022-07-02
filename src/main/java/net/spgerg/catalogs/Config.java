package net.spgerg.catalogs;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.spgerg.catalogs.elements.Button;
import net.spgerg.catalogs.elements.CatalogElement;
import net.spgerg.catalogs.elements.Text;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Config {

    private Main plugin;

    private File configFile;

    public static String use_in_console;

    public static String can_not_use_catalog;

    public static String catalog_not_found;

    public Config(Main plugin){
        this.plugin = plugin;

        configFile = new File(plugin.getDataFolder() + File.separator + "config.yml");

        if(!configFile.exists()){
            plugin.saveDefaultConfig();
        }

        initMessages();
    }

    private void initMessages(){
        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

        use_in_console = ChatColor.translateAlternateColorCodes('&', config.getString("use_in_console"));

        can_not_use_catalog = ChatColor.translateAlternateColorCodes('&', config.getString("can_not_use_catalog"));

        catalog_not_found = ChatColor.translateAlternateColorCodes('&', config.getString("catalog_not_found"));
    }

    public FileConfiguration getConfig(){
        return YamlConfiguration.loadConfiguration(configFile);
    }
}
