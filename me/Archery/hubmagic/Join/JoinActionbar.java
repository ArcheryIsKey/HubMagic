package me.Archery.hubmagic.Join;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.Archery.hubmagic.Main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class JoinActionbar implements Listener
{
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (Main.plugin.config.getBoolean("Enable.JoinActionBar", true)) {
            e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', Main.plugin.config.getString("JoinActionBar.Text")).replaceAll("%player%", e.getPlayer().getName())));
        }
    }
}
