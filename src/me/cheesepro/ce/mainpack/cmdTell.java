package me.cheesepro.ce.mainpack;

import me.cheesepro.ce.extra.Messenger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Mark on 08/11/2014.
 */
public class cmdTell implements CommandExecutor{

    private CEMain plugin;
    private final Messenger msg;

    public cmdTell(CEMain plugin){
        this.plugin = plugin;
        this.msg = new Messenger(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if(cmd.getLabel().equalsIgnoreCase("tell") || cmd.getLabel().equalsIgnoreCase("msg")){
                if(args.length>=1){
                    Player target = p.getServer().getPlayer(args[0]);
                    if(target!=null){
                        String input="";
                        for(int i=1;i<args.length;i++){
                            input = input + args[i] + " ";
                        }
                        msg.m(p, "f", "[You]" + " -> " + "[" + target.getName()+ "] " + input);
                        msg.m(target, "f", "[" + p.getName() + "] -> " + "[You] " + input);
                    }else{
                        msg.w(p, "4", "Target not found");
                    }
                }else{
                    msg.t(p, "e", "/tell [name]");
                }
            }
        }
        return false;
    }

}
