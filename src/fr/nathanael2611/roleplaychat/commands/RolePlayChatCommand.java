package fr.nathanael2611.roleplaychat.commands;

import fr.nathanael2611.roleplaychat.RolePlayChat;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class RolePlayChatCommand implements CommandExecutor {



    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        FileConfiguration getConfig = RolePlayChat.instance.getConfig();

        //On défini les prefix
        String prefix = getConfig.getString("miscs.prefix");
        String hrpprefix = getConfig.getString("prefix.hrp");
        String shoutprefix = getConfig.getString("prefix.shout");
        String whispprefix = getConfig.getString("prefix.whisp");
        String actionprefix = getConfig.getString("prefix.action");

        Player p = Bukkit.getServer().getPlayer(sender.getName());

        if(args.length<1){
            p.sendMessage(getConfig.getString("miscs.prefix")+"Utilisation correcte : /roleplaychat <admin/infos> ");
        }else{
            if(args.length>=1){
                if(args[0].equals("admin")){
                    if(p.hasPermission("roleplaychat.admin")){
                        if (args.length== 1){
                            p.sendMessage("§cWIP");
                        }
                    }
                }
                if(args[0].equals("infos")){

                    p.sendMessage("§6===["+prefix+"§6]===");
                    p.sendMessage("§6Prefix pour crier : §c"+shoutprefix);
                    p.sendMessage("§6Prefix pour chuchotter : §2"+whispprefix);
                    p.sendMessage("§6Prefix pour faire une action : §a"+actionprefix);
                    p.sendMessage("§6Prefix pour parler en HRP : §7"+hrpprefix);

                }
            }
        }

        return false;
    }
}
