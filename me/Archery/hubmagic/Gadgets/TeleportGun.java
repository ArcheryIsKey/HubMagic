package me.Archery.hubmagic.Gadgets;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.projectiles.ProjectileSource;

import me.Archery.hubmagic.Main;

public class TeleportGun implements Listener
{
    String pre;
    
    public TeleportGun() {
        this.pre = ChatColor.DARK_GRAY + "[" + ChatColor.AQUA + "HubMagic" + ChatColor.DARK_GRAY + "]";
    }
    
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
    	if(Main.plugin.config.getBoolean("Enable.TeleportGun", true)) {
        ItemStack bow = new ItemStack(Material.BOW);
        ItemMeta bowmeta = bow.getItemMeta();
        bowmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Main.plugin.config.getString("TeleportGun.Name")));
        bowmeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
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
            	e.getProjectile().setMetadata("teleportgun", new FixedMetadataValue(Main.plugin, "teleportgun"));
                Main.plugin.haveCooldownsTeleportGun.add(p.getUniqueId());
                Main.plugin.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        Main.plugin.haveCooldownsTeleportGun.remove(p.getUniqueId());
                        if (Main.plugin.config.getInt("TeleportGun.Cooldown") >= 5 && Main.plugin.config.getBoolean("Enable.CooldownAnnouce", true)) {
                            p.sendMessage(TeleportGun.this.pre + ChatColor.GREEN + " You no longer have a cooldown!");
                        }
                    }
                }, (long)(20 * Main.plugin.config.getInt("TeleportGun.Cooldown")));
            }
        }
    }
    
    @EventHandler
    public void arrowEvent(ProjectileHitEvent event) {
        if (event.getEntity() instanceof Arrow) {
            Arrow arrow = (Arrow)event.getEntity();
            ProjectileSource shooter = arrow.getShooter();
            if (shooter instanceof Player) {
                Player player = (Player)shooter;
                if (Main.plugin.config.getBoolean("Enable.TeleportGun", true)) {
                	Main.plugin.getServer().getLogger().info(arrow.getMetadata("teleportgun").toString());
                    player.teleport(arrow.getLocation().add(0.0, 1.0, 0.0));
                    arrow.remove();
                	
                }
            }
        }
    }
}
