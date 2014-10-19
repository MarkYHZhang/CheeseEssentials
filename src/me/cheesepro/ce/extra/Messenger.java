package me.cheesepro.ce.extra;

import me.cheesepro.ce.mainpack.CEMain;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Mark on 06/10/2014.
 */
public class Messenger {

    private CEMain plugin;

    ChatColor WHITE = ChatColor.WHITE;
    ChatColor YELLOW = ChatColor.YELLOW;
    ChatColor GREEN = ChatColor.GREEN;
    ChatColor RED = ChatColor.RED;
    ChatColor DRED = ChatColor.DARK_RED;
    ChatColor AQUA = ChatColor.AQUA;
    ChatColor LPURPLE = ChatColor.LIGHT_PURPLE;
    ChatColor DPURPLE = ChatColor.DARK_PURPLE;

    ChatColor color;
    public Messenger(CEMain plugin){
        this.plugin = plugin;
    }

    public void main(Player p, String msg){
        String prefix = plugin.getConfig().getString("prefix.main");
        prefix = prefix.replaceAll("&", "§");

        p.sendMessage(prefix + " " + msg);
    }

    public void warn(Player p, String msg){
        String prefix = plugin.getConfig().getString("prefix.warn");
        prefix = prefix.replaceAll("&", "§");
        p.sendMessage(prefix + " " + msg);
    }

    public void tip(Player p, String msg){
        String prefix = plugin.getConfig().getString("prefix.tip");
        prefix = prefix.replaceAll("&", "§");
        p.sendMessage(prefix + " " + msg);
    }

    public void motd(Player p, String msg){
        String prefix = plugin.getConfig().getString("prefix.gamemotd");
        prefix = prefix.replaceAll("&", "§");
        p.sendMessage(prefix + " " + msg);
    }

    public void m(Player p, String c, String msg){
        String prefix = plugin.getConfig().getString("prefix.main");
        prefix = prefix.replaceAll("&", "§");
        color(c);
        p.sendMessage(prefix + " " + color.toString() + ChatColor.BOLD + msg);
    }

    public void w(Player p, String c, String msg){
        String prefix = plugin.getConfig().getString("prefix.warn");
        prefix = prefix.replaceAll("&", "§");
        color(c);
        p.sendMessage(prefix + " " + color.toString() + ChatColor.BOLD + msg);
    }

    public void t(Player p, String c, String msg){
        String prefix = plugin.getConfig().getString("prefix.tip");
        prefix = prefix.replaceAll("&", "§");
        color(c);
        p.sendMessage(prefix + " " + color.toString() + ChatColor.BOLD + msg);
    }

    private void color(String c){
        if(c.equalsIgnoreCase("f")){
            color = WHITE;
        }else if(c.equalsIgnoreCase("e")){
            color = YELLOW;
        }else if(c.equalsIgnoreCase("a")){
            color = GREEN;
        }else if(c.equalsIgnoreCase("4")){
            color = DRED;
        }else if(c.equalsIgnoreCase("b")){
            color = AQUA;
        }else if(c.equalsIgnoreCase("d")){
            color = LPURPLE;
        }else if(c.equalsIgnoreCase("5")){
            color = DPURPLE;
        }else if(c.equalsIgnoreCase("c")){
            color = RED;
        }
    }

}