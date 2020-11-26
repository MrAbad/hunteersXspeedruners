package ru.mrabad.main;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import ru.mrabad.commands.AddSpeedrunner;
import ru.mrabad.commands.GetSpeedrunners;
import ru.mrabad.commands.RemoveSpeedrunner;
import ru.mrabad.events.OnCompassClick;
import ru.mrabad.events.OnExit;
import ru.mrabad.events.OnJoin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class HxS extends JavaPlugin
{
    public ArrayList<String> speedrunnersListRaw = new ArrayList<String>();
    public ArrayList<Player> speedrunnersList = new ArrayList<Player>();

    @Override
    public void onEnable()
    {
        File speedrunnersFile = new File(getDataFolder() + File.separator + "speedrunners.yml");
        if (!speedrunnersFile.getParentFile().exists())
        {
            speedrunnersFile.getParentFile().mkdirs();
        }
        if (!speedrunnersFile.exists())
        {
            try {
                speedrunnersFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileConfiguration speedrunnersYaml = YamlConfiguration.loadConfiguration(speedrunnersFile);
        speedrunnersListRaw = (ArrayList<String>) speedrunnersYaml.getStringList("speedrunners");

        Bukkit.getPluginManager().registerEvents(new OnJoin(this), this);
        Bukkit.getPluginManager().registerEvents(new OnExit(this), this);
        Bukkit.getPluginManager().registerEvents(new OnCompassClick(this), this);

        getCommand("addspeedrunner").setExecutor(new AddSpeedrunner(this));
        getCommand("removespeedrunner").setExecutor(new RemoveSpeedrunner(this));
        getCommand("getspeedrunners").setExecutor(new GetSpeedrunners(this));
    }

    @Override
    public void onDisable()
    {
        for (int speedrunnersCount = 0; speedrunnersCount < speedrunnersList.size(); speedrunnersCount++)
        {
            if (!speedrunnersListRaw.contains(speedrunnersList.get(speedrunnersCount).getUniqueId().toString())) speedrunnersListRaw.add(speedrunnersList.get(speedrunnersCount).getUniqueId().toString());
            speedrunnersList.remove(speedrunnersCount);
        }
        File speedrunnersFile = new File(getDataFolder() + File.separator + "speedrunners.yml");
        FileConfiguration speedrunnersYaml = YamlConfiguration.loadConfiguration(speedrunnersFile);
        for (String i : speedrunnersListRaw)
        {
            getLogger().info(i);
        }

        speedrunnersYaml.set("speedrunners", speedrunnersListRaw);
        try {
            speedrunnersYaml.save(speedrunnersFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
