package nicholasmy.darkrealms;

import org.bukkit.entity.Player;
import sun.plugin2.main.server.Plugin;

public class PermissionUtils {

    private static DarkRealms darkRealms = null;

    public PermissionUtils(DarkRealms darkRealms) {
        PermissionUtils.darkRealms = darkRealms;
    }

    /**
     * Checks if a player has permission to perform an action by comparing the minimum permission to perform that action with their status, such as being op or not
     * @param p The player to check permissions against
     * @param permission The permission string from the config file to check if the player is allowed to do, such as "fly"
     * @return True if the player is allowed to perform the action.
     */
    public static boolean playerHasPermission(Player p, String permission) {
        String  requiredLevel = darkRealms.getConfig().getString("permissions." + permission); // Such as "ALL" or "OPS" or "NOBODY"
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
}
