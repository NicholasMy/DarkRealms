package nicholasmy.darkrealms;

import org.bukkit.ChatColor;
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
        sender.sendMessage("");
        sender.sendMessage(ChatColor.AQUA + "This Dark Realm is running DarkRealms version " + ChatColor.GREEN + darkRealms.getDescription().getVersion() + ChatColor.AQUA + ".");
        sender.sendMessage(ChatColor.WHITE + "DarkRealms is an open source plugin, developed by Nicholas Myers, intended to enhance gameplay on Dark Realms servers.");
        sender.sendMessage(ChatColor.WHITE + "Developers can view and contribute to the source code here:" + ChatColor.LIGHT_PURPLE + " https://github.com/NicholasMy/DarkRealms");
        sender.sendMessage(ChatColor.YELLOW + "You can get your own Dark Realm by contacting Nicholas!");
        sender.sendMessage(ChatColor.AQUA + "Discord: Nicholas#4088, Email: contact@NicholasMy.com");
        sender.sendMessage("");
        return true;
    }
}
