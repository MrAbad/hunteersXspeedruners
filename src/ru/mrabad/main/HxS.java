package ru.mrabad.main;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import ru.mrabad.commands.AddSpeedrunner;

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
        File speedrunnersFile = new File(getDataFolder() + File.separator + "speedrunnersFile.yml");
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
        getCommand("addspeedrunner").setExecutor(new AddSpeedrunner(this));
    }

    @Override
    public void onDisable()
    {
        File speedrunnersFile = new File(getDataFolder() + File.separator + "speedrunnersFile.yml");
        FileConfiguration speedrunnersYaml = YamlConfiguration.loadConfiguration(speedrunnersFile);
        speedrunnersYaml.set("speedrunners", speedrunnersListRaw);
        try {
            speedrunnersYaml.save(speedrunnersFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
