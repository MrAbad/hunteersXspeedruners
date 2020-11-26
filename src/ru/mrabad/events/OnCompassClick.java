package ru.mrabad.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.CompassMeta;
import ru.mrabad.main.HxS;

import java.util.HashMap;

public class OnCompassClick implements Listener
{
    private final HxS plugin;

    public OnCompassClick(HxS plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCompassClick(PlayerInteractEvent e)
    {
        Player p = e.getPlayer();
        Action a = e.getAction();
        if (p.getInventory().getItemInMainHand().getType() == Material.COMPASS && (a == Action.RIGHT_CLICK_BLOCK || a == Action.RIGHT_CLICK_AIR))
        {
            e.setCancelled(true);
            p.sendMessage("Lox!");
            Player nearest = plugin.speedrunnersList.get(0);
            int nearestDiff = (int) (Math.abs(p.getLocation().getX() - nearest.getLocation().getX()) +
                                     Math.abs(p.getLocation().getY() - nearest.getLocation().getY()) +
                                     Math.abs(p.getLocation().getZ() - nearest.getLocation().getZ()));
            for (Player speedrunner : plugin.speedrunnersList)
            {
                if (p.getWorld().getName().equals(speedrunner.getWorld().getName()))
                {
                    if ((int) (Math.abs(p.getLocation().getX() - speedrunner.getLocation().getX()) +
                               Math.abs(p.getLocation().getY() - speedrunner.getLocation().getY()) +
                               Math.abs(p.getLocation().getZ() - speedrunner.getLocation().getZ()))
                    < nearestDiff)
                    {
                        nearestDiff = (int) (Math.abs(p.getLocation().getX() - speedrunner.getLocation().getX()) +
                                             Math.abs(p.getLocation().getY() - speedrunner.getLocation().getY()) +
                                             Math.abs(p.getLocation().getZ() - speedrunner.getLocation().getZ()));
                        nearest = speedrunner;
                        p.sendMessage("A");
                    }
                    p.sendMessage("A!");
                } else if (speedrunner.getWorld().getName().equals(Bukkit.getWorlds().get(1).getName()) && p.getWorld().getName().equals(Bukkit.getWorlds().get(0).getName()))
                {
                    if ((int) (Math.abs(p.getLocation().getX() - speedrunner.getLocation().getX() * 8) +
                               Math.abs(p.getLocation().getY() - speedrunner.getLocation().getY() * 8) +
                               Math.abs(p.getLocation().getZ() - speedrunner.getLocation().getZ() * 8))
                    < nearestDiff)
                    {
                        nearestDiff = (int) (Math.abs(p.getLocation().getX() - speedrunner.getLocation().getX() * 8) +
                                             Math.abs(p.getLocation().getY() - speedrunner.getLocation().getY() * 8) +
                                             Math.abs(p.getLocation().getZ() - speedrunner.getLocation().getZ() * 8));
                        nearest = speedrunner;
                        p.sendMessage("B");
                    }
                    p.sendMessage("B!");
                } else if (speedrunner.getWorld().getName().equals(Bukkit.getWorlds().get(0).getName()) && p.getWorld().getName().equals(Bukkit.getWorlds().get(1).getName()))
                {
                    if ((int) (Math.abs(p.getLocation().getX() - speedrunner.getLocation().getX() / 8) +
                               Math.abs(p.getLocation().getY() - speedrunner.getLocation().getY() / 8) +
                               Math.abs(p.getLocation().getZ() - speedrunner.getLocation().getZ() / 8))
                    < nearestDiff)
                    {
                        nearestDiff = (int) (Math.abs(p.getLocation().getX() - speedrunner.getLocation().getX() / 8) +
                                             Math.abs(p.getLocation().getY() - speedrunner.getLocation().getY() / 8) +
                                             Math.abs(p.getLocation().getZ() - speedrunner.getLocation().getZ() / 8));
                        nearest = speedrunner;
                        p.sendMessage("B");
                    }
                    p.sendMessage("C!");
                } else return;
            }
            CompassMeta meta = (CompassMeta) p.getInventory().getItemInMainHand().getItemMeta();
            meta.setLodestoneTracked(true);
            if (p.getWorld() == nearest.getWorld())
            {
                meta.setLodestone(nearest.getLocation());
            } else if (p.getWorld() == Bukkit.getWorlds().get(0))
            {
                meta.setLodestone(new Location(p.getWorld(), nearest.getLocation().getX() * 8, nearest.getLocation().getY() * 8, nearest.getLocation().getZ() * 8));
            } else if (p.getWorld() == Bukkit.getWorlds().get(1))
            {
                meta.setLodestone(new Location(p.getWorld(), nearest.getLocation().getX() / 8, nearest.getLocation().getY() / 8, nearest.getLocation().getZ() / 8));
            }
        }
    }
}
