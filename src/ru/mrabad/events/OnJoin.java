package ru.mrabad.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import ru.mrabad.main.HxS;

public class OnJoin implements Listener
{
    private final HxS plugin;

    public OnJoin(HxS plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        Player p = e.getPlayer();
        if (plugin.speedrunnersListRaw.contains(p.getUniqueId().toString()))
        {
            if (!plugin.speedrunnersList.contains(p)) plugin.speedrunnersList.add(p);
            plugin.speedrunnersListRaw.remove(p.getUniqueId().toString());
        }
    }
}
