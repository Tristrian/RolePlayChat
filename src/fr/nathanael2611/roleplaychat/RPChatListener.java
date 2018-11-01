package fr.nathanael2611.roleplaychat;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;

public class RPChatListener implements Listener {

    public RPChatListener(RolePlayChat plugin){
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }


    //Méthode de vérification de la distance entre joueurs (utile pour le RPCHat)
    boolean checkDistance(Location loc1, Location loc2, int maxDist){
        if(loc1.distance(loc2) <= maxDist){
            return true;
        }else{
            return false;
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        e.setCancelled(true);

        FileConfiguration getConfig = RolePlayChat.instance.getConfig();

        List<Player> playerHearList = new ArrayList<>();

        //On défini les distances
        int shoutdist = getConfig.getInt("distance.shout");
        int whispdist = getConfig.getInt("distance.whisp");
        int speakdist = getConfig.getInt("distance.speak");
        int actiondist = getConfig.getInt("distance.action");
        //On défini les prefix
        String hrpprefix = getConfig.getString("prefix.hrp");
        String shoutprefix = getConfig.getString("prefix.shout");
        String whispprefix = getConfig.getString("prefix.whisp");
        String actionprefix = getConfig.getString("prefix.action");

        Player p = e.getPlayer();
        Location playerLoc = p.getLocation();

        String message = e.getMessage();
        String msg = message.substring(1, message.length());

        String firstchar = message.substring(0, 1);

        String name = p.getDisplayName();

        if(firstchar.equals(hrpprefix)) {
            Bukkit.broadcastMessage("§7"+name+"§r [HRP]: §7"+msg);
        }else if(firstchar.equals(shoutprefix)) {
            for (Player pl : e.getRecipients()) {
                if(checkDistance(pl.getLocation(), playerLoc, shoutdist) == true){
                    playerHearList.add(pl);
                }
            }
            for (Player pll : playerHearList) {
                pll.sendMessage("§7"+name+" §ccrie: "+msg);
            }
        }else if(firstchar.equals(whispprefix)) {
            for (Player pl : e.getRecipients()) {
                if(checkDistance(pl.getLocation(), playerLoc, whispdist) == true){
                    playerHearList.add(pl);
                }
            }
            for (Player pll : playerHearList) {
                pll.sendMessage("§7"+name+" §2chuchotte: "+msg);
            }
        }else if(firstchar.equals(actionprefix)) {
            for (Player pl : e.getRecipients()) {
                if(checkDistance(pl.getLocation(), playerLoc, actiondist) == true){
                    playerHearList.add(pl);
                }
            }
            for (Player pll : playerHearList) {
                pll.sendMessage("§a§o"+name+" "+msg);
            }
        }else {
            for (Player pl : e.getRecipients()) {
                if(checkDistance(pl.getLocation(), playerLoc, speakdist) == true){
                    playerHearList.add(pl);
                }
            }
            for (Player pll : playerHearList) {
                pll.sendMessage("§7"+name+" §fdit: "+message);
            }
        }

    }

}
