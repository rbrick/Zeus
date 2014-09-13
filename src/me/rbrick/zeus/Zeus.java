package me.rbrick.zeus;

import me.rbrick.zeus.annotations.Command;
import me.rbrick.zeus.registers.Registrar;
import me.rbrick.zeus.registers.bukkit.BukkitRegistrar;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Method;
import java.util.HashMap;

public class Zeus extends JavaPlugin {

    Registrar registrar;

    static HashMap<String, Method> registeredCommands = new HashMap<String, Method>();

    public void onEnable() {
        registrar = new BukkitRegistrar();
        registrar.registerAll(Zeus.class, this);
    }


    @Command(name = "hello", aliases = {"h"}, desc = "Sends a nice message!", usage = "/<command>")
    public void hello(CommandSender sender, String[] args) {
        sender.sendMessage("Hello!");
    }

    @Command(name="test")
    public void test(CommandSender sender, String[] args) {
        if(sender instanceof Player) {
            sender.sendMessage(ChatColor.RED + "You are a player!");
        } else {
            sender.sendMessage(ChatColor.RED + "You are not a player!");
        }

    }


    public static HashMap<String, Method> getRegisteredCommands() {
        return registeredCommands;
    }
}
