package me.rbrick.zeus.registers;


public interface Registrar {

    /**
     * Registers only the command that is equal to the name.
     *
     * @param clazz
     * @param name
     */
    void registerCommand(Class<?> clazz, String name, Object obj);


    /**
     * Registers all commands in a class that has the @Command annotation.
     *
     * @param clazz
     */
    void registerAll(Class<?> clazz, Object obj);

}
