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
        int x90 = e.getMessage().length();
        String msg = e.getMessage().substring(1, x90);

        String firstchar = message.substring(0, 1);

        String name = RolePlayChat.instance.rpnames.getString("namerp."+p.getName()+".name");
        String displayname = p.getDisplayName();

        if(firstchar.equals(hrpprefix)) {

            String x1;
            x1 = getConfig.getString("format.hrp");
            if(x1.contains("{player}")){

                x1 = x1.replace("{player}", displayname);
            }else{
                x1 = x1;
            }
            if(x1.contains("{rpname}")){
                x1 = x1.replace("{rpname}", name);

            }else{
                x1 = x1;
            }
            if(x1.contains("{message}")){
                x1 = x1.replace("{message}", msg);

            }else{
                x1 = x1;
            }
            Bukkit.broadcastMessage(x1);
        }else if(firstchar.equals(shoutprefix)) {
            for (Player pl : e.getRecipients()) {
                if(checkDistance(pl.getLocation(), playerLoc, shoutdist) == true){
                    playerHearList.add(pl);
                }
            }
            for (Player pll : playerHearList) {
                String x1 = getConfig.getString("format.shout");
                if(x1.contains("{player}")){

                    x1 = x1.replace("{player}", displayname);
                }else{
                    x1 = x1;
                }
                if(x1.contains("{rpname}")){
                    x1 = x1.replace("{rpname}", name);

                }else{
                    x1 = x1;
                }
                if(x1.contains("{message}")){
                    x1 = x1.replace("{message}", msg);

                }else{
                    x1 = x1;
                }
                pll.sendMessage(x1);
            }
        }else if(firstchar.equals(whispprefix)) {
            for (Player pl : e.getRecipients()) {
                if(checkDistance(pl.getLocation(), playerLoc, whispdist) == true){
                    playerHearList.add(pl);
                }
            }
            for (Player pll : playerHearList) {
                String x1 = getConfig.getString("format.whisp");
                if(x1.contains("{player}")){

                    x1 = x1.replace("{player}", displayname);
                }else{
                    x1 = x1;
                }
                if(x1.contains("{rpname}")){
                    x1 = x1.replace("{rpname}", name);

                }else{
                    x1 = x1;
                }
                if(x1.contains("{message}")){
                    x1 = x1.replace("{message}", msg);

                }else{
                    x1 = x1;
                }
                pll.sendMessage(x1);
            }
        }else if(firstchar.equals(actionprefix)) {
            for (Player pl : e.getRecipients()) {
                if(checkDistance(pl.getLocation(), playerLoc, actiondist) == true){
                    playerHearList.add(pl);
                }
            }
            for (Player pll : playerHearList) {
                String x1 = getConfig.getString("format.action");
                if(x1.contains("{player}")){

                    x1 = x1.replace("{player}", displayname);
                }else{
                    x1 = x1;
                }
                if(x1.contains("{rpname}")){
                    x1 = x1.replace("{rpname}", name);

                }else{
                    x1 = x1;
                }
                if(x1.contains("{message}")){
                    x1 = x1.replace("{message}", msg);

                }else{
                    x1 = x1;
                }
                pll.sendMessage(x1);
            }
        }else {
            for (Player pl : e.getRecipients()) {
                if(checkDistance(pl.getLocation(), playerLoc, speakdist) == true){
                    playerHearList.add(pl);
                }
            }
            for (Player pll : playerHearList) {

                String x1;

                x1 = getConfig.getString("format.speak");;

                if(x1.contains("{player}")){

                    x1 = x1.replace("{player}", displayname);
                }else{
                    x1 = x1;
                }
                if(x1.contains("{rpname}")){
                    x1 = x1.replace("{rpname}", name);

                }else{
                    x1 = x1;
                }
                if(x1.contains("{message}")){
                    x1 = x1.replace("{message}", message);

                }else{
                    x1 = x1;
                }

                pll.sendMessage(x1);
            }
        }

    }

}
