package me.rbrick.zeus;

import me.rbrick.zeus.registers.bukkit.BukkitRegistrar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.lang.reflect.Method;
import java.util.List;

public class ZeusCommand extends Command  {

    Object obj;

    int maxArgs;
    int minArgs;

    String[] args;

    public ZeusCommand(String name, String description, String usageMessage, List<String> aliases, Object obj) {
        super(name, description, usageMessage, aliases);
        this.obj = obj;
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        this.args = args;
        if(!sender.hasPermission(getPermission()) && !getPermission().isEmpty()) {
            sender.sendMessage(getPermissionMessage());
            return true;
        }
        if(!(maxArgs < 0)) {
            if(args.length > maxArgs) {
                sender.sendMessage(getUsage().replace("<command>", label));
                return true;
            }
        }

        if(!(minArgs < 0)) {
            if(args.length < minArgs) {
                sender.sendMessage(getUsage().replace("<command>", label));
                return true;
            }
        }




        try {
//            if(args.length != 0) {
//                if(hasSubCommand(args)) {
//                  BukkitRegistrar.getRegisteredZeusSubCommands().get(args[0]).execute(sender, fixArgs(args));
//                  return true;
//                }
//            }

            Method m = BukkitRegistrar.getRegisteredCommands().get(this.getName());
            m.invoke(obj, sender, args);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public Object getObj() {
        return obj;
    }


    public void setMaxArgs(int maxArgs) {
        this.maxArgs = maxArgs;
    }

    public void setMinArgs(int minArgs) {
        this.minArgs = minArgs;
    }

    public int getMaxArgs() {
        return maxArgs;
    }

    public int getMinArgs() {
        return minArgs;
    }

//    public boolean hasSubCommand(String[] args) {
//        return BukkitRegistrar.getRawRegisteredSubcommands().get(getName()).containsKey(args[0]);
//    }

    public String[] fixArgs(String[] args) {
        String[] subArgs = new String[args.length - 1];
        for (int i = 1; i < args.length; i++) {
            subArgs[i - 1] = args[i];
        }
        return subArgs;
    }

    public String[] getArgs() {
        return args;
    }
}
