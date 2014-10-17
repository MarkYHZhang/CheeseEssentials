package me.cheesepro.ce.extra;

import org.bukkit.Bukkit;

/**
 * Created by Mark on 06/10/2014.
 */
public class ConsoleSender{

    public static void msgConsole(String msg){
        Bukkit.getConsoleSender().sendMessage(msg);
    }

    public static void noConsole(){
        Bukkit.getLogger().info("Command Executor must be a in-game Player!");
    }

}
