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

        warploc wloc = warploc.getInstance();
        wloc.setup(this);
        if (!new File(getDataFolder(), "warploc.yml").exists()) {
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
        cmdSudo cmdSudoInstance = new cmdSudo(this);
        cmdBarmsg cmdBarmsgInstance = new cmdBarmsg(this);
        cmdWorkbench cmdWorkbenchInstance = new cmdWorkbench(this);
        cmdClear cmdClearInstance = new cmdClear(this);
        cmdWarp cmdWarpInstance = new cmdWarp(this);
        //End of Command Register

        //Start of Listeners Register
        new onPlayerjoin(this);
        new onPlayerquit(this);
        new onServerPing(this);
        new cmdOverride(this);
        new AdminChat(this);
        new Playerloc(this);
        new chatFormatter(this);
        new onPlayerdeath(this);
        new warpSign(this);

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
        getCommand("sudo").setExecutor(cmdSudoInstance);
        getCommand("barmsg").setExecutor(cmdBarmsgInstance);
        getCommand("workbench").setExecutor(cmdWorkbenchInstance);
        getCommand("clear").setExecutor(cmdClearInstance);
        getCommand("warplist").setExecutor(cmdWarpInstance);
        getCommand("warp").setExecutor(cmdWarpInstance);
        getCommand("setwarp").setExecutor(cmdWarpInstance);
        getCommand("delwarp").setExecutor(cmdWarpInstance);
        //End of Command getters

        getLogger().info("Plugin Enabled!");
    }

    public void onDisable() {
        getLogger().info("Plugin Disabled!");
    }

    public static Plugin getPlugin() {
        return plugin;
    }
}
