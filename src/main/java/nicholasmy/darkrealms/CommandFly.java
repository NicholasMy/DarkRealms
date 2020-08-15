package nicholasmy.darkrealms;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandFly implements CommandExecutor {

    private DarkRealms darkRealms;

    public CommandFly(DarkRealms darkRealms) {
        this.darkRealms = darkRealms;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("You need to specify a player when executing from the console or a command block!");
                return true;
            }

            Player p = (Player) sender;
            if (PermissionUtils.playerHasPermission(p, "fly")) {
                p.setAllowFlight(!p.getAllowFlight());
                String state = p.getAllowFlight() ? "enabled" : "disabled";
                ChatColor color = p.getAllowFlight() ? ChatColor.GREEN : ChatColor.RED;
                p.sendMessage(ChatColor.AQUA + "Flight mode " + color + state + ChatColor.AQUA + ".");
            } else {
                // Player doesn't have permission to fly
                p.sendMessage(ChatColor.RED + "You don't have permission to toggle your flight. Contact the owner of this Dark Realm if you believe this is an error.");
            }
        } else if (args.length == 1) {
            Player p;
            if (!(sender instanceof Player)) {
                p = null; // This was sent from the console or a command block, so there is no player sending the command.
            } else {
                p = (Player) sender;
            }
            if (PermissionUtils.playerHasPermission(p, "fly_others")) {
                // TODO get target
                //p.setAllowFlight(!p.getAllowFlight());
                //String state = p.getAllowFlight() ? "enabled" : "disabled";
                //ChatColor color = p.getAllowFlight() ? ChatColor.GREEN : ChatColor.RED;
                //sender.sendMessage(ChatColor.AQUA + "Flight mode " + color + state + ChatColor.AQUA + " for " + ChatColor.YELLOW + "TARGET_NAME_HERE" + ChatColor.AQUA + ".");
            } else {
                // Player doesn't have permission to fly
                sender.sendMessage(ChatColor.RED + "You don't have permission to toggle flight for other players. Contact the owner of this Dark Realm if you believe this is an error.");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "Invalid usage. Try /fly to toggle your own flight or /fly [player] to toggle another player's flight.");
        }


        return true;
    }
}
