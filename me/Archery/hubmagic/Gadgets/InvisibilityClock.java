package me.Archery.hubmagic.Gadgets;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
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

public class InvisibilityClock implements Listener
{
    private Main plugin;
    File file;
    YamlConfiguration pfile;
    
    public InvisibilityClock(Main instance) {
        this.file = new File("plugins/HubMagic/config.yml");
        this.pfile = YamlConfiguration.loadConfiguration(this.file);
        this.plugin = instance;
    }
    
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getHand() == EquipmentSlot.OFF_HAND) {
            return;
        }
        if(this.pfile.getBoolean("Enable.InvisibilityClock", true)) {
        Player p = e.getPlayer();
        ItemStack clock = new ItemStack(Material.CLOCK);
        ItemMeta clockmeta = clock.getItemMeta();
        clockmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.pfile.getString("InvisibilityClock.Name")));
        clock.setItemMeta(clockmeta);
        if (p.hasPermission("HubMagic.InvisibilityClock.Use") && (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && p.getInventory().getItemInMainHand().equals(clock)) {
            if (!this.plugin.invis.contains(p.getUniqueId())) {
            	Bukkit.getOnlinePlayers().forEach(pl -> p.hidePlayer(Main.plugin, pl));
            	this.plugin.invis.add(p.getUniqueId());
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.config.getString("InvisibilityClock.PlayersHidden")));
            }
            else {
            	Bukkit.getOnlinePlayers().forEach(pl -> p.showPlayer(Main.plugin, pl));
                    this.plugin.invis.remove(p.getUniqueId());
                }
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.config.getString("InvisibilityClock.PlayersShown")));
        }
      }
    }
    
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        ItemStack clock = new ItemStack(Material.getMaterial("CLOCK"));
        ItemMeta clockmeta = clock.getItemMeta();
        clockmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.pfile.getString("InvisibilityClock.Name")));
        clock.setItemMeta(clockmeta);
        if (this.pfile.getBoolean("Enable.InvisibilityClock", true) && e.getPlayer().hasPermission("HubMagic.InvisibilityClock.Use")) {
            e.getPlayer().getInventory().setItem(this.pfile.getInt("InvisibilityClock.Slot"), clock);
        }
    }
}
