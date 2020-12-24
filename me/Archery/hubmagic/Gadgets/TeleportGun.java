package me.Archery.hubmagic.Gadgets;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.projectiles.ProjectileSource;

import me.Archery.hubmagic.Main;

public class TeleportGun implements Listener
{
    
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
    	if(Main.plugin.config.getBoolean("Enable.TeleportGun", true)) {
        ItemStack bow = new ItemStack(Material.BOW);
        ItemMeta bowmeta = bow.getItemMeta();
        bowmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Main.plugin.config.getString("TeleportGun.Name")));
        bowmeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
        bowmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        bow.setItemMeta(bowmeta);
        if(e.getPlayer().hasPermission("HubMagic.TeleportGun.Use")) {
            e.getPlayer().getInventory().setItem(Main.plugin.config.getInt("TeleportGun.Slot"), bow);
            e.getPlayer().getInventory().setItem(9, new ItemStack(Material.ARROW, 1));
          
        }
      }
    }
    
    @EventHandler
    public void onClick(EntityShootBowEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player)e.getEntity();
            ItemStack bow = new ItemStack(Material.BOW);
            ItemMeta bowmeta = bow.getItemMeta();
            bowmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Main.plugin.config.getString("TeleportGun.Name")));
            bow.setItemMeta(bowmeta);
            if (p.hasPermission("HubMagic.TeleportGun.Use") && e.getBow().isSimilar(bow) && Main.plugin.config.getBoolean("Enable.TeleportGun", true) && !Main.plugin.haveCooldownsTeleportGun.contains(p.getUniqueId())) {
            	e.setCancelled(true);
            	Arrow arrow = (Arrow) p.getWorld().spawnEntity(p.getLocation(), EntityType.ARROW);
            	arrow.setVelocity(e.getEntity().getVelocity());
            	arrow.setShooter(p);
                Main.plugin.haveCooldownsTeleportGun.add(p.getUniqueId());
            }
        }
    }
    
    @EventHandler
    public void arrowEvent(ProjectileHitEvent e) {
        if (e.getEntity() instanceof Arrow) {
            Arrow arrow = (Arrow)e.getEntity();
            ProjectileSource shooter = arrow.getShooter();
            if (shooter instanceof Player) {
                Player p = (Player)shooter;
                if (Main.plugin.config.getBoolean("Enable.TeleportGun", true)) {
                    p.teleport(arrow.getLocation().add(0.0, 1.0, 0.0));
                    p.setVelocity(p.getVelocity());
                    arrow.remove();
                	
                }
            }
        }
    }
}
