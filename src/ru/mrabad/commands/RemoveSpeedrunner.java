package ru.mrabad.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.mrabad.main.HxS;

public class RemoveSpeedrunner implements CommandExecutor
{
    private final HxS plugin;

    public RemoveSpeedrunner(HxS plugin)
    {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (args.length != 0)
        {
            Player p = Bukkit.getPlayer(args[0]);
            if (p != null && plugin.speedrunnersList.contains(p))
            {
                plugin.speedrunnersList.remove(p);
                plugin.speedrunnersListRaw.remove(p.getUniqueId().toString());
                sender.sendMessage(ChatColor.RED + p.getDisplayName() + " is no longer speedrunner!");
            } else
            {
                sender.sendMessage(ChatColor.RED + "This player isn't speedrunner!");
            }
            return true;
        } else
        {
            return false;
        }
    }
}
