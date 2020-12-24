package me.Archery.hubmagic.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PluginCmds implements CommandExecutor
{
    
    public boolean onCommand(CommandSender sender, Command cmd, String commandlabel, String[] args) {
        if (!sender.hasPermission("hubmagic.helsender")) {
            sender.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.AQUA + "HubMagic" + ChatColor.DARK_GRAY + "]" + " You cannot use this command.");
            return true;
        }
        sender.sendMessage(ChatColor.YELLOW + "****************************************************************");
        sender.sendMessage(ChatColor.LIGHT_PURPLE + "/hubmagic" + ChatColor.DARK_AQUA + " See this help page.");
        sender.sendMessage(ChatColor.LIGHT_PURPLE + "/particlegun" + ChatColor.DARK_AQUA + " Give yourself a ParticleGun.");
        sender.sendMessage(ChatColor.LIGHT_PURPLE + "/teleportgun" + ChatColor.DARK_AQUA + " Give yourself a TeleportGun.");
        sender.sendMessage(ChatColor.LIGHT_PURPLE + "/speedstick" + ChatColor.DARK_AQUA + " Give yourself a SpeedStick.");
        sender.sendMessage(ChatColor.LIGHT_PURPLE + "/hatselector" + ChatColor.DARK_AQUA + " Give yourself a Hat Selector");
        sender.sendMessage(ChatColor.LIGHT_PURPLE + "/invisibilityclock" + ChatColor.DARK_AQUA + " Give yourself an Invisibility Clock.");
        sender.sendMessage(ChatColor.LIGHT_PURPLE + "/flyingwings" + ChatColor.DARK_AQUA + " Give yourself Flying Wings.");
        sender.sendMessage(ChatColor.LIGHT_PURPLE + "/wardrobe" + ChatColor.DARK_AQUA + " Show the wardrobe.");
        sender.sendMessage(ChatColor.LIGHT_PURPLE + "/walkingparticles" + ChatColor.DARK_AQUA + " Toggle walking particles.");
        return true;
    }
}
