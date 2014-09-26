Zeus
====

An annotation based command system inspired by @sk89q 's and @DSH105 's command systems projects.  

### Features
+ An annotated command system.
+ No need to register your plugins into your plugin.yml
+ Easy class based command registration.
+ Simple to use!
+ Override default Bukkit commands by default!

### TODO
+ Sub-command system.
+ Custom methods instead of a set method.

### Documentation

How to create a command:
 At the moment you must have your command method consist of two parameters.
 `CommandSender` and `String[]`, in that order. The arguments can be named anything of course.
 It is recommended for the method to be void.

 To specify that method is a command, you must have the `@Command` annotation.
 You can specify various command information, but the one thing needed is the `name` parameter.
 `name` is simply the name of the command. Below are other parameters:
   + `aliases` Type: `String[]` About: The aliases for the command.
   + `desc` Type: `String` About: The description of the command.
   + `usage` Type: `String` About: The usage of the command.
   + `permission` Type: `String` About: The permission used for the command.

 Example(A custom /stop command for teh lawls):
 ```java
 @Command(name="stop", aliases = {"staph"}, usage = "/<command>", permission = "admin.stop", desc = "Stops the server.")
    public void stopServer(CommandSender sender, String[] args) {
       sender.sendMessage(ChatColor.GREEN + "Stopping server...");
        Bukkit.getServer().savePlayers();
        for(World world : Bukkit.getServer().getWorlds()) {
            world.save();
        }
        Bukkit.getServer().shutdown();
    }
 ```

How to register command(s):
  In your main class have a variable like this:
    `Registrar registrar;`
  Then in your onEnable initialize it:
    `registrar = new BukkitRegistrar();`

  If you want to register all methods(commands) in a class simply do:
    ```
    registrar.registerAll(<NameOfClass>.class, <Instance of class annotation is in>); 
    ```
   and it will register every method that has the `@Command` annotation and the proper parameters.

 If you want to register just one command:
   ```java
   registrar.registerCommand(<NameOfClass>.class, <NameOfCommand>, <Instance of class annotation is in>);
   ```
 and it will only register that one command.

