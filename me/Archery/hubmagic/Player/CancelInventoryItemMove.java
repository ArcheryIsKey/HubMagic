package me.Archery.hubmagic.Player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.Archery.hubmagic.Main;

public class CancelInventoryItemMove implements Listener
{
    @EventHandler
    public void onMove(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player) {
            Player p = (Player)e.getWhoClicked();
            if (!p.hasPermission("HubMagic.InventoryMove.bypass") && Main.plugin.config.getBoolean("Disable.ItemMovingInsideInv", true)) {
                e.setCancelled(true);
            	
            }
        }
    }
}
