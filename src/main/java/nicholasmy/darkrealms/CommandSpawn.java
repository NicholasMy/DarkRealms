package nicholasmy.darkrealms;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSpawn implements CommandExecutor {

    private DarkRealms darkRealms;

    public CommandSpawn(DarkRealms darkRealms) {
        this.darkRealms = darkRealms;
    }

    private boolean sendPlayerToSpawn(Player p) {
        try {
            Location spawnLocation = Utils.getSpawnLocation();
            p.teleport(spawnLocation);
            return true;
        } catch (Exception ignored) {
            return false; // Failed to get spawn location
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            // Teleporting self
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (Utils.playerHasPermission(p, "spawn")) {
                    if (sendPlayerToSpawn(p)) {
                        // If they were successfully sent to spawn
                        p.sendMessage(ChatColor.YELLOW + "You've been teleported to spawn!");
                    } else {
                        // There was an error teleporting
                        p.sendMessage(ChatColor.RED + "There was an error teleporting you to spawn. Contact the owner of this Dark Realm if you believe this is an error.");
                    }
                } else {
                    p.sendMessage(ChatColor.RED + "You don't have permission to teleport to spawn. Contact the owner of this Dark Realm if you believe this is an error.");
                    return true;
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Only players may use this command without a target. From the console or a command block, try /spawn [player].");
            }
        } else if (args.length == 1) {
            // Teleporting a target to spawn
            Player p = Utils.getPlayerSenderOrNull(sender);
            boolean allowed = (p == null) || Utils.playerHasPermission(p, "spawn_others"); // Is the player allowed to run this command on another player?
            if (!allowed) {
                p.sendMessage(ChatColor.RED + "You don't have permission to teleport other players to spawn. Contact the owner of this Dark Realm if you believe this is an error.");
                return true;
            }
            // At this point, the sender is allowed to send a player to spawn
            Player t = Utils.getPlayerByUsername(args[0]);
            if (t == null) {
                sender.sendMessage(ChatColor.RED + "The target (" + args[0] + ") is not online!");
                return true;
            }
            if (sendPlayerToSpawn(t)) {
                // If they were successfully sent to spawn
                t.sendMessage(ChatColor.YELLOW + "You've been teleported to spawn!");
                sender.sendMessage(ChatColor.YELLOW + "You successfully teleported " + t.getName() + " to spawn!");
            } else {
                // There was an error teleporting
                sender.sendMessage(ChatColor.RED + "There was an error teleporting " + t.getName() + " to spawn. Contact the owner of this Dark Realm if you believe this is an error.");
            }
            return true;
        } else {
            sender.sendMessage(ChatColor.RED + "Invalid usage. Try /spawn to teleport yourself to spawn or /spawn [player] to send another player to spawn.");
        }
        return true;
    }
}