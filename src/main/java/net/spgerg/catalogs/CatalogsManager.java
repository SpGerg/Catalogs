package net.spgerg.catalogs;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.spgerg.catalogs.elements.Button;
import net.spgerg.catalogs.elements.CatalogElement;
import net.spgerg.catalogs.elements.Text;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CatalogsManager {

    private Config configClass;

    public CatalogsManager(Config configClass){
        this.configClass = configClass;
    }

    public boolean TryShowCatalog(String catalog){
        FileConfiguration config = configClass.getConfig();

        return config.contains(getPath(catalog));
    }

    public void showCatalog(Player player, String catalog){
        FileConfiguration config = configClass.getConfig();

        List<CatalogElement> catalogList = new ArrayList<>();

        for (int i = 0;;i++){
            if(config.contains(getPathElement(catalog, "button", i))){
                Button button = new Button();

                button.text = config.getString(getPathElement(catalog, "button", i) + ".text");
                button.bold = config.getBoolean(getPathElement(catalog, "button", i) + ".bold");
                button.italic = config.getBoolean(getPathElement(catalog, "button", i) + ".italic");
                button.strikethrough = config.getBoolean(getPathElement(catalog, "button", i) + ".strikethrough");
                button.underlined = config.getBoolean(getPathElement(catalog, "button", i) + ".underlined");
                button.obfuscated = config.getBoolean(getPathElement(catalog, "button", i) + ".obfuscated");
                button.run_command = config.getString(getPathElement(catalog, "button", i) + ".run_command");
                button.color = ChatColor.valueOf(config.getString(getPathElement(catalog, "button", i) + ".color"));

                button.type = 0;

                catalogList.add(button);
            }
            else if(config.contains(getPathElement(catalog, "text", i))) {
                Text text = new Text();

                text.text = ChatColor.translateAlternateColorCodes('&', config.getString(getPathElement(catalog, "text", i)));

                text.type = 1;

                catalogList.add(text);
            }
            else if(!config.contains(getPathElement(catalog, "button", i)) & !config.contains(getPathElement(catalog, "text", i))){
                break;
            }
        }

        for(int i = 0;i < catalogList.size();i++){
            if(catalogList.get(i).type == 0){
                Button button = (Button) catalogList.get(i);

                TextComponent text = new TextComponent(button.text);
                text.setBold(button.bold);
                text.setItalic(button.italic);
                text.setStrikethrough(button.strikethrough);
                text.setUnderlined(button.underlined);
                text.setObfuscated(button.obfuscated);
                text.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, button.run_command.replace("<player>", player.getName())));
                text.setColor(button.color);

                player.spigot().sendMessage(text);
            }
            if(catalogList.get(i).type == 1){
                Text textSend = (Text) catalogList.get(i);

                player.sendMessage(ChatColor.translateAlternateColorCodes('&', textSend.text));
            }
        }
    }

    public String getCatalogPermission(String catalog){
        return getPath(catalog) + ".permission_use";
    }

    private String getPathElement(String catalog, String element, int type){
        return getPath(catalog) + "." + element + String.valueOf(type);
    }

    private String getPath(String catalog){
        return "catalogs." + catalog;
    }
}
