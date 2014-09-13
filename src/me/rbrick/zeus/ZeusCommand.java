package me.rbrick.zeus;

import me.rbrick.zeus.registers.bukkit.BukkitRegistrar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Method;
import java.util.List;

public class ZeusCommand extends Command implements PluginIdentifiableCommand  {

    Plugin owningPlugin;

    public ZeusCommand(String name, String description, String usageMessage, List<String> aliases, Plugin owningPlugin) {
        super(name, description, usageMessage, aliases);
        this.owningPlugin = owningPlugin;
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
            try {
                Method m = BukkitRegistrar.getRegisteredCommands().get(this.getName());
                m.invoke(owningPlugin, sender, args );
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        return false;
    }

    @Override
    public Plugin getPlugin() {
        return owningPlugin;
    }
}
