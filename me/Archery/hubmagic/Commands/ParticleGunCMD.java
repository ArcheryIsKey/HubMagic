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

public class ParticleGunCMD implements CommandExecutor
{
	private String pre;
    
    public ParticleGunCMD() {
        this.pre = ChatColor.DARK_GRAY + "[" + ChatColor.AQUA + "HubMagic" + ChatColor.DARK_GRAY + "]";
    }
    
    @SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
        ItemStack gun = new ItemStack(Material.DIAMOND_HORSE_ARMOR);
        ItemMeta gunmeta = gun.getItemMeta();
        gunmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', HubMagic.getInstance().config.getString("ParticleGun.Name")));
        gun.setItemMeta(gunmeta);
        if (args.length == 0) {
            if (!sender.hasPermission("HubMagic.ParticleGun.Give")) {
                sender.sendMessage(this.pre + " You may not use this command.");
                
            }
            else {
            	if(sender instanceof Player) {
                ((Player) sender).getInventory().addItem(gun);
                sender.sendMessage(this.pre + ChatColor.GREEN + " Here's your Particle Gun!");
                
            	}
            }
        }
        else if (args.length == 1) {
        	OfflinePlayer t = Bukkit.getOfflinePlayer(args[0]);
        	if(t.isOnline()) {
            ((Player) t).getInventory().addItem(gun);
            sender.sendMessage(this.pre + ChatColor.GREEN + " " + args[0] + " has received their Particle Gun.");
        	} else {
                sender.sendMessage(this.pre + ChatColor.RED + " Player not found.");
        	}
        }
		return true;
    }
}