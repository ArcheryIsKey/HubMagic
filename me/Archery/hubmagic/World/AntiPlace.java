package me.Archery.hubmagic.World;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import me.Archery.hubmagic.HubMagic;

public class AntiPlace implements Listener
{
    
    @EventHandler
    public void onBlockBreak(BlockPlaceEvent e) {
        if (!e.getPlayer().hasPermission("HubMagic.AntiPlace.bypass") && HubMagic.getInstance().config.getBoolean("Disable.BlockPlace", true)) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', HubMagic.getInstance().config.getString("UnableToPlaceBlocksMSG")));
        }
    }
}
