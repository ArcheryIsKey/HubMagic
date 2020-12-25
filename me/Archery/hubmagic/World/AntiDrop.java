package me.Archery.hubmagic.World;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import me.Archery.hubmagic.HubMagic;

public class AntiDrop implements Listener
{
    
    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        if (!e.getPlayer().hasPermission("HubMagic.AntiDrop.bypass") && HubMagic.getInstance().config.getBoolean("Disable.ItemDropping", true)) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', HubMagic.getInstance().config.getString("UnableToDropItemsMSG")));
        }
    }
}
