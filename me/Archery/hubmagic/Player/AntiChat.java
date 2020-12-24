package me.Archery.hubmagic.Player;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.Archery.hubmagic.HubMagic;

public class AntiChat implements Listener
{
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (HubMagic.plugin.config.getBoolean("Disable.Chat", true) && !p.hasPermission("HubMagic.AntiChat.bypass")) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', HubMagic.plugin.config.getString("UnableToChatMSG")));
            e.setCancelled(true);
        }
    }
}
