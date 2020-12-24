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
            switch(clicked.getType()) {
            
			case BARRIER:
				p.getInventory().setHelmet(null);
	            p.getInventory().setChestplate(null);
	            p.getInventory().setLeggings(null);
	            p.getInventory().setBoots(null);
	            
	            break;
				
			case LEATHER_HELMET:
                p.getInventory().setHelmet(new ItemStack(Material.LEATHER_HELMET));
                break;
            
			case GOLDEN_HELMET:
                p.getInventory().setHelmet(new ItemStack(Material.GOLDEN_HELMET));
                 break;
            
			case IRON_HELMET:
                p.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
                break;
            
			case DIAMOND_HELMET:
                p.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
                break;
            
			case CHAINMAIL_HELMET:
                p.getInventory().setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));
                break;
            
			case LEATHER_CHESTPLATE:
                p.getInventory().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
                break;
            
			case GOLDEN_CHESTPLATE:
                p.getInventory().setChestplate(new ItemStack(Material.GOLDEN_CHESTPLATE));
                break;
             
			case IRON_CHESTPLATE:
                p.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
                break;
            
			case DIAMOND_CHESTPLATE:
                p.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
                break;
            
			case CHAINMAIL_CHESTPLATE:
                p.getInventory().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
                break;
            
            case GOLDEN_LEGGINGS:
                p.getInventory().setLeggings(new ItemStack(Material.GOLDEN_LEGGINGS));
                break;
             
            case IRON_LEGGINGS:
                p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
                break;
            
            case DIAMOND_LEGGINGS:
                p.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
                break;
            
            case CHAINMAIL_LEGGINGS:
                p.getInventory().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
                break;
            
            case LEATHER_LEGGINGS:
                p.getInventory().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
                break;
            
            case LEATHER_BOOTS:
                p.getInventory().setBoots(new ItemStack(Material.LEATHER_BOOTS));
                break;
            
            case GOLDEN_BOOTS:
                p.getInventory().setBoots(new ItemStack(Material.GOLDEN_BOOTS));
                break;
            
            case IRON_BOOTS:
                p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
                break;
            
            case DIAMOND_BOOTS:
                p.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
                break;
            
            case CHAINMAIL_BOOTS:
                p.getInventory().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
                break;
                
            default: break;
               }
            }
        }
    }
}
