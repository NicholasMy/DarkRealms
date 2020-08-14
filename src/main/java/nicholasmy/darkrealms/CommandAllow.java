package nicholasmy.darkrealms;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class CommandAllow implements CommandExecutor {

    public CommandAllow(DarkRealms darkRealms) {
    }

    private void sendCurrentPermissions(CommandSender sender) {
        // TODO list current permissions level, for now it's just made up values
        sender.sendMessage(ChatColor.YELLOW + "Fly: " + ChatColor.RED + "NOBODY");
        sender.sendMessage(ChatColor.YELLOW + "Set spawn: " + ChatColor.GOLD + "OP");
        sender.sendMessage(ChatColor.YELLOW + "Go to spawn: " + ChatColor.GREEN + "ALL");
        sender.sendMessage(ChatColor.RED + "To change permissions, use /allow <permission> <all|op|nobody>");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        boolean allowedToRunCommand = false;
        if (sender instanceof ConsoleCommandSender) {
            // If the console sends the command, allow it
            allowedToRunCommand = true;
        } else if (sender instanceof Player) {
            // If a player who is op sends the command, allow it
            Player p = (Player) sender;
            if (p.isOp()) {
                allowedToRunCommand = true;
            }
        }
        if (!allowedToRunCommand) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to change permissions. Please talk to the owner of this Dark Realm if you believe this is an error.");
            return true;
        }

        // At this point, the sender is allowed to change permissions

        if (args.length != 2) {
            sender.sendMessage(ChatColor.GREEN + "Here are the current permissions:");
        } else {
            // args length is 2
            String permission = args[0]; // Such as "fly"
            String level = args[1]; // such as "ops"
            // TODO actually change the permissions in the config file
            // TODO check if what the user entered is valid for both entries
            sender.sendMessage(ChatColor.GREEN + "You successfully set the " + permission + " to " + level + "!");
            sender.sendMessage(ChatColor.GREEN + "Here are the updated permissions:");
        }
        sendCurrentPermissions(sender);
        return true;
    }
}
