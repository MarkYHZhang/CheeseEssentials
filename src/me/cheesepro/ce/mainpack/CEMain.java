package me.cheesepro.ce.mainpack;

import java.io.File;

import me.cheesepro.ce.file.*;
import me.cheesepro.ce.listener.*;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.Plugin;

public class CEMain extends JavaPlugin implements Listener{

    private static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;

        //Start of Files Configs
        playerdata pdata = playerdata.getInstance();
        pdata.setup(this);
        if (!new File(getDataFolder(), "playerdata.yml").exists()) {
            saveDefaultConfig();
        }

        getConfig().options().copyDefaults(true);
        saveConfig();
        //End of Files Configs

        //Start of Commands Register
        cmdgm cmdgmInstance = new cmdgm(this);
        cmdhf cmdhfInstance = new cmdhf(this);
        cmdFly cmdFlyInstance = new cmdFly(this);
        cmdAdminChat cmdAdminChatInstance = new cmdAdminChat(this);
        cmdTP cmdTPInstance = new cmdTP(this);
        cmdSpeed cmdSpeedInstance = new cmdSpeed(this);
        cmdJump cmdJumpInstance = new cmdJump(this);
        cmdHat cmdHatInstance = new cmdHat(this);
        cmdInvsee cmdInvseeInstance = new cmdInvsee(this);
        cmdTpa cmdTpaInstance = new cmdTpa(this);
        //End of Command Register

        //Start of Listeners Register
        new onPlayerjoin(this);
        new onServerPing(this);
        new cmdOverride(this);
        new AdminChat(this);
        new Playerloc(this);
        new chatFormatter(this);

        //End of Listeners Register

        //Start of Command getters
        getCommand("gmc").setExecutor(cmdgmInstance);
        getCommand("gms").setExecutor(cmdgmInstance);
        getCommand("heal").setExecutor(cmdhfInstance);
        getCommand("feed").setExecutor(cmdhfInstance);
        getCommand("fly").setExecutor(cmdFlyInstance);
        getCommand("speed").setExecutor(cmdSpeedInstance);
        getCommand("adminchat").setExecutor(cmdAdminChatInstance);
        getCommand("tp").setExecutor(cmdTPInstance);
        getCommand("tpall").setExecutor(cmdTPInstance);
        getCommand("back").setExecutor(cmdTPInstance);
        getCommand("logoutloc").setExecutor(cmdTPInstance);
        getCommand("jump").setExecutor(cmdJumpInstance);
        getCommand("hat").setExecutor(cmdHatInstance);
        getCommand("invsee").setExecutor(cmdInvseeInstance);
        getCommand("tpa").setExecutor(cmdTpaInstance);
        getCommand("tpaccept").setExecutor(cmdTpaInstance);
        getCommand("tpdeny").setExecutor(cmdTpaInstance);
        //End of Command getters

        getLogger().info("Plugin Enabled!");
    }

    public void onDisable() {
        getLogger().info("Plugin Disabled!");
    }


//    public static void registerEvents(org.bukkit.plugin.Plugin plugin,
//                                      Listener... listeners) {
//        for (Listener listener : listeners) {
//            Bukkit.getServer().getPluginManager()
//                    .registerEvents(listener, plugin);
//        }
//
//
//    }

    public static Plugin getPlugin() {
        return plugin;
    }
}
