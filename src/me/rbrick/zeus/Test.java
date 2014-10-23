package me.rbrick.zeus;

import me.rbrick.zeus.annotations.Command;
import me.rbrick.zeus.registers.bukkit.BukkitRegistrar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Ryan on 10/22/2014
 * <p/>
 * Project: Zeus
 */
public class Test extends JavaPlugin {

    @Command(name = "test")
    public void test(Player player, String[] args) {

    }


//    @SubCommand(parent = "test", name = "test")
//    public void testSubCommand(Player player, String[] args) {
//        player.sendMessage("It works!");
//
//        // if not i am killing myself
//    }


    @Override
    public void onEnable() {
        BukkitRegistrar registrar = new BukkitRegistrar();
        registrar.registerAll(this);
    //    registrar.registerAllSubCommands(this);
    }
}
