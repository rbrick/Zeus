package me.rbrick.zeus.registers;

import org.bukkit.plugin.Plugin;

public interface Registrar {

    /**
     * Registers only the command that is equal to the name.
     *
     * @param clazz
     * @param name
     */
    void registerCommand(Class<?> clazz, String name, Plugin plugin);


    /**
     * Registers all commands in a class that has the @Command annotation.
     *
     * @param clazz
     */
    void registerAll(Class<?> clazz, Plugin plugin);

}
