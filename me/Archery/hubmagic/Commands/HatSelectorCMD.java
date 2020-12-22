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

public class HatSelectorCMD implements CommandExecutor
{
    String pre;
    
    public HatSelectorCMD() {
        this.pre = ChatColor.DARK_GRAY + "[" + ChatColor.AQUA + "HubMagic" + ChatColor.DARK_GRAY + "]";
    }
    
    public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        Player p = (Player)sender;
        ItemStack selector = new ItemStack(Material.BEACON);
        ItemMeta selectorm = selector.getItemMeta();
        selectorm.setDisplayName(ChatColor.translateAlternateColorCodes('&', Main.plugin.config.getString("HatSelector.Name")));
        selector.setItemMeta(selectorm);
        if (cmd.getName().equalsIgnoreCase("hatselector") && args.length == 0) {
            if (!sender.hasPermission("HubMagic.HatSelector.Give")) {
                sender.sendMessage(this.pre
                		+ " You may not use this command.");
            }
            else {
                p.getInventory().addItem(new ItemStack[] { selector });
                p.sendMessage(this.pre + ChatColor.GREEN + " Here's your Hat Selector!");
            }
        }
        else if (args.length == 1) {
            Player t = Bukkit.getPlayer(args[0]);
            if (t.getName() == null) {
                p.sendMessage(this.pre + ChatColor.RED + " Player not found.");
            }
            if (args.length == 1) {
                t.getInventory().addItem(new ItemStack[] { selector });
                t.sendMessage(this.pre + ChatColor.GREEN + " Here's your Hat Selector!");
                p.sendMessage(this.pre + ChatColor.GREEN + args[0].toString() + " has received their Hat Selector!");
            }
        }
        return true;
    }
}
