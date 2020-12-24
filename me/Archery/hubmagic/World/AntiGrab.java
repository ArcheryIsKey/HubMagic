package me.Archery.hubmagic.World;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

import me.Archery.hubmagic.HubMagic;

public class AntiGrab implements Listener
{
    
    @EventHandler
    public void onDrop(EntityPickupItemEvent e) {
        if (!e.getEntity().hasPermission("HubMagic.AntiGrab.bypass") && HubMagic.plugin.config.getBoolean("Disable.ItemGrabbing", true)) {
            e.setCancelled(true);
            if(!HubMagic.plugin.config.getString("UnableToGrabItemsMSG").isEmpty()) {
            e.getEntity().sendMessage(ChatColor.translateAlternateColorCodes('&', HubMagic.plugin.config.getString("UnableToGrabItemsMSG")));
            }
        }
    }
}
