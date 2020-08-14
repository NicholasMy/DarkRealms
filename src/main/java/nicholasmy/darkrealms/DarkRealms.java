package nicholasmy.darkrealms;

import org.bukkit.plugin.java.JavaPlugin;

public final class DarkRealms extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getConfig().options().copyDefaults(true); // Update config file when the plugin is updated
        this.saveDefaultConfig();
        this.getCommand("allow").setExecutor(new CommandAllow(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        this.saveConfig();
    }
}
