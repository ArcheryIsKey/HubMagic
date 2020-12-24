
package me.Archery.hubmagic.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Archery.hubmagic.Main;

public class WardrobeCMD implements CommandExecutor
{
    String pre;
    
    public WardrobeCMD() {
        this.pre = ChatColor.DARK_GRAY + "[" + ChatColor.AQUA + "HubMagic" + ChatColor.DARK_GRAY + "]";
    }
    
    public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
        ItemStack ward = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemMeta wardm = ward.getItemMeta();
        wardm.setDisplayName(ChatColor.translateAlternateColorCodes('&', Main.plugin.config.getString("Wardrobe.Name")));
        ward.setItemMeta(wardm);
        if (cmd.getName().equalsIgnoreCase("wardrobe") && args.length == 0) {
            if (!sender.hasPermission("HubMagic.Wardrobe.Give")) {
                sender.sendMessage(this.pre + " You may not use this command.");
            }
            else {
            	if(sender instanceof Player) {
                ((Player) sender).getInventory().addItem(new ItemStack[] { ward });
                sender.sendMessage(this.pre + ChatColor.GREEN + " Here's your Wardrobe Selector!");
            	}
            }
        }
        else if (args.length == 1) {
            Player t = Bukkit.getPlayer(args[0]);
            if (t.getName() == null) {
                sender.sendMessage(this.pre + ChatColor.RED + " Player not found.");
            }
            if (args.length == 1) {
                t.getInventory().addItem(new ItemStack[] { ward });
                t.sendMessage(this.pre + ChatColor.GREEN + " Here's your Wardrobe Selector!");
                sender.sendMessage(this.pre + ChatColor.GREEN + args[0] + " has received their Wardrobe Selector!");
            }
        }
        return true;
    }
}
