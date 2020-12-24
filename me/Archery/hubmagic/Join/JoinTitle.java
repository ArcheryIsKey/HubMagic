package me.Archery.hubmagic.Join;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.Archery.hubmagic.HubMagic;

public class JoinTitle implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, SecurityException, ClassNotFoundException, NoSuchMethodException, InstantiationException {
        if (HubMagic.plugin.config.getBoolean("Enable.JoinTitle", true)) {
        	String title = ChatColor.translateAlternateColorCodes('&', HubMagic.plugin.getConfig().getString("JoinTitle.Title.Text").replaceAll("%player%", e.getPlayer().getName()));
        	String subtitle = ChatColor.translateAlternateColorCodes('&', HubMagic.plugin.getConfig().getString("JoinTitle.Subtitle.Text").replaceAll("%player%", e.getPlayer().getName()));
        	e.getPlayer().sendTitle(title, subtitle, 20, 40, 20);
        	
        }
    }
}