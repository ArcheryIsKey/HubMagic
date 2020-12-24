package me.Archery.hubmagic.Gadgets;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.Archery.hubmagic.HubMagic;

public class SpeedStick implements Listener {
    
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        ItemStack boot = new ItemStack(Material.STICK);
        ItemMeta bootmeta = boot.getItemMeta();
        bootmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', HubMagic.plugin.config.getString("SpeedStick.Name")));
        boot.setItemMeta(bootmeta);
        if (HubMagic.plugin.config.getBoolean("Enable.SpeedStick", true) && e.getPlayer().hasPermission("HubMagic.SpeedStick.Use")) {
            e.getPlayer().getInventory().setItem(HubMagic.plugin.config.getInt("SpeedStick.Slot"), boot);
        }
    }
    
    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        if (e.getHand() == EquipmentSlot.OFF_HAND) {
            return;
        }
        Player p = e.getPlayer();
        ItemStack boot = new ItemStack(Material.STICK);
        ItemMeta bootmeta = boot.getItemMeta();
        bootmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', HubMagic.plugin.config.getString("SpeedStick.Name")));
        boot.setItemMeta(bootmeta);
        if (HubMagic.plugin.config.getBoolean("Enable.SpeedStick", true)) {
            if (!p.getInventory().getItemInMainHand().equals(boot)) {
                return;
            }
            if (e.getPlayer().hasPermission("HubMagic.SpeedStick.Use") && !HubMagic.plugin.haveCooldownsSpeedStick.contains(p.getUniqueId())) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 2));
                HubMagic.plugin.haveCooldownsSpeedStick.add(p.getUniqueId());
            }
        }
    }
}
