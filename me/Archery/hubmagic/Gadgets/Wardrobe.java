
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

import me.Archery.hubmagic.Main;

public class Wardrobe implements Listener
{
	
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (Main.plugin.config.getBoolean("Enable.Wardrobe", true)) {
            ItemStack ward = new ItemStack(Material.LEATHER_CHESTPLATE);
            ItemMeta wardm = ward.getItemMeta();
            wardm.setDisplayName(ChatColor.translateAlternateColorCodes('&', Main.plugin.config.getString("Wardrobe.Name")));
            ward.setItemMeta(wardm);
            if (p.hasPermission("HubMagic.Wardrobe.Use")) {
                p.getInventory().setItem(Main.plugin.config.getInt("Wardrobe.Slot"), ward);
            }
        }
    }
    
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getHand() == EquipmentSlot.OFF_HAND) {
            return;
        }
        if(Main.plugin.config.getBoolean("Enable.Wardrobe", true)) {	
        Player p = e.getPlayer();
        ItemStack ward = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemMeta wardm = ward.getItemMeta();
        wardm.setDisplayName(ChatColor.translateAlternateColorCodes('&', Main.plugin.config.getString("Wardrobe.Name")));
        ward.setItemMeta(wardm);
        if (p.getInventory().getItemInMainHand().isSimilar(ward) && p.hasPermission("HubMagic.Wardrobe.Use")) {
        	
            p.openInventory(Main.plugin.ward);
        }
      }
    }
    
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player) {
            Player p = (Player) e.getWhoClicked();
            ItemStack clicked = e.getCurrentItem();
            if(e.getInventory().equals(Main.plugin.ward)) {
            if(clicked != null) {
            if (clicked.getType() == Material.BARRIER) {
                p.getInventory().setHelmet((ItemStack) null);
                p.getInventory().setChestplate((ItemStack) null);
                p.getInventory().setLeggings((ItemStack) null);
                p.getInventory().setBoots((ItemStack) null);
                e.setCancelled(true);
                return;
            }
            if (clicked.getType() == Material.LEATHER_HELMET) {
                p.getInventory().setHelmet(new ItemStack(Material.LEATHER_HELMET));
                e.setCancelled(true);
                return;
            }
            if (clicked.getType() == Material.GOLDEN_HELMET) {
                p.getInventory().setHelmet(new ItemStack(Material.GOLDEN_HELMET));
                e.setCancelled(true);
                return;
            }
            if (clicked.getType() == Material.IRON_HELMET) {
                p.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
                e.setCancelled(true);
                return;
            }
            if (clicked.getType() == Material.DIAMOND_HELMET) {
                p.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
                e.setCancelled(true);
                return;
            }
            if (clicked.getType() == Material.CHAINMAIL_HELMET) {
                p.getInventory().setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));
                e.setCancelled(true);
                return;
            }
            if (clicked.getType() == Material.LEATHER_CHESTPLATE) {
                p.getInventory().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
                e.setCancelled(true);
                return;
            }
            if (clicked.getType() == Material.GOLDEN_CHESTPLATE) {
                p.getInventory().setChestplate(new ItemStack(Material.GOLDEN_CHESTPLATE));
                e.setCancelled(true);
                return;
            }
            if (clicked.getType() == Material.IRON_CHESTPLATE) {
                p.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
                e.setCancelled(true);
                return;
            }
            if (clicked.getType() == Material.DIAMOND_CHESTPLATE) {
                p.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
                e.setCancelled(true);
                return;
            }
            if (clicked.getType() == Material.CHAINMAIL_CHESTPLATE) {
                p.getInventory().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
                e.setCancelled(true);
                return;
            }
            if (clicked.getType() == Material.GOLDEN_LEGGINGS) {
                p.getInventory().setLeggings(new ItemStack(Material.GOLDEN_LEGGINGS));
                e.setCancelled(true);
                return;
            }
            if (clicked.getType() == Material.IRON_LEGGINGS) {
                p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
                e.setCancelled(true);
                return;
            }
            if (clicked.getType() == Material.DIAMOND_LEGGINGS) {
                p.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
                e.setCancelled(true);
                return;
            }
            if (clicked.getType() == Material.CHAINMAIL_LEGGINGS) {
                p.getInventory().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
                e.setCancelled(true);
                return;
            }
            if (clicked.getType() == Material.LEATHER_LEGGINGS) {
                p.getInventory().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
                e.setCancelled(true);
                return;
            }
            if (clicked.getType() == Material.LEATHER_BOOTS) {
                p.getInventory().setBoots(new ItemStack(Material.LEATHER_BOOTS));
                e.setCancelled(true);
                return;
            }
            if (clicked.getType() == Material.GOLDEN_BOOTS) {
                p.getInventory().setBoots(new ItemStack(Material.GOLDEN_BOOTS));
                e.setCancelled(true);
                return;
            }
            if (clicked.getType() == Material.IRON_BOOTS) {
                p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
                e.setCancelled(true);
                return;
            }
            if (clicked.getType() == Material.DIAMOND_BOOTS) {
                p.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
                e.setCancelled(true);
                return;
            }
            if (clicked.getType() == Material.CHAINMAIL_BOOTS) {
                p.getInventory().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
                e.setCancelled(true);
                  }
               }
            }
        }
    }
}
