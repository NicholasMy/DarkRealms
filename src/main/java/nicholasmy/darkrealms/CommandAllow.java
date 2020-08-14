package nicholasmy.darkrealms;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class CommandAllow implements CommandExecutor {

    private DarkRealms darkRealms;

    public CommandAllow(DarkRealms darkRealms) {
        this.darkRealms = darkRealms;
    }

    private HashMap<String, String> getPermissionsFromConfig() {

        HashMap<String, Object> rawPermissions = (HashMap<String, Object>) darkRealms.getConfig().getConfigurationSection("permissions").getKeys(false); // raw permissions hashmap from config file
        HashMap<String, String> permissions = new HashMap<>(); // Final permissions hashmap to return

        for (Map.Entry<String, Object> e : rawPermissions.entrySet()) {
            String k = e.getKey();
            String v = darkRealms.getConfig().getString((String) e.getValue());
            permissions.put(k, v);
        }

        return permissions;

    }

    /**
     * @param input a user input for the permission level. This might not be 100% accurate, so this method will interpret what they mean
     * @return the correct permission level name
     */
    private String interpretPermissionLevel(String input) {
        if (input.toLowerCase().startsWith("no")) {
            return "NOBODY";
        } else if (input.toLowerCase().startsWith("op")) {
            return "OPS";
        } else if (input.toLowerCase().startsWith("al") || input.toLowerCase().startsWith("every")) {
            return "ALL";
        } else {
            return null;
        }
    }

    /**
     * @param permissionLevel "NOBODY" "OPS" "ALL"
     * @return chat color to display the permission level in
     */
    private ChatColor getChatColorForPermissionLevel(String permissionLevel) {
        switch (permissionLevel) {
            case "NOBODY":
                return ChatColor.RED;
            case "OPS":
                return ChatColor.GOLD;
            case "ALL":
                return ChatColor.GREEN;
            default:
                return null;
        }
    }

    /**
     * Sends the current permissions table to the specified sender
     *
     * @param sender the CommandSender to send the permissions table to
     */
    private void sendCurrentPermissions(CommandSender sender) {

        for (Map.Entry<String, String> e : getPermissionsFromConfig().entrySet()) {
            String k = e.getKey();
            String v = e.getValue();
            ChatColor color = getChatColorForPermissionLevel(v);
            sender.sendMessage(ChatColor.YELLOW + k + ": " + color + v);
        }

//        sender.sendMessage(ChatColor.YELLOW + "Fly: " + ChatColor.RED + "NOBODY");
//        sender.sendMessage(ChatColor.YELLOW + "Set spawn: " + ChatColor.GOLD + "OP");
//        sender.sendMessage(ChatColor.YELLOW + "Go to spawn: " + ChatColor.GREEN + "ALL");
        sender.sendMessage(ChatColor.RED + "To change permissions, use /allow <permission> <all|ops|nobody>");
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
            String permission = args[0].toLowerCase(); // Such as "fly"
            if (!getPermissionsFromConfig().containsKey(permission)){
                sender.sendMessage(ChatColor.RED + "Invalid permission name. An example of a valid permission name is set_spawn.");
                return true;
            }
            String level = interpretPermissionLevel(args[1]); // such as "OPS"
            if (level == null) {
                sender.sendMessage(ChatColor.RED + "Invalid permission level. Use all, ops, or nobody.");
                return true;
            }

            // TODO actually change the permissions in the config file
            // TODO check if what the user entered is valid for both entries
            sender.sendMessage(ChatColor.GREEN + "You successfully set the " + permission + " to " + level + "!");
            sender.sendMessage(ChatColor.GREEN + "Here are the updated permissions:");
        }
        sendCurrentPermissions(sender);
        return true;
    }
}
