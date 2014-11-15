package me.cheesepro.ce.mainpack;

import me.cheesepro.ce.extra.Messenger;
import java.sql.Date;
import java.text.SimpleDateFormat;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

/**
 * Created by Mark on 13/11/2014.
 */
public class cmdSeen implements CommandExecutor{

    private CEMain plugin;
    private final Messenger msg;

    public cmdSeen(CEMain plugin){
        this.plugin = plugin;
        this.msg = new Messenger(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if(cmd.getLabel().equalsIgnoreCase("seen")){
                if(args.length==1){
                    OfflinePlayer target = p.getServer().getOfflinePlayer(args[0]);
                    if(target.getLastPlayed()!=0){
                        String time = getCurrentDTG(target.getLastPlayed());
                        msg.m(p, "e", target.getName() + " left the game at " + time);
                    }else{
                        msg.w(p, "c", "Target not found");
                    }
                }else{
                    msg.t(p, "a", "/seen [name]");
                }
            }
        }else{

        }
        return false;
    }

    public static String getCurrentDTG(long l_time)
    {
        Date date = new Date(l_time);
        SimpleDateFormat dtgFormat = new SimpleDateFormat("[hh:mm MM/dd/yyyy]");
        return dtgFormat.format(date);
    }

}
