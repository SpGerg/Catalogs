package net.spgerg.catalogs;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

    private Main plugin;

    private CatalogsManager catalogsManager;

    public Commands(Main plugin, CatalogsManager catalogsManager){
        this.plugin = plugin;

        this.catalogsManager = catalogsManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p;

        if(sender instanceof ConsoleCommandSender){
            plugin.getLogger().info(Config.use_in_console);

            return true;
        }

        p = (Player) sender;

        if(args.length == 0){
            return false;
        }

        if(args.length >= 1){
            if(!p.hasPermission(catalogsManager.getCatalogPermission(args[0])) & !catalogsManager.getCatalogPermission(args[0]).equals("")){
                p.sendMessage(Config.can_not_use_catalog);

                return true;
            }

            if(catalogsManager.TryShowCatalog(args[0])){
                catalogsManager.showCatalog(p, args[0]);

                return true;
            }
            else{
                p.sendMessage(Config.catalog_not_found);

                return true;
            }
        }

        return false;
    }
}
