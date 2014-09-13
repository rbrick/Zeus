package me.rbrick.zeus.registers.bukkit;

import me.rbrick.zeus.Zeus;
import me.rbrick.zeus.ZeusCommand;
import me.rbrick.zeus.annotations.Command;
import me.rbrick.zeus.exceptions.InvalidMethodException;
import me.rbrick.zeus.registers.Registrar;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/*
 This class is for registering Bukkit commands(Which is the main goal of the library.)
 Will eventually move on too other APIs since Bukkit is probably not going to update to 1.8 :(
 */
public class BukkitRegistrar implements Registrar {

    @Override
    public void registerCommand(Class<?> clazz, String name, Plugin plugin) {
        for(Method m : clazz.getMethods()) {
           if(m.isAnnotationPresent(Command.class)) {
               Command command = (Command) m.getAnnotation(Command.class);
               if(command.name().equalsIgnoreCase(name)) {
                   // TODO Inject commands into Bukkit.
                   try {
                       Constructor<?> commandConstructor =  ZeusCommand.class.getDeclaredConstructor(String.class, String.class, String.class, List.class, Plugin.class);
                       commandConstructor.setAccessible(true);
                       System.out.println("Successfully hooked into org.bukkit.command.Command");

                       org.bukkit.command.Command command1 = (ZeusCommand) commandConstructor.newInstance(command.name(), command.desc(), command.usage(), Arrays.asList(command.aliases()), plugin);

                       command1.setPermission(command.permission());

                       System.out.println("Successfully created new org.bukkit.command.Command.\nInjecting...");

                       // Confirm that the method is correct.
                       if(m.getParameterTypes() == null || m.getParameterTypes() != new Class[] {CommandSender.class, String[].class}) {
                           throw new InvalidMethodException("Invalid parameter types!");
                       }

                       Zeus.getRegisteredCommands().put(command.name(), m);

                       System.out.println(m.getDeclaringClass().getName());

                       Field field =  Bukkit.getServer().getPluginManager().getClass().getDeclaredField("commandMap");
                       field.setAccessible(true);

                       CommandMap map = (CommandMap) field.get(Bukkit.getServer().getPluginManager());

                       map.register(command.name(), command1);


                       System.out.println("Successfully injected command '" + command.name() + "'.");



                   } catch (Exception ex) {
                       ex.printStackTrace();
                   }
                   break;
               }
           }
        }
    }

    @Override
    public void registerAll(Class<?> clazz, Plugin plugin) {
        for(Method m : clazz.getMethods()) {
            if(m.isAnnotationPresent(Command.class)) {
                Command command = (Command) m.getAnnotation(Command.class);
                // TODO Inject commands into Bukkit.
                try {
                    Constructor<?> commandConstructor =  ZeusCommand.class.getDeclaredConstructor(String.class, String.class, String.class, List.class, Plugin.class);
                    commandConstructor.setAccessible(true);
                    System.out.println("Successfully hooked into org.bukkit.command.Command");

                    org.bukkit.command.Command command1 = (ZeusCommand) commandConstructor.newInstance(command.name(), command.desc(), command.usage(), Arrays.asList(command.aliases()), plugin);

                    command1.setPermission(command.permission());

                    System.out.println("Successfully created new org.bukkit.command.Command.\nInjecting...");

                    // Confirm that the method is correct.
                    if(m.getParameterTypes() == null || m.getParameterTypes()[0] != CommandSender.class && m.getParameterTypes()[1] != String[].class) {
                        throw new InvalidMethodException("Invalid parameter types!");
                    }

                    Zeus.getRegisteredCommands().put(command.name(), m);

                    System.out.println(m.getDeclaringClass().getName());

                    Field field =  Bukkit.getServer().getPluginManager().getClass().getDeclaredField("commandMap");
                    field.setAccessible(true);
                    CommandMap map = (CommandMap) field.get(Bukkit.getServer().getPluginManager());

                    map.register(command.name(), command1);

                    System.out.println("Successfully injected command '" + command.name() + "'.");

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
