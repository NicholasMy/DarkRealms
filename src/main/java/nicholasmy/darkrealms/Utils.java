package nicholasmy.darkrealms;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Utils {

    private static DarkRealms darkRealms = null;

    public Utils(DarkRealms darkRealms) {
        Utils.darkRealms = darkRealms;
    }

    /**
     * Checks if a player has permission to perform an action by comparing the minimum permission to perform that action with their status, such as being op or not
     *
     * @param p          The player to check permissions against
     * @param permission The permission string from the config file to check if the player is allowed to do, such as "fly"
     * @return True if the player is allowed to perform the action.
     */
    public static boolean playerHasPermission(Player p, String permission) {
        String requiredLevel = darkRealms.getConfig().getString("permissions." + permission); // Such as "ALL" or "OPS" or "NOBODY"
        if (p == null)
            return true; // If there is no player, it was sent from the console or a command block, so it's allowed.
        assert requiredLevel != null;
        switch (requiredLevel) {
            case "ALL":
                return true;
            // If all players are allowed to do something, this player certainly can.
            case "NOBODY":
                return false;
            // If nobody is allowed to do this, obviously this player can't either.
            case "OPS":
                return p.isOp();
            // If the player is OP, they can do this, otherwise not.
            default:
                return false;
            // There isn't a valid level assigned to this permission, so nobody is allowed to do it.
        }
    }

    /**
     * Gets a player instance from a sender instance. If the sender is not a player, returns null
     *
     * @param sender The command sender that needs to be converted to a player
     * @return Player instance or null
     */
    public static Player getPlayerSenderOrNull(CommandSender sender) {
        if (!(sender instanceof Player)) {
            return null; // This was sent from the console or a command block, so there is no player sending the command.
        } else {
            return (Player) sender;
        }
    }

    /**
     * Get an online player by their username
     *
     * @param username String of their username
     * @return Player instance (or null if not found)
     */
    public static Player getPlayerByUsername(String username) {
        return Bukkit.getServer().getPlayer(username);
    }

    public static Location getSpawnLocation() {
        // Get spawn info from config
        double x = darkRealms.getConfig().getDouble("spawn.x");
        double y = darkRealms.getConfig().getDouble("spawn.y");
        double z = darkRealms.getConfig().getDouble("spawn.z");
        float pitch = (float) darkRealms.getConfig().getDouble("spawn.pitch");
        float yaw = (float) darkRealms.getConfig().getDouble("spawn.yaw");
        String worldName = darkRealms.getConfig().getString("spawn.world");
        assert worldName != null;
        World world = Bukkit.getWorld(worldName);
        return new Location(world, x, y, z, yaw, pitch);
    }

    public static Double tryToGetDoubleFromInput(String s) {
        try {
            return Double.parseDouble(s);
        } catch (Exception e) {
            return null;
        }
    }

}
