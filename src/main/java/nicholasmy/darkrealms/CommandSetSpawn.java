package nicholasmy.darkrealms;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class CommandSetSpawn implements CommandExecutor {

    private DarkRealms darkRealms;

    public CommandSetSpawn(DarkRealms darkRealms) {
        this.darkRealms = darkRealms;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only in-game players can run this command.");
            return true;
        }

        Player p = (Player) sender;
        if (Utils.playerHasPermission(p, "set_spawn")) {
            try {
                Location location = p.getLocation();
                double x = location.getX();
                double y = location.getY();
                double z = location.getZ();
                double yaw = location.getY();
                double pitch = location.getPitch();
                String worldName = location.getWorld().getName();
                FileConfiguration config = darkRealms.getConfig();
                config.set("spawn.x", x);
                config.set("spawn.y", y);
                config.set("spawn.z", z);
                config.set("spawn.yaw", yaw);
                config.set("spawn.pitch", pitch);
                config.set("spawn.world", worldName);
                darkRealms.saveConfig();
                p.sendMessage(ChatColor.YELLOW + "Successfully set this Dark Realm's spawn location to X: " + x + ", Y: " + y + ", Z: " + z + ", Yaw: " + yaw + ", Pitch: " + pitch + "World: '" + worldName + ". This is where the /spawn command will teleport players.");
            } catch (Exception ignored) {
                p.sendMessage(ChatColor.RED + "There was an error setting the spawn point. Please contact the owner of this Dark Realm for help.");
            }
        } else {
            p.sendMessage(ChatColor.RED + "You don't have permission to set the spawn point. Contact the owner of this Dark Realm if you believe this is an error.");
        }
        return true;
    }
}
