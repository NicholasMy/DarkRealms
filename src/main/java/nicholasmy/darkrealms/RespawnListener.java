package nicholasmy.darkrealms;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class RespawnListener implements Listener {

    private DarkRealms darkRealms;

    public RespawnListener(DarkRealms darkRealms) {
        this.darkRealms = darkRealms;
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        boolean isAllowedToSpawnAtBed = Utils.playerHasPermission(p, "spawn_at_bed");
        boolean isAllowedToSpawnAtAnchor = Utils.playerHasPermission(p, "spawn_at_anchor");
        if (e.isAnchorSpawn() && isAllowedToSpawnAtAnchor) {
            return;
            // Don't interfere with this anchor respawn
        }
        if (e.isBedSpawn() && isAllowedToSpawnAtBed) {
            return;
            // Don't interfere with this bed respawn
        }
        // Not a bed spawn or anchor spawn, so send the player to the Dark Realm spawn
        e.setRespawnLocation(Utils.getSpawnLocation());
    }

}
