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

import me.Archery.hubmagic.HubMagic;

public class FlyingWings implements Listener
{
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (HubMagic.getInstance().config.getBoolean("Enable.FlyingWings", true)) {
            ItemStack fly = new ItemStack(Material.ELYTRA);
            ItemMeta flym = fly.getItemMeta();
            flym.setDisplayName(ChatColor.translateAlternateColorCodes('&', HubMagic.getInstance().config.getString("FlyingWings.Name")));
            fly.setItemMeta(flym);
            if (p.hasPermission("HubMagic.FlyingWings.Use")) {
                p.getInventory().setItem(HubMagic.getInstance().config.getInt("FlyingWings.Slot"), fly);
            }
        }
    }
    
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getHand() == EquipmentSlot.OFF_HAND) {
            return;
        }
        Player p = e.getPlayer();
        ItemStack fly = new ItemStack(Material.ELYTRA);
        ItemMeta flym = fly.getItemMeta();
        flym.setDisplayName(ChatColor.translateAlternateColorCodes('&', HubMagic.getInstance().config.getString("FlyingWings.Name")));
        fly.setItemMeta(flym);
        if (p.getInventory().getItemInMainHand().isSimilar(fly) && e.getAction() == Action.RIGHT_CLICK_AIR && HubMagic.getInstance().config.getBoolean("Enable.FlyingWings", true) && p.hasPermission("HubMagic.FlyingWings.Use")) {
            if (!HubMagic.getInstance().flyingwings.contains(p.getUniqueId())) {
                p.getInventory().setChestplate(new ItemStack(Material.ELYTRA));
                HubMagic.getInstance().flyingwings.add(p.getUniqueId());
                p.getInventory().setItemInMainHand(fly);
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', HubMagic.getInstance().config.getString("FlyingWings.EnableMSG")));
                p.setAllowFlight(true);
                p.setFlying(true);
                e.setCancelled(true);
            }
            else if (HubMagic.getInstance().flyingwings.contains(p.getUniqueId())) {
                p.getInventory().setChestplate(null);
                e.setCancelled(true);
                p.getInventory().setItemInMainHand(fly);
                HubMagic.getInstance().flyingwings.remove(p.getUniqueId());
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', HubMagic.getInstance().config.getString("FlyingWings.DisableMSG")));
                p.setAllowFlight(false);
                p.setFlying(false);
            }
        }
    }
}