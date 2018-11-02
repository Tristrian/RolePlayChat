package fr.nathanael2611.roleplaychat.commands;

import fr.nathanael2611.roleplaychat.RolePlayChat;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;

public class RPName implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        FileConfiguration getConfig = RolePlayChat.instance.getConfig();

        Player p = Bukkit.getServer().getPlayer(sender.getName());

        if(args.length>=1){

            StringBuilder sb = new StringBuilder();
            int i;
            for (i = 0; i < args.length; i++) {
                sb.append(" ");
                sb.append(args[i]);


            }



            RolePlayChat.instance.rpnames.set("namerp."+p.getName()+".name", sb.toString());

            p.sendMessage(getConfig.getString("miscs.prefix")+"Votre nom rp a été set à "+sb.toString());

            try {
                RolePlayChat.instance.rpnames.save(RolePlayChat.instance.getFile("config"));

            } catch (IOException e) {
                e.printStackTrace();
            }

        }else{

            p.sendMessage("Veuillez saisir un argument !");

        }


        return false;
    }
}
