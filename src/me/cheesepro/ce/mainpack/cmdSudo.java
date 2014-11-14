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

/**
 * Created by Mark on 18/10/2014.
 */
public class cmdSudo implements CommandExecutor{

    private CEMain plugin;
    private final Messenger msg;

    public cmdSudo(CEMain plugin){
        this.plugin = plugin;
        this.msg = new Messenger(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if(sender instanceof Player){
            String targetn="";
            String option="";
            if(player.hasPermission("super.sudo")){
                if(label.equalsIgnoreCase("sudo")){
                    if(args.length>=3){
                        Player targetPlayer = player.getServer().getPlayer(args[1]);
                        option=args[0];
                        if(targetPlayer!=null && option!=null){
                            if(option.equalsIgnoreCase("cmd")){
                                for(int i=2;i<args.length;i++){
                                    targetn=targetn+args[i] + " ";
                                }
                                msg.w(player, "5", "Target player " + targetPlayer.getName() + " preformed command [ /" + targetn +"]");
                                targetPlayer.performCommand(targetn);
                            }else if(option.equalsIgnoreCase("msg")){
                                for(int i=2;i<args.length;i++){
                                    targetn=targetn+args[i] + " ";
                                }
                                msg.w(player, "5", "Target player " + targetPlayer.getName() + " sent message [ " + targetn +"]");
                                targetPlayer.chat(targetn);
                            }else{
                                msg.t(player, "b","options:");
                                msg.t(player, "b","cmd : send a command");
                                msg.t(player, "b","msg : send a message");
                            }
                        }else if(args[1].equalsIgnoreCase("all") && option!=null){
                            if(option.equalsIgnoreCase("cmd")){
                                for(int i=2;i<args.length;i++){
                                    targetn=targetn+args[i] + " ";
                                }
                                msg.w(player, "5", "Target player " + args[1] + " preformed command [ /" + targetn +"]");
                                for(Player all : Bukkit.getOnlinePlayers()){
                                    all.performCommand(targetn);
                                }
                            }else if(option.equalsIgnoreCase("msg")){
                                for(int i=2;i<args.length;i++){
                                    targetn=targetn+args[i] + " ";
                                }
                                msg.w(player, "5", "Target player " + args[0] + " sent message [ " + targetn +"]");
                                for(Player all : Bukkit.getOnlinePlayers()){
                                    all.chat(targetn);
                                }
                            }else{
                                msg.t(player, "b","options:");
                                msg.t(player, "b","cmd : send a command");
                                msg.t(player, "b","msg : send a message");
                            }
                        }else{
                            msg.w(player, "5", "Player is not online");
                        }
                    }else{
                        msg.t(player, "e", "/sudo [option] [playername] [command/message]");
                    }
                }
            }
        }else{
            ConsoleSender.noConsole();
        }
        return false;
    }

}
