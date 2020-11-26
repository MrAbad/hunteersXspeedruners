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
import org.bukkit.util.Vector;
import ru.mrabad.main.HxS;

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
        if (plugin.speedrunnersList.contains(p)) return;
        Action a = e.getAction();
        if (p.getInventory().getItemInMainHand().getType() == Material.COMPASS && (a == Action.RIGHT_CLICK_BLOCK || a == Action.RIGHT_CLICK_AIR))
        {
            e.setCancelled(true);
            if (plugin.speedrunnersList.isEmpty()) return;
            Player nearest = plugin.speedrunnersList.get(0);
            int nearestDiff = (int) p.getLocation().distanceSquared(nearest.getLocation().toVector().toLocation(p.getWorld()));
            for (Player speedrunner : plugin.speedrunnersList)
            {
                if (p.getWorld().getName().equals(speedrunner.getWorld().getName()))
                {
                    if ((int) p.getLocation().distanceSquared(speedrunner.getLocation()) < nearestDiff)
                    {
                        nearestDiff = (int) p.getLocation().distanceSquared(speedrunner.getLocation());
                        nearest = speedrunner;
                    }
                } else if (speedrunner.getWorld().getName().equals(Bukkit.getWorlds().get(1).getName()) && p.getWorld().getName().equals(Bukkit.getWorlds().get(0).getName()))
                {
                    if ((int) p.getLocation().distanceSquared(speedrunner.getLocation().multiply(8).toVector().toLocation(p.getWorld())) < nearestDiff)
                    {
                        nearestDiff = (int) speedrunner.getLocation().toVector().toLocation(p.getWorld()).distanceSquared(p.getLocation().multiply(8));
                        nearest = speedrunner;
                    }
                } else if (speedrunner.getWorld().getName().equals(Bukkit.getWorlds().get(0).getName()) && p.getWorld().getName().equals(Bukkit.getWorlds().get(1).getName()))
                {
                    if ((int) p.getLocation().distanceSquared(speedrunner.getLocation().multiply(1./8).toVector().toLocation(p.getWorld())) < nearestDiff)
                    {
                        nearestDiff = (int) p.getLocation().distanceSquared(speedrunner.getLocation().multiply(1./8).toVector().toLocation(p.getWorld()));
                        nearest = speedrunner;
                    }
                }
            }
            CompassMeta meta = (CompassMeta) p.getInventory().getItemInMainHand().getItemMeta();
            meta.setLodestoneTracked(false);
            Location nearestLoc = nearest.getLocation();
            if (p.getWorld() == nearestLoc.getWorld())
            {
                meta.setLodestone(nearestLoc);
            } else if (p.getWorld() == Bukkit.getWorlds().get(0))
            {
                nearestLoc.setWorld(p.getWorld());
                meta.setLodestone(nearestLoc.multiply(8));
            } else if (p.getWorld() == Bukkit.getWorlds().get(1))
            {
                nearestLoc.setWorld(p.getWorld());
                meta.setLodestone(nearestLoc.multiply(1./8));
            }
            p.getInventory().getItemInMainHand().setItemMeta(meta);
        }
    }
}
