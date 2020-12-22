
package me.Archery.hubmagic.Player;

import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.Archery.hubmagic.Main;

public class WalkingParticles implements Listener
{
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if ((e.getFrom().getX() != e.getTo().getX() || e.getFrom().getZ() != e.getTo().getZ()) && Main.plugin.config.getBoolean("Enable.WalkingParticles", true) && p.hasPermission("HubMagic.WalkingParticles.Use") && !Main.plugin.invis.contains(p.getUniqueId()) && !Main.plugin.walkingparticles.contains(p.getUniqueId())) {
        	p.getLocation().getWorld().spawnParticle(Particle.LAVA, p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), 4);
        }
    }
}