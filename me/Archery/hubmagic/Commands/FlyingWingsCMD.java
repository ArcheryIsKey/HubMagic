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

public class FlyingWingsCMD implements CommandExecutor
{
    String pre;
    
    public FlyingWingsCMD() {
        this.pre = ChatColor.DARK_GRAY + "[" + ChatColor.AQUA + "HubMagic" + ChatColor.DARK_GRAY + "]";
    }
    
    public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
        ItemStack gun = new ItemStack(Material.ELYTRA);
        ItemMeta gunmeta = gun.getItemMeta();
        gunmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Main.plugin.config.getString("FlyingWings.Name")));
        gun.setItemMeta(gunmeta);
        if (cmd.getName().equalsIgnoreCase("FlyingWings") && args.length == 0) {
            if (!sender.hasPermission("HubMagic.FlyingWings.Give")) {
                sender.sendMessage(this.pre + " You may not use this command.");
            }
            else {
            	if(sender instanceof Player) {
                ((Player) sender).getInventory().addItem(new ItemStack[] { gun });
                sender.sendMessage(this.pre + ChatColor.GREEN + " Here are your Flying Wings!");
            	}
            }
        }
        else if (args.length == 1 && sender.hasPermission("HubMagic.FlyingWings.Give")) {
            Player t = Bukkit.getPlayer(args[0]);
            if (t.getName() == null) {
                sender.sendMessage(this.pre + ChatColor.RED + " Player not found.");
            }
            if (args.length == 1) {
                t.getInventory().addItem(new ItemStack[] { gun });
                t.sendMessage(this.pre + ChatColor.GREEN + " Here are your Flying Wings!");
                sender.sendMessage(this.pre + ChatColor.GREEN + " " + args[0].toString() + " has Received Their FlyingWings!");
            }
        }
        return true;
    }
}
