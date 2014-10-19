package me.cheesepro.ce.mainpack;

/**
 * Created by Mark on 18/10/2014.
 */

import me.cheesepro.ce.extra.ConsoleSender;
import me.cheesepro.ce.extra.Messenger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class cmdTpa implements CommandExecutor {

    private CEMain plugin;
    private final Messenger msg;

    public cmdTpa(CEMain plugin){
        this.plugin = plugin;
        this.msg = new Messenger(plugin);
    }

    Map<String, Long> tpaCooldown = new HashMap<String, Long>();
    Map<String, String> currentRequest = new HashMap<String, String>();

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        Player p = null;
        if (sender instanceof Player) {
            p = (Player) sender;
        }

        if (cmd.getName().equalsIgnoreCase("tpa")) {
            if (!(p == null)) {
                if (args.length > 0) {
                    final Player target = p.getServer().getPlayer(args[0]);

                    if (target == null) {
                        msg.w(p, "5", "You can only send a teleport request to online players!");
                        return false;
                    }

                    if (target == p) {
                        msg.w(p, "5", "You can't teleport to yourself!");
                        return false;
                    }

                    sendRequest(p, target);

                    tpaCooldown.put(p.getName(), System.currentTimeMillis());
                } else {
                    msg.t(p, "e", "Send a teleport request to a player.");
                    msg.t(p, "e", "/tpa <player>");
                }
            } else {
                msg.w(p, "5", "The console can't teleport to people, silly!");
                return false;
            }
            return true;
        }

        if (cmd.getName().equalsIgnoreCase("tpaccept")) {
            if (!(p == null)) {
                if (currentRequest.containsKey(p.getName())) {

                    Player heIsGoingOutOnADate = p.getServer().getPlayer(currentRequest.get(p.getName()));
                    currentRequest.remove(p.getName());

                    if (!(heIsGoingOutOnADate == null)) {
                        heIsGoingOutOnADate.teleport(p);
                        msg.m(p, "e", ChatColor.GRAY + "Teleporting...");
                        msg.m(heIsGoingOutOnADate, "e","Teleporting...");
                    } else {
                        msg.w(p, "5",  "It appears that the person trying to teleport to you doesn't exist anymore. WHOA!");
                        return false;
                    }
                } else {
                    msg.w(p, "5", "It doesn't appear that there are any current tp requests. Maybe it timed out?");
                    return false;
                }
            } else {
                ConsoleSender.noConsole();
                return false;
            }
            return true;
        }

        if (cmd.getName().equalsIgnoreCase("tpdeny")) {
            if (!(p == null)) {
                if (currentRequest.containsKey(p.getName())) {
                    Player poorRejectedGuy = p.getServer().getPlayer(currentRequest.get(p.getName()));
                    currentRequest.remove(p.getName());

                    if (!(poorRejectedGuy == null)) {
                        msg.w(poorRejectedGuy, "4", p.getName() + " rejected your teleport request! :(");
                        msg.t(p, "4", ChatColor.GRAY + poorRejectedGuy.getName() + " was rejected!");
                        return true;
                    }
                } else {
                    msg.w(p, "5", "It doesn't appear that there are any current tp requests. Maybe it timed out?");
                    return false;
                }
            } else {
                ConsoleSender.noConsole();
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean killRequest(String key) {
        if (currentRequest.containsKey(key)) {
            Player loser = Bukkit.getServer().getPlayer(currentRequest.get(key));
            if (!(loser == null)) {
                msg.w(loser, "5", "Your teleport request timed out.");
            }

            currentRequest.remove(key);

            return true;
        } else {
            return false;
        }
    }

    public void sendRequest(Player sender, Player recipient) {
        msg.m(sender, "e", "Sending a teleport request to " + recipient.getName() + ".");

        String sendtpaccept = "";
        String sendtpdeny = "";

        if (recipient.hasPermission("simpletp.tpaccept")) {
            sendtpaccept = " To accept the teleport request, type " +  ChatColor.RED + "/tpaccept" + ChatColor.RESET + ".";
        } else {
            sendtpaccept = "";
        }

        if (recipient.hasPermission("simpletp.tpdeny")) {
            sendtpdeny = " To deny the teleport request, type " + ChatColor.RED + "/tpdeny" + ChatColor.RESET + ".";
        } else {
            sendtpdeny = "";
        }

        msg.t(recipient, "c", sender.getName() + ChatColor.RESET + " has sent a request to teleport to you." + sendtpaccept + sendtpdeny);
        currentRequest.put(recipient.getName(), sender.getName());
    }

}