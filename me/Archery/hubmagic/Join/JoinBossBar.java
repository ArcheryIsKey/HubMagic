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

import me.Archery.hubmagic.HubMagic;

public class JoinBossBar implements Listener
{
	
    private ArrayList<String> list;
    private int count;
    
    public JoinBossBar() {
        this.list = new ArrayList<String>();
    }
    
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (HubMagic.getInstance().config.getBoolean("Enable.JoinBossBar", true)) {
            if (HubMagic.getInstance().config.getBoolean("JoinBossBar.Animate", true)) {
                animateBossBar(e.getPlayer());
            }
            else {
                 BossBar bar = Bukkit.getServer().createBossBar(ChatColor.translateAlternateColorCodes('&', HubMagic.getInstance().config.getString("JoinBossBar.Message").replaceAll("%player%", e.getPlayer().getName())), BarColor.valueOf(HubMagic.getInstance().config.getString("JoinBossBar.BarColor")), BarStyle.valueOf(HubMagic.getInstance().config.getString("JoinBossBar.BarStyle")), BarFlag.valueOf(HubMagic.getInstance().config.getString("JoinBossBar.BarFlag")));
                bar.addPlayer(e.getPlayer());
            }
        }
    }
    
    private void animateBossBar( Player p) {
        for ( String msgs : HubMagic.getInstance().config.getStringList("JoinBossBar.Messages")) {
            this.list.add(ChatColor.translateAlternateColorCodes('&', msgs.replaceAll("%player%", p.getName())));
        }
         BossBar bar = Bukkit.getServer().createBossBar(".", BarColor.valueOf(HubMagic.getInstance().config.getString("JoinBossBar.BarColor")), BarStyle.valueOf(HubMagic.getInstance().config.getString("JoinBossBar.BarStyle")), BarFlag.valueOf(HubMagic.getInstance().config.getString("JoinBossBar.BarFlag")));
        bar.addPlayer(p);
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(HubMagic.getInstance(), new Runnable() {
            public void run() {
                 String message = JoinBossBar.this.list.get(count);
                count++;
                if(count >= list.size()) {
                	count = 0;
                }
                bar.setTitle(message);
            }
        }, 0L, 20L * HubMagic.getInstance().config.getLong("JoinBossBar.AnimateSpeed"));
    }
}
