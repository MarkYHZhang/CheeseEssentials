package me.cheesepro.ce.mainpack;

import me.cheesepro.ce.extra.Messenger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Mark on 08/11/2014.
 */
public class cmdBroadcast implements CommandExecutor{

    private CEMain plugin;
    private final Messenger msg;

    public cmdBroadcast(CEMain plugin){
        this.plugin = plugin;
        this.msg = new Messenger(plugin);
    }


    public boolean onCommand(CommandSender sender, Command cmd, String label,String[] args) {
        Player p = (Player) sender;
        if(sender instanceof Player){
            if(label.equalsIgnoreCase("broadcast")){
                String usrcmdinput="";
                for(int i=0;i<args.length;i++){
                    usrcmdinput = usrcmdinput + args[i] + " ";
                }
                if(args.length>0){
                    Bukkit.broadcastMessage(ChatColor.YELLOW.toString() + ChatColor.BOLD + "[!]" + ChatColor.RESET + ChatColor.RED + "" + ChatColor.BOLD + "Broadcast" + ChatColor.RESET + ChatColor.YELLOW.toString() + ChatColor.BOLD + "[!] " + usrcmdinput);
                }else if(args.length==0){
                    msg.t(p, "e", "/broadcast [Message]");
                }
            }
        }else{
            Bukkit.getLogger().info("Command executor must be a in-game player!");
        }
        return false;
    }

}
