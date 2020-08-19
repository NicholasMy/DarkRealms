package nicholasmy.darkrealms;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSpeed implements CommandExecutor {

    private DarkRealms darkRealms;

    public CommandSpeed(DarkRealms darkRealms) {
        this.darkRealms = darkRealms;
    }

    private void setPlayerSpeed(Player p, double amount) {
        if (amount != 0) {
            amount /= 10.0; // Handle the scale from -10 to 10 instead of the API's -1 to 1
        }
        p.setFlySpeed((float) amount);
        p.setWalkSpeed((float) amount);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = Utils.getPlayerSenderOrNull(sender);
        boolean hasPermissionSelf = Utils.playerHasPermission(p, "speed");
        boolean hasPermissionOthers = Utils.playerHasPermission(p, "speed_others");

        if (!hasPermissionSelf && !hasPermissionOthers) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use the speed command. Contact the owner of this Dark Realm if you believe this is an error.");
            return true;
        }

        // At this point, they're allowed to either set their own speed or another player's speed

        if (args.length == 0 || args.length >= 3) {
            sender.sendMessage(ChatColor.RED + "Incorrect usage. Try /speed <amount> [player]. Amount can be from -10 to 10. For regular speed, choose 1. Negative speeds are reversed.");
            return true;
        }

        // At this point, there is a correct number of arguments given

        if (args.length == 1) {
            // Set self speed

            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "This command requires two arguments if used from a command block or the console. Try /speed <amount> [player].");
                return true;
            }

            // At this point, the sender is a player

            if (hasPermissionSelf) {
                Double amount = Utils.tryToGetDoubleFromInput(args[0]);
                if (amount == null || amount > 10 || amount < 0) {
                    p.sendMessage(ChatColor.RED + "Invalid speed (" + args[0] + "). Amount must be a number from 0 to 10. Regular speed is 1.");
                    return true;
                }

                setPlayerSpeed(p, amount);
                p.sendMessage(ChatColor.YELLOW + "Your movement speed has been set to " + amount + ".");

            } else {
                sender.sendMessage(ChatColor.RED + "You don't have permission to change your own speed. Contact the owner of this Dark Realm if you believe this is an error.");
                return true;
            }
        }

        if (args.length == 2) {
            // Set another player's speed

            if (hasPermissionOthers) {

                Double amount = Utils.tryToGetDoubleFromInput(args[0]);
                if (amount == null || amount > 10 || amount < 0) {
                    sender.sendMessage(ChatColor.RED + "Invalid speed (" + args[0] + "). Amount must be a number from 0 to 10. Regular speed is 1.");
                    return true;
                }

                Player t = Utils.getPlayerByUsername(args[1]);
                if (t == null) {
                    sender.sendMessage(ChatColor.RED + "Invalid player (" + args[1] + "). The target player must be online.");
                    return true;
                }

                setPlayerSpeed(t, amount);
                sender.sendMessage(ChatColor.YELLOW + "You set " + t.getName() + "'s movement speed to " + amount + ".");
                t.sendMessage(ChatColor.YELLOW + "Your movement speed has been set to " + amount + ".");

            } else {
                sender.sendMessage(ChatColor.RED + "You don't have permission to change another player's speed. Contact the owner of this Dark Realm if you believe this is an error.");
                return true;
            }
        }
        return true;
    }
}
