package nicholasmy.darkrealms;

import org.bukkit.plugin.java.JavaPlugin;

public final class DarkRealms extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.saveDefaultConfig();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}