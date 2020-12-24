package me.Archery.hubmagic.World;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import me.Archery.hubmagic.HubMagic;

public class AntiBreak implements Listener
{
    
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (HubMagic.plugin.config.getBoolean("Disable.BlockBreak", true) && !e.getPlayer().hasPermission("HubMagic.AntiBreak.bypass")) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', HubMagic.plugin.config.getString("UnableToBreakBlocksMSG")));
        }
    }
}
