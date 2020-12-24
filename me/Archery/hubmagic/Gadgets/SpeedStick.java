package me.Archery.hubmagic.Gadgets;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.Archery.hubmagic.Main;

public class SpeedStick implements Listener {
    
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        ItemStack boot = new ItemStack(Material.STICK);
        ItemMeta bootmeta = boot.getItemMeta();
        bootmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Main.plugin.config.getString("SpeedStick.Name")));
        boot.setItemMeta(bootmeta);
        if (Main.plugin.config.getBoolean("Enable.SpeedStick", true) && e.getPlayer().hasPermission("HubMagic.SpeedStick.Use")) {
            e.getPlayer().getInventory().setItem(Main.plugin.config.getInt("SpeedStick.Slot"), boot);
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
        bootmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Main.plugin.config.getString("SpeedStick.Name")));
        boot.setItemMeta(bootmeta);
        if (Main.plugin.config.getBoolean("Enable.SpeedStick", true) && e.getAction() == Action.RIGHT_CLICK_AIR) {
            if (!p.getInventory().getItemInMainHand().isSimilar(boot)) {
                return;
            }
            if (e.getPlayer().hasPermission("HubMagic.SpeedStick.Use") && !Main.plugin.haveCooldownsSpeedStick.contains(p.getUniqueId())) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 2));
                Main.plugin.haveCooldownsSpeedStick.add(p.getUniqueId());
            }
        }
    }
}
