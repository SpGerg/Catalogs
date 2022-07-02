package net.spgerg.catalogs;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public final Config config = new Config(this);

    public final CatalogsManager catalogsManager = new CatalogsManager(config);

    @Override
    public void onEnable() {
        getCommand("catalogs").setExecutor(new Commands(this, catalogsManager));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
