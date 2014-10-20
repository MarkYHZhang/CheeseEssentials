package me.cheesepro.ce.mainpack;

import me.cheesepro.ce.extra.ConsoleSender;
import me.cheesepro.ce.extra.Messenger;
import me.confuser.barapi.BarAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.Console;

/**
 * Created by Mark on 19/10/2014.
 */
public class cmdBarmsg implements CommandExecutor{

    private CEMain plugin;
    private final Messenger msg;

    public cmdBarmsg(CEMain plugin){
        this.plugin = plugin;
        this.msg = new Messenger(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if(label.equalsIgnoreCase("barmsg")){
                String input="";
                for(int i=0;i<args.length;i++){
                    input = input + args[i] + " ";
                }
                if(args.length>0){
                    input = ChatColor.BOLD + input.replaceAll("&", "§");
                    BarAPI.setMessage(input);
                    msg.m(p, "e", "Message successfully set");
                }else{
                   msg.t(p, "a", "/barmsg [message]");
                }
            }
        }else{
            if(label.equalsIgnoreCase("barmsg")){
                String input="";
                for(int i=0;i<args.length;i++){
                    input = input + args[i] + " ";
                }
                if(args.length>0){
                    input = ChatColor.BOLD + input.replaceAll("&", "§");
                    BarAPI.setMessage(input);
                    ConsoleSender.msgConsole("Message successfully set");
                }else{
                    ConsoleSender.msgConsole("/barmsg [message]");
                }

            }
        }
        return false;
    }


}
