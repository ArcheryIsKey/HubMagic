package me.Archery.hubmagic.World;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

import me.Archery.hubmagic.Main;

public class AntiGrab implements Listener
{
    
    @EventHandler
    public void onDrop(EntityPickupItemEvent e) {
        if (!e.getEntity().hasPermission("HubMagic.AntiGrab.bypass") && Main.plugin.config.getBoolean("Disable.ItemGrabbing", true)) {
            e.setCancelled(true);
            if(!Main.plugin.config.getString("UnableToGrabItemsMSG").isEmpty()) {
            e.getEntity().sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.config.getString("UnableToGrabItemsMSG")));
            }
        }
    }
}
