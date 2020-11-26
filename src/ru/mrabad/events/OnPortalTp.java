package ru.mrabad.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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
        Player p = e.getPlayer();
        if (plugin.speedrunnersList.isEmpty()) return;
        if (e.getTo().getWorld().getName().equals("world_the_end")) return;
        for (int itemCounter = 0; itemCounter < p.getInventory().getContents().length; itemCounter++)
        {
            ItemStack item = p.getInventory().getItem(itemCounter);
            if (item != null && item.getType() == Material.COMPASS)
            {
                CompassMeta meta = (CompassMeta) item.getItemMeta();
                if (meta != null && meta.hasLodestone())
                    meta.setLodestoneTracked(false);
                    Location mls;
                    if (e.getTo().getWorld() == meta.getLodestone().getWorld())
                    {
                        meta.setLodestone(meta.getLodestone());
                    } else if (e.getTo().getWorld().getName().equals("world"))
                    {
                        mls = meta.getLodestone();
                        mls.setWorld(Bukkit.getWorld("world"));
                        meta.setLodestone(mls.multiply(8));
                    } else if (e.getTo().getWorld().getName().equals("world_nether"))
                    {
                        mls = meta.getLodestone();
                        mls.setWorld(Bukkit.getWorld("world_nether"));
                        meta.setLodestone(mls.multiply(1./8));
                    }
                    item.setItemMeta(meta);
                    p.getInventory().setItem(itemCounter, item);
                }
            }
        }
    }

