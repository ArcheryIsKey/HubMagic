
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

import me.Archery.hubmagic.Main;

public class FlyingWings implements Listener
{
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (Main.plugin.config.getBoolean("Enable.FlyingWings", true)) {
            ItemStack fly = new ItemStack(Material.getMaterial("ELYTRA"));
            ItemMeta flym = fly.getItemMeta();
            flym.setDisplayName(ChatColor.translateAlternateColorCodes('&', Main.plugin.config.getString("FlyingWings.Name")));
            fly.setItemMeta(flym);
            if (p.hasPermission("HubMagic.FlyingWings.Use")) {
                p.getInventory().setItem(Main.plugin.config.getInt("FlyingWings.Slot"), fly);
            }
        }
    }
    
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getHand() == EquipmentSlot.OFF_HAND) {
            return;
        }
        Player p = e.getPlayer();
        ItemStack fly = new ItemStack(Material.getMaterial("ELYTRA"));
        ItemMeta flym = fly.getItemMeta();
        flym.setDisplayName(ChatColor.translateAlternateColorCodes('&', Main.plugin.config.getString("FlyingWings.Name")));
        fly.setItemMeta(flym);
        if (p.getInventory().getItemInMainHand().isSimilar(fly) && e.getAction() == Action.RIGHT_CLICK_AIR && Main.plugin.config.getBoolean("Enable.FlyingWings", true) && p.hasPermission("HubMagic.FlyingWings.Use")) {
            if (!Main.plugin.flyingwings.contains(p.getUniqueId())) {
                p.getInventory().setChestplate(new ItemStack(Material.ELYTRA));
                Main.plugin.flyingwings.add(p.getUniqueId());
                p.getInventory().setItemInMainHand(fly);
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.config.getString("FlyingWings.EnableMSG")));
                p.setAllowFlight(true);
                p.setFlying(true);
                e.setCancelled(true);
            }
            else if (Main.plugin.flyingwings.contains(p.getUniqueId())) {
                p.getInventory().setChestplate(null);
                e.setCancelled(true);
                p.getInventory().setItemInMainHand(fly);
                Main.plugin.flyingwings.remove(p.getUniqueId());
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.config.getString("FlyingWings.DisableMSG")));
                p.setAllowFlight(false);
                p.setFlying(false);
            }
        }
    }
}
