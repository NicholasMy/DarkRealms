package nicholasmy.darkrealms;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandDarkRealms implements CommandExecutor {

    private DarkRealms darkRealms;

    public CommandDarkRealms(DarkRealms darkRealms) {
        this.darkRealms = darkRealms;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        return false;
    }
}
