package me.Archery.hubmagic.Gadgets;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import me.Archery.hubmagic.Main;

public class ParticleGun implements Listener
{
    
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        ItemStack gun = new ItemStack(Material.DIAMOND_HORSE_ARMOR);
        ItemMeta gunmeta = gun.getItemMeta();
        gunmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Main.plugin.config.getString("ParticleGun.Name")));
        gun.setItemMeta(gunmeta);
        if (Main.plugin.config.getBoolean("Enable.ParticleGun", true) && e.getPlayer().hasPermission("HubMagic.ParticleGun.Use")) {
            e.getPlayer().getInventory().setItem(Main.plugin.config.getInt("ParticleGun.Slot"), gun);
        }
    }
    
    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        if (e.getHand() == EquipmentSlot.OFF_HAND) {
            return;
        }
        Player p = e.getPlayer();
        ItemStack gun = new ItemStack(Material.DIAMOND_HORSE_ARMOR);
        ItemMeta gunmeta = gun.getItemMeta();
        gunmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Main.plugin.config.getString("ParticleGun.Name")));
        gun.setItemMeta(gunmeta);
        if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && Main.plugin.config.getBoolean("Enable.ParticleGun", true) && e.getPlayer().hasPermission("HubMagic.ParticleGun.Use") && p.getInventory().getItemInMainHand().isSimilar(gun) && !Main.plugin.haveCooldownsParticleGun.contains(p.getUniqueId())) {
        	Location pl = p.getLocation();
        	pl.add(0, 1.5, 0);
            Snowball snowball = (Snowball) p.getWorld().spawnEntity(pl, EntityType.SNOWBALL);
            snowball.setShooter(p);
            snowball.setVelocity(new Vector(5,5,5).multiply(p.getLocation().getDirection()));
            Main.plugin.haveCooldownsParticleGun.add(p.getUniqueId());
            Main.plugin.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    Main.plugin.haveCooldownsParticleGun.remove(p.getUniqueId());
                    if (Main.plugin.config.getInt("ParticleGun.Cooldown") >= 5 && Main.plugin.config.getBoolean("Enable.CooldownAnnouce", true)) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.config.getString("ParticleGun.CooldownMSG")));
                    }
                }
            }, (long)(20 * Main.plugin.config.getInt("ParticleGun.Cooldown")));
        }
    }
    
    @EventHandler
    public void hti(ProjectileHitEvent e) {
        if (e.getEntity() instanceof Snowball && Main.plugin.config.getBoolean("Enable.ParticleGun", true)) {
        	e.getEntity().getLocation().getWorld().spawnParticle(Particle.LAVA, e.getEntity().getLocation().getX(), e.getEntity().getLocation().getY(), e.getEntity().getLocation().getZ(), 15);
        	e.getEntity().getLocation().getWorld().spawnParticle(Particle.CLOUD, e.getEntity().getLocation().getX(), e.getEntity().getLocation().getY(), e.getEntity().getLocation().getZ(), 15);
        	e.getEntity().getLocation().getWorld().spawnParticle(Particle.HEART, e.getEntity().getLocation().getX(), e.getEntity().getLocation().getY(), e.getEntity().getLocation().getZ(), 15);
        	
            e.getEntity().getWorld().playSound(e.getEntity().getLocation(), Sound.ENTITY_CHICKEN_EGG, 40.0f, 40.0f);
        }
    }
}
