package me.Archery.hubmagic.World;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import me.Archery.hubmagic.Main;

public class AntiBreak implements Listener
{
    
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (Main.plugin.config.getBoolean("Disable.BlockBreak", true) && !e.getPlayer().hasPermission("HubMagic.AntiBreak.bypass")) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.config.getString("UnableToBreakBlocksMSG")));
        }
    }
}
