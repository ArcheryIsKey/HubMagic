package me.Archery.hubmagic.Gadgets;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Archery.hubmagic.HubMagic;

public class Wardrobe implements Listener
{
	
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (HubMagic.plugin.config.getBoolean("Enable.Wardrobe", true)) {
            ItemStack ward = new ItemStack(Material.LEATHER_CHESTPLATE);
            ItemMeta wardm = ward.getItemMeta();
            wardm.setDisplayName(ChatColor.translateAlternateColorCodes('&', HubMagic.plugin.config.getString("Wardrobe.Name")));
            ward.setItemMeta(wardm);
            if (p.hasPermission("HubMagic.Wardrobe.Use")) {
                p.getInventory().setItem(HubMagic.plugin.config.getInt("Wardrobe.Slot"), ward);
            }
        }
    }
    
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getHand() == EquipmentSlot.OFF_HAND) {
            return;
        }
        if(HubMagic.plugin.config.getBoolean("Enable.Wardrobe", true)) {	
        Player p = e.getPlayer();
        ItemStack ward = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemMeta wardm = ward.getItemMeta();
        wardm.setDisplayName(ChatColor.translateAlternateColorCodes('&', HubMagic.plugin.config.getString("Wardrobe.Name")));
        ward.setItemMeta(wardm);
        if (p.getInventory().getItemInMainHand().equals(ward) && p.hasPermission("HubMagic.Wardrobe.Use")) {
        	e.setCancelled(true);
            p.openInventory(HubMagic.plugin.ward);
        }
      }
    }
    
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player) {
            Player p = (Player) e.getWhoClicked();
            ItemStack clicked = e.getCurrentItem();
            if(e.getInventory().equals(HubMagic.plugin.ward) && clicked != null) {
            	e.setCancelled(true);
            	setWardrobe(p, clicked);
            }
        }
    }
    
    private void setWardrobe(Player p, ItemStack item) {
    	if(item.getType().name().endsWith("_HELMET")) {
    		p.getInventory().setHelmet(item);
    }
    	if(item.getType().name().endsWith("_CHESTPLATE")) {
    		p.getInventory().setChestplate(item);
    }
		if(item.getType().name().endsWith("_LEGGINGS")) {
			p.getInventory().setLeggings(item);
	}
		if(item.getType().name().endsWith("_BOOTS")) {
			p.getInventory().setBoots(item);
		}
		if(item.getType() == Material.BARRIER) {
			p.getInventory().setArmorContents(null);
		}
    }
}