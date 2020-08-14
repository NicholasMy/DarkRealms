package nicholasmy.darkrealms;

import org.bukkit.plugin.java.JavaPlugin;

public final class DarkRealms extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getConfig().options().copyDefaults(true); // Update config file when the plugin is updated
        this.saveDefaultConfig(); // Create a new config file if it doesn't exist
        this.saveConfig(); // Update the existing config file to have all the new fields from the latest version of the plugin
        this.getCommand("allow").setExecutor(new CommandAllow(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
            this.saveConfig();
    }
}
