
package me.Archery.hubmagic.World;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import me.Archery.hubmagic.Main;

public class AntiDrop implements Listener
{
    
    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        if (!e.getPlayer().hasPermission("HubMagic.AntiDrop.bypass") && Main.plugin.config.getBoolean("Disable.ItemDropping", true)) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.config.getString("UnableToDropItemsMSG")));
        }
    }
}
