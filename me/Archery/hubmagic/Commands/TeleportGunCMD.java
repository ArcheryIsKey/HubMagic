package me.Archery.hubmagic.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Archery.hubmagic.HubMagic;

public class TeleportGunCMD implements CommandExecutor
{
    String pre;
    
    public TeleportGunCMD() {
        this.pre = ChatColor.DARK_GRAY + "[" + ChatColor.AQUA + "HubMagic" + ChatColor.DARK_GRAY + "]";
    }
    
    @SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
        ItemStack bow = new ItemStack(Material.BOW);
        ItemMeta bowmeta = bow.getItemMeta();
        bowmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', HubMagic.plugin.config.getString("TeleportGun.Name")));
        bowmeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
        bowmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        bow.setItemMeta(bowmeta);
        if (args.length == 0) {
            if (!sender.hasPermission("HubMagic.TeleportGun.Give")) {
                sender.sendMessage(this.pre + " You may not use this command.");
                
            }
            else {
            	if(sender instanceof Player) {
                ((Player) sender).getInventory().addItem(bow);
                ((Player) sender).getInventory().setItem(9, new ItemStack(Material.ARROW, 1));
                sender.sendMessage(this.pre + ChatColor.GREEN + " Here's your Teleport Gun!");
                
            }
          }
        }
        else if (args.length == 1) {
        	OfflinePlayer t = Bukkit.getOfflinePlayer(args[0]);
        	if(t.isOnline()) {
            ((Player) t).getInventory().addItem(bow);
            sender.sendMessage(this.pre + ChatColor.GREEN + " " + args[0] + " has received their Teleport Gun.");
        	} else {
                sender.sendMessage(this.pre + ChatColor.RED + " Player not found.");
        	}
        }
		return true;
    }
}