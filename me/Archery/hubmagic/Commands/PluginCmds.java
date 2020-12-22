package me.Archery.hubmagic.Commands;

import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;

public class PluginCmds implements CommandExecutor
{
    String pre;
    
    public PluginCmds() {
        this.pre = ChatColor.DARK_GRAY + "[" + ChatColor.AQUA + "HubMagic" + ChatColor.DARK_GRAY + "]";
    }
    
    public boolean onCommand(CommandSender sender, Command cmd, String commandlabel, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        Player p = (Player)sender;
        if (!cmd.getName().equalsIgnoreCase("hubmagic")) {
            return true;
        }
        if (!p.hasPermission("hubmagic.help")) {
            p.sendMessage(this.pre + " You cannot use this command.");
            return true;
        }
        p.sendMessage(ChatColor.YELLOW + "****************************************************************");
        p.sendMessage(ChatColor.LIGHT_PURPLE + "/hubmagic" + ChatColor.DARK_AQUA + " See this help page.");
        p.sendMessage(ChatColor.LIGHT_PURPLE + "/particlegun" + ChatColor.DARK_AQUA + " Give yourself a ParticleGun.");
        p.sendMessage(ChatColor.LIGHT_PURPLE + "/teleportgun" + ChatColor.DARK_AQUA + " Give yourself a TeleportGun.");
        p.sendMessage(ChatColor.LIGHT_PURPLE + "/speedstick" + ChatColor.DARK_AQUA + " Give yourself a SpeedStick.");
        p.sendMessage(ChatColor.LIGHT_PURPLE + "/hatselector" + ChatColor.DARK_AQUA + " Give yourself a Hat Selector");
        p.sendMessage(ChatColor.LIGHT_PURPLE + "/invisibilityclock" + ChatColor.DARK_AQUA + " Give yourself an Invisibility Clock.");
        p.sendMessage(ChatColor.LIGHT_PURPLE + "/flyingwings" + ChatColor.DARK_AQUA + " Give yourself Flying Wings.");
        p.sendMessage(ChatColor.LIGHT_PURPLE + "/wardrobe" + ChatColor.DARK_AQUA + " Show the wardrobe.");
        p.sendMessage(ChatColor.LIGHT_PURPLE + "/walkingparticles" + ChatColor.DARK_AQUA + " Toggle walking particles.");
        return true;
    }
}
