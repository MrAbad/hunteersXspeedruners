package ru.mrabad.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;
import ru.mrabad.main.HxS;

public class OnPortalTp implements Listener
{
    private final HxS plugin;

    public OnPortalTp(HxS plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPortalTp(PlayerPortalEvent e)
    {
        if (plugin.speedrunnersList.isEmpty()) return;
        Player p = e.getPlayer();
        for (int itemCounter = 0; itemCounter < p.getInventory().getContents().length; itemCounter++)
        {
            ItemStack item = p.getInventory().getItem(itemCounter);
            if (item != null && item.getType() == Material.COMPASS)
            {
                CompassMeta meta = (CompassMeta) item.getItemMeta();
                if (meta != null && meta.hasLodestone())
                    meta.setLodestoneTracked(false);
                    if (e.getTo().getWorld() == meta.getLodestone().getWorld())
                    {
                        meta.setLodestone(meta.getLodestone());
                    } else if (e.getTo().getWorld() == Bukkit.getWorlds().get(0))
                    {
                        meta.getLodestone().setWorld(e.getTo().getWorld());
                        meta.setLodestone(meta.getLodestone().multiply(8));
                    } else if (e.getTo().getWorld() == Bukkit.getWorlds().get(1))
                    {
                        meta.getLodestone().setWorld(e.getTo().getWorld());
                        meta.setLodestone(meta.getLodestone().multiply(1. / 8));
                    }
                    item.setItemMeta(meta);
                    p.getInventory().setItem(itemCounter, item);
                }
            }
        }
    }

