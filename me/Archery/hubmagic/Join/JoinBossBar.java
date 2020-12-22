
package me.Archery.hubmagic.Join;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.Archery.hubmagic.Main;

public class JoinBossBar implements Listener
{
	
    private ArrayList<String> list;
    private int count;
    
    public JoinBossBar() {
        this.list = new ArrayList<String>();
    }
    
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (Main.plugin.config.getBoolean("Enable.JoinBossBar", true)) {
            if (Main.plugin.config.getBoolean("JoinBossBar.Animate", true)) {
                animateBossBar(e.getPlayer());
            }
            else {
                 BossBar bar = Bukkit.getServer().createBossBar(ChatColor.translateAlternateColorCodes('&', Main.plugin.config.getString("JoinBossBar.Message").replaceAll("%player%", e.getPlayer().getName())), BarColor.valueOf(Main.plugin.config.getString("JoinBossBar.BarColor")), BarStyle.valueOf(Main.plugin.config.getString("JoinBossBar.BarStyle")), BarFlag.valueOf(Main.plugin.config.getString("JoinBossBar.BarFlag")));
                bar.addPlayer(e.getPlayer());
            }
        }
    }
    
    private void animateBossBar( Player p) {
        for ( String msgs : Main.plugin.config.getStringList("JoinBossBar.Messages")) {
            this.list.add(ChatColor.translateAlternateColorCodes('&', msgs.replaceAll("%player%", p.getName())));
        }
         BossBar bar = Bukkit.getServer().createBossBar(".", BarColor.valueOf(Main.plugin.config.getString("JoinBossBar.BarColor")), BarStyle.valueOf(Main.plugin.config.getString("JoinBossBar.BarStyle")), BarFlag.valueOf(Main.plugin.config.getString("JoinBossBar.BarFlag")));
        bar.addPlayer(p);
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.plugin, new Runnable() {
            public void run() {
                 String message = JoinBossBar.this.list.get(count);
                count++;
                if(count >= list.size()) {
                	count = 0;
                }
                bar.setTitle(message);
            }
        }, 0L, 20L * Main.plugin.config.getLong("JoinBossBar.AnimateSpeed"));
    }
}
