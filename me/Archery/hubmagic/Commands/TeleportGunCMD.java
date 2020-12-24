
package me.Archery.hubmagic.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Archery.hubmagic.Main;

public class TeleportGunCMD implements CommandExecutor
{
    String pre;
    
    public TeleportGunCMD() {
        this.pre = ChatColor.DARK_GRAY + "[" + ChatColor.AQUA + "HubMagic" + ChatColor.DARK_GRAY + "]";
    }
    
    public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
        ItemStack bow = new ItemStack(Material.BOW);
        ItemMeta bowmeta = bow.getItemMeta();
        bowmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Main.plugin.config.getString("TeleportGun.Name")));
        bowmeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
        bowmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        bow.setItemMeta(bowmeta);
        if (cmd.getName().equalsIgnoreCase("teleportgun") && args.length == 0) {
            if (!sender.hasPermission("HubMagic.TeleportGun.Give")) {
                sender.sendMessage(this.pre + " You may not use this command.");
            }
            else {
            	if(sender instanceof Player) {
                ((Player) sender).getInventory().addItem(new ItemStack[] { bow });
                ((Player) sender).getInventory().setItem(9, new ItemStack(Material.ARROW, 1));
                sender.sendMessage(this.pre + ChatColor.GREEN + " Here's your Teleport Gun!");
            }
          }
        }
        else if (args.length == 1) {
            Player t = Bukkit.getPlayer(args[0]);
            if (t.getName() == null) {
                sender.sendMessage(this.pre + ChatColor.RED + " Player not found.");
            }
            if (args.length == 1) {
                t.getInventory().addItem(new ItemStack[] { bow });
                t.getInventory().setItem(9, new ItemStack(Material.ARROW, 1));
                t.sendMessage(this.pre + ChatColor.GREEN + " Here's your Teleport Gun!");
                sender.sendMessage(this.pre + ChatColor.GREEN + args[0] + " has received their Teleport Gun!");
            }
        }
        return true;
    }
}
