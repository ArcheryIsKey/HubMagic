package me.Archery.hubmagic.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Archery.hubmagic.HubMagic;

public class InvisibilityClockCMD implements CommandExecutor
{
	private String pre;
    
    public InvisibilityClockCMD() {
        this.pre = ChatColor.DARK_GRAY + "[" + ChatColor.AQUA + "HubMagic" + ChatColor.DARK_GRAY + "]";
    }
    
    @SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
        ItemStack clock = new ItemStack(Material.CLOCK);
        ItemMeta clockmeta = clock.getItemMeta();
        clockmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', HubMagic.plugin.config.getString("InvisibilityClock.Name")));
        clock.setItemMeta(clockmeta);
        if (args.length == 0) {
            if (!sender.hasPermission("HubMagic.InvisibilityClock.Give")) {
                sender.sendMessage(this.pre + " You may not use this command.");
                
            }
            else {
            	if(sender instanceof Player) {
                ((Player) sender).getInventory().addItem(clock);
                sender.sendMessage(this.pre + ChatColor.GREEN + " Here's your Invisibility Clock!");
                
            	}
            }
        }
        else if (args.length == 1 && sender.hasPermission("HubMagic.InvisibilityClock.Give")) {
        	OfflinePlayer t = Bukkit.getOfflinePlayer(args[0]);
        	if(t.isOnline()) {
            ((Player) t).getInventory().addItem(clock);
            sender.sendMessage(this.pre + ChatColor.GREEN + " " + args[0] + " has received their Invisibility Clock.");
        	} else {
                sender.sendMessage(this.pre + ChatColor.RED + " Player not found.");
        	}
        }
		return true;
    }
}