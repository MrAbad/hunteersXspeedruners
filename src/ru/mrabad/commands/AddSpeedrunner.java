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
                plugin.speedrunnersList.add(p);
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
