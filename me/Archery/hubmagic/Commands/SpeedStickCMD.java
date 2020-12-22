
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

public class SpeedStickCMD implements CommandExecutor
{
    String pre;
    
    public SpeedStickCMD() {
        this.pre = ChatColor.DARK_GRAY + "[" + ChatColor.AQUA + "HubMagic" + ChatColor.DARK_GRAY + "]";
    }
    
    public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
        Player p = (Player)sender;
        ItemStack boot = new ItemStack(Material.STICK);
        ItemMeta bootmeta = boot.getItemMeta();
        bootmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Main.plugin.config.getString("SpeedStick.Name")));
        boot.setItemMeta(bootmeta);
        if (cmd.getName().equalsIgnoreCase("speedstick") && args.length == 0) {
            if (!sender.hasPermission("HubMagic.SpeedStick.Give")) {
                sender.sendMessage(this.pre + " You may not use this command.");
            }
            else {
                p.getInventory().addItem(new ItemStack[] { boot });
                p.sendMessage(this.pre + ChatColor.GREEN + " Here's your Speed Stick!");
            }
        }
        else if (args.length == 1) {
            Player t = Bukkit.getPlayer(args[0]);
            if (t.getName() == null) {
                p.sendMessage(this.pre + ChatColor.RED + " Player not found.");
            }
            if (args.length == 1) {
                t.getInventory().addItem(new ItemStack[] { boot });
                t.sendMessage(this.pre + ChatColor.GREEN + " Here's your SpeedStick!");
                p.sendMessage(this.pre + ChatColor.GREEN + args[0].toString() + " has received their SpeedStick!");
            }
        }
        return true;
    }
}
