package fr.nathanael2611.roleplaychat;

import fr.nathanael2611.roleplaychat.commands.RPName;
import fr.nathanael2611.roleplaychat.commands.RolePlayChatCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import sun.awt.Win32GraphicsConfig;

import java.io.File;
import java.io.IOException;

public class RolePlayChat extends JavaPlugin {


    public static RolePlayChat instance;

    public FileConfiguration rpnames = new YamlConfiguration().loadConfiguration(getFile("config"));


    public void onEnable(){
        instance = this;

        new RPChatListener(this);

        saveDefaultConfig();
        getCommand("roleplaychat").setExecutor(new RolePlayChatCommand());
        getCommand("rpname").setExecutor(new RPName());
        System.out.println(getConfig().getShortList("miscs.prefix"));




        System.out.println("RolePlayChat >> ON");

    }





    public void onDisable(){

        System.out.println("RolePlayChat >> OFF");

    }

    private void createFile(String filename){
        if(!getDataFolder().exists()){
            getDataFolder().mkdir();
        }

        File file = new File(getDataFolder(), filename+".yml");

        if(!file.exists()){
            try{
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public File getFile(String filename){
        return new File(getDataFolder(), filename+".yml");
    }
}
