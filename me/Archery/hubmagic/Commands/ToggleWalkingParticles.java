package me.Archery.hubmagic.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Archery.hubmagic.Main;

public class ToggleWalkingParticles implements CommandExecutor
{
    public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        Player p = (Player)sender;
        if (Main.plugin.config.getBoolean("Enable.WalkingParticles", true) && p.hasPermission("HubMagic.WalkingParticles.Toggle")) {
            if (!Main.plugin.walkingparticles.contains(p.getUniqueId())) {
                p.sendMessage(ChatColor.RED + " You've disabled walking particles!");
                Main.plugin.walkingparticles.add(p.getUniqueId());
            }
            else {
                Main.plugin.walkingparticles.remove(p.getUniqueId());
                p.sendMessage(ChatColor.GREEN + " You've enabled walking particles!");
            }
        }
        return true;
    }
}
