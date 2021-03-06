package ru.mrabad.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.mrabad.main.HxS;

public class GetSpeedrunners implements CommandExecutor
{
    private final HxS plugin;

    public GetSpeedrunners(HxS plugin)
    {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (!plugin.speedrunnersList.isEmpty())
        {
            for (Player speedrunner : plugin.speedrunnersList)
            {
                sender.sendMessage(speedrunner.getDisplayName());
            }
        } else sender.sendMessage(ChatColor.RED + "None of the current players are speedrunners!");
        return true;
    }
}
