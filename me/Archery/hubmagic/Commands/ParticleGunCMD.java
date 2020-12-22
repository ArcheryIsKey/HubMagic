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

public class ParticleGunCMD implements CommandExecutor
{
    String pre;
    
    public ParticleGunCMD() {
        this.pre = ChatColor.DARK_GRAY + "[" + ChatColor.AQUA + "HubMagic" + ChatColor.DARK_GRAY + "]";
    }
    
    public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
        Player p = (Player)sender;
        ItemStack gun = new ItemStack(Material.getMaterial("DIAMOND_HORSE_ARMOR"));
        ItemMeta gunmeta = gun.getItemMeta();
        gunmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Main.plugin.config.getString("ParticleGun.Name")));
        gun.setItemMeta(gunmeta);
        if (cmd.getName().equalsIgnoreCase("particlegun") && args.length == 0) {
            if (!sender.hasPermission("HubMagic.ParticleGun.Give")) {
                sender.sendMessage(this.pre + " You may not use this command.");
            }
            else {
                p.getInventory().addItem(new ItemStack[] { gun });
                p.sendMessage(this.pre + ChatColor.GREEN + " Here's your Particle Gun!");
            }
        }
        else if (args.length == 1) {
            Player t = Bukkit.getPlayer(args[0]);
            if (t.getName() == null) {
                p.sendMessage(this.pre + ChatColor.RED + " Player not found.");
            }
            if (args.length == 1) {
                t.getInventory().addItem(new ItemStack[] { gun });
                t.sendMessage(this.pre + ChatColor.GREEN + " Here's your Particle Gun!");
                p.sendMessage(this.pre + ChatColor.GREEN + args[0].toString() + " has received their Particle Gun!");
            }
        }
        return true;
    }
}
