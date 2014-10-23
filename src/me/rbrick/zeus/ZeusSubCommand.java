package me.rbrick.zeus;

/**
 * Created by Ryan on 10/22/2014
 * <p/>
 * Project: Zeus
 */
public class ZeusSubCommand {

   String parent;
   String name;
   String[] aliases;
   String[] args;
   Object instance;

    public ZeusSubCommand(String parent,String name, String[] aliases,String[] args, Object instance) {
        this.name = name;
        this.parent = parent;
        this.aliases = aliases;
        this.args = args;
        this.instance = instance;
    }

//    public void execute(CommandSender sender, String[] args) {
//       try {
//          Method method = BukkitRegistrar.getRawRegisteredSubcommands().get(parent).get(name);
//          method.invoke(instance, sender, args);
//       } catch (Exception ex) {
//          ex.printStackTrace();
//       }
//    }
}
