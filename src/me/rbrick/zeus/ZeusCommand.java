package me.rbrick.zeus;

import me.rbrick.zeus.registers.bukkit.BukkitRegistrar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.lang.reflect.Method;
import java.util.List;

public class ZeusCommand extends Command  {

    Object obj;

    public ZeusCommand(String name, String description, String usageMessage, List<String> aliases, Object obj) {
        super(name, description, usageMessage, aliases);
        this.obj = obj;

    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        try {
            Method m = BukkitRegistrar.getRegisteredCommands().get(this.getName());
            m.invoke(obj, sender, args );
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }


}
