package ru.mrabad.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.mrabad.main.HxS;

public class AddSpeedrunner implements CommandExecutor
{
    private final HxS plugin;

    public AddSpeedrunner(HxS plugin)
    {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (args.length != 0)
        {
            Player p = Bukkit.getPlayer(args[0]);
            if (p != null)
            {
                if (!plugin.speedrunnersList.contains(p))
                {
                    plugin.speedrunnersList.add(p);
                    sender.sendMessage(ChatColor.GREEN + p.getDisplayName() + " is now speedrunner!");
                } else
                {
                    sender.sendMessage(ChatColor.RED + "This player is already speedrunner!");
                }
            } else
            {
                sender.sendMessage(ChatColor.RED + "Wrong name!");
            }
            return true;
        } else
        {
            return false;
        }
    }
}
