package ru.mrabad.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import ru.mrabad.main.HxS;

public class OnExit implements Listener
{
    private final HxS plugin;

    public OnExit(HxS plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void onExit(PlayerQuitEvent e)
    {
        Player p = e.getPlayer();
        if (plugin.speedrunnersList.contains(p))
        {
            plugin.speedrunnersListRaw.add(p.getUniqueId().toString());
        }
    }
}
