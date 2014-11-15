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

        homeloc hloc = homeloc.getInstance();
        hloc.setup(this);
        if (!new File(getDataFolder(), "homeloc.yml").exists()) {
            saveDefaultConfig();
        }

        spawnloc sloc = spawnloc.getInstance();
        sloc.setup(this);
        if (!new File(getDataFolder(), "spawnloc.yml").exists()) {
            saveDefaultConfig();
        }

        hubloc hbloc = hubloc.getInstance();
        hbloc.setup(this);
        if (!new File(getDataFolder(), "hubloc.yml").exists()) {
            saveDefaultConfig();
        }

//        File dir = new File("plugins/CheeseEssentials/locations");
//        dir.mkdir();

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
//        cmdBarmsg cmdBarmsgInstance = new cmdBarmsg(this);
        cmdWorkbench cmdWorkbenchInstance = new cmdWorkbench(this);
        cmdClear cmdClearInstance = new cmdClear(this);
        cmdWarp cmdWarpInstance = new cmdWarp(this);
        cmdSpawn cmdSpawnInstance = new cmdSpawn(this);
        cmdSysinfo cmdSysinfoInstance = new cmdSysinfo(this);
        cmdHome cmdHomeInstance = new cmdHome(this);
        cmdHub cmdHubInstance = new cmdHub(this);
        cmdSlap cmdSlapInstance = new cmdSlap(this);
        cmdRocket cmdRocketInstance = new cmdRocket(this);
        cmdLightning cmdLightningInstance = new cmdLightning(this);
        cmdTell cmdTellInstance = new cmdTell(this);
        cmdMute cmdMuteInstance = new cmdMute(this);
        cmdKill cmdKillInstance = new cmdKill(this);
        cmdSpam cmdSpamInstance = new cmdSpam(this);
        cmdBroadcast cmdBroadcastInstance = new cmdBroadcast(this);
        cmdCchat cmdCchatInstance = new cmdCchat(this);
        cmdVanish cmdVanishInstance = new cmdVanish(this);
        cmdTime cmdTimeInstance = new cmdTime(this);
        cmdSuicide cmdSuicideInstance = new cmdSuicide(this);
        cmdClaim cmdClaimInstance = new cmdClaim(this);
        cmdSeen cmdSeenInstance = new cmdSeen(this);
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
        new coloredSign(this);
        new onPlayerRespawn(this);

        registerEvents(this, cmdMuteInstance);
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
//        getCommand("barmsg").setExecutor(cmdBarmsgInstance);
        getCommand("workbench").setExecutor(cmdWorkbenchInstance);
        getCommand("clear").setExecutor(cmdClearInstance);
        getCommand("warplist").setExecutor(cmdWarpInstance);
        getCommand("warp").setExecutor(cmdWarpInstance);
        getCommand("setwarp").setExecutor(cmdWarpInstance);
        getCommand("delwarp").setExecutor(cmdWarpInstance);
        getCommand("spawn").setExecutor(cmdSpawnInstance);
        getCommand("setspawn").setExecutor(cmdSpawnInstance);
        getCommand("delspawn").setExecutor(cmdSpawnInstance);
        getCommand("sysinfo").setExecutor(cmdSysinfoInstance);
        getCommand("homelist").setExecutor(cmdHomeInstance);
        getCommand("home").setExecutor(cmdHomeInstance);
        getCommand("sethome").setExecutor(cmdHomeInstance);
        getCommand("delhome").setExecutor(cmdHomeInstance);
        getCommand("hub").setExecutor(cmdHubInstance);
        getCommand("sethub").setExecutor(cmdHubInstance);
        getCommand("delhub").setExecutor(cmdHubInstance);
        getCommand("slap").setExecutor(cmdSlapInstance);
        getCommand("rocket").setExecutor(cmdRocketInstance);
        getCommand("lightning").setExecutor(cmdLightningInstance);
        getCommand("tell").setExecutor(cmdTellInstance);
        getCommand("msg").setExecutor(cmdTellInstance);
        getCommand("mute").setExecutor(cmdMuteInstance);
        getCommand("unmute").setExecutor(cmdMuteInstance);
        getCommand("kill").setExecutor(cmdKillInstance);
        getCommand("spam").setExecutor(cmdSpamInstance);
        getCommand("broadcast").setExecutor(cmdBroadcastInstance);
        getCommand("cchat").setExecutor(cmdCchatInstance);
        getCommand("vanish").setExecutor(cmdVanishInstance);
        getCommand("day").setExecutor(cmdTimeInstance);
        getCommand("night").setExecutor(cmdTimeInstance);
        getCommand("suicide").setExecutor(cmdSuicideInstance);
        getCommand("claim").setExecutor(cmdClaimInstance);
        getCommand("seen").setExecutor(cmdSeenInstance);
        //End of Command getters

        getLogger().info("Plugin Enabled!");
    }

    public static void registerEvents(org.bukkit.plugin.Plugin plugin,
                                      Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getServer().getPluginManager()
                    .registerEvents(listener, plugin);
        }
    }

    public void onDisable() {
        getLogger().info("Plugin Disabled!");
    }

    public static Plugin getPlugin() {
        return plugin;
    }
}
