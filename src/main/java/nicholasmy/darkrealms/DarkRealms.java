package nicholasmy.darkrealms;

import org.bukkit.plugin.java.JavaPlugin;

public final class DarkRealms extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getConfig().options().copyDefaults(true); // Update config file when the plugin is updated
        this.saveDefaultConfig(); // Create a new config file if it doesn't exist
        this.saveConfig(); // Update the existing config file to have all the new fields from the latest version of the plugin
        this.getCommand("darkrealms").setExecutor(new CommandDarkRealms(this));
        this.getCommand("dr").setExecutor(new CommandDarkRealms(this));
        this.getCommand("allow").setExecutor(new CommandAllow(this));
        this.getCommand("fly").setExecutor(new CommandFly(this));
        this.getCommand("spawn").setExecutor(new CommandSpawn(this));
        this.getCommand("setspawn").setExecutor(new CommandSetSpawn(this));
        this.getCommand("speed").setExecutor(new CommandSpeed(this));
        getServer().getPluginManager().registerEvents(new RespawnListener(this), this);


        Utils utils = new Utils(this); // Initialize Utils to reference this plugin

        // TODO planned features:
        /*
        - DarkRealms command to show info about this plugin
        - Lookup command to show information about yourself or another player
        - Gamemode commands
        - sethome and home (make a config for each player named by uuid)
        - colored chat
        - namecolor
        - prefixes (maybe)
        - weather and time
         */

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        this.saveConfig();
    }
}
