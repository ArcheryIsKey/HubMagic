
package me.Archery.hubmagic.Gadgets;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Archery.hubmagic.Main;

public class HatSelector implements Listener
{
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getHand() == EquipmentSlot.OFF_HAND) {
            return;
        }
        Player p = e.getPlayer();
        ItemStack selector = new ItemStack(Material.BEACON);
        ItemMeta selectorm = selector.getItemMeta();
        selectorm.setDisplayName(ChatColor.translateAlternateColorCodes('&', Main.plugin.config.getString("HatSelector.Name")));
        selector.setItemMeta(selectorm);
        if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && e.getPlayer().getInventory().getItemInMainHand().isSimilar(selector) && Main.plugin.config.getBoolean("Enable.HatSelector", true) && p.hasPermission("HubMagic.HatSelector.Use")) {
            p.openInventory(Main.plugin.select);
        }
    }
    
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        ItemStack selector = new ItemStack(Material.BEACON);
        ItemMeta selectorm = selector.getItemMeta();
        selectorm.setDisplayName(ChatColor.translateAlternateColorCodes('&', Main.plugin.config.getString("HatSelector.Name")));
        selector.setItemMeta(selectorm);
        if (Main.plugin.config.getBoolean("Enable.HatSelector", true) && p.hasPermission("HubMagic.HatSelector.Use")) {
            p.getInventory().setItem(Main.plugin.config.getInt("HatSelector.Slot"), selector);
        }
    }
    
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player) {
            Player p = (Player)e.getWhoClicked();
            if (e.getInventory().equals(Main.plugin.select)) {
                e.setCancelled(true);
                p.getInventory().setHelmet(e.getCurrentItem());
                Main.plugin.getServer().getLogger().info("trying to set " + e.getCurrentItem().toString() + " as helmet");
                p.updateInventory();
            }
        }
    }
}
