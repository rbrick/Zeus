package me.rbrick.zeus.registers.bukkit;

import me.rbrick.zeus.ZeusCommand;
import me.rbrick.zeus.annotations.Command;
import me.rbrick.zeus.exceptions.InvalidMethodException;
import me.rbrick.zeus.registers.Registrar;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * This code is copyrighted by rbrick and the BreakMC Network.
 */
public class BukkitRegistrar implements Registrar {
    static HashMap<String, Method> registeredCommands = new HashMap<String, Method>();

    @Override
    public void registerCommand(Class<?> clazz, String name, Object obj) {
        for(Method m : clazz.getMethods()) {
            if(m.isAnnotationPresent(Command.class)) {
                Command command = (Command) m.getAnnotation(Command.class);
                if(command.name().equalsIgnoreCase(name)) {
                    // TODO Inject commands into Bukkit.
                    try {
                        Constructor<?> commandConstructor =  ZeusCommand.class.getDeclaredConstructor(String.class, String.class, String.class, List.class, Object.class);
                        commandConstructor.setAccessible(true);
                        System.out.println("Successfully hooked into org.bukkit.command.Command");

                        ZeusCommand command1 = (ZeusCommand) commandConstructor.newInstance(command.name(), command.desc(), command.usage(), Arrays.asList(command.aliases()), obj);

                        command1.setPermission(command.permission());
                        command1.setPermissionMessage((command.permissionMsg().isEmpty() ? command1.getPermissionMessage() : command.permissionMsg()));
                        command1.setMaxArgs(command.maxArgs());
                        command1.setMinArgs(command.minArgs());

                        System.out.println("Successfully created new org.bukkit.command.Command.\nInjecting...");




                        // Confirm that the method is correct.
                        if(m.getParameterTypes() == null || m.getParameterTypes()[0] != CommandSender.class && m.getParameterTypes()[1] != String[].class) {
                            throw new InvalidMethodException("Invalid parameter types!");
                        }

                        registeredCommands.put(command.name(), m);

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
    public void registerAll(Class<?> clazz, Object obj) {
        for(Method m : clazz.getMethods()) {
            if(m.isAnnotationPresent(Command.class)) {
                Command command = (Command) m.getAnnotation(Command.class);
                // TODO Inject commands into Bukkit.
                try {
                    Constructor<?> commandConstructor =  ZeusCommand.class.getDeclaredConstructor(String.class, String.class, String.class, List.class, Object.class);
                    commandConstructor.setAccessible(true);
                    System.out.println("Successfully hooked into org.bukkit.command.Command");

                    ZeusCommand command1 = (ZeusCommand) commandConstructor.newInstance(command.name(), command.desc(), command.usage(), Arrays.asList(command.aliases()), obj);

                    command1.setPermission(command.permission());

                    command1.setPermissionMessage((command.permissionMsg().isEmpty() ? command1.getPermissionMessage() : command.permissionMsg()));

                    command1.setMaxArgs(command.maxArgs());
                    command1.setMinArgs(command.minArgs());

                    System.out.println("Successfully created new org.bukkit.command.Command.\nInjecting...");

                    // Confirm that the method is correct.
                    if(m.getParameterTypes() == null || m.getParameterTypes()[0] != CommandSender.class && m.getParameterTypes()[1] != String[].class) {
                        throw new InvalidMethodException("Invalid parameter types!");
                    }

                    registeredCommands.put(command.name(), m);

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

    public static HashMap<String, Method> getRegisteredCommands() {
        return registeredCommands;
    }
}
