package me.Archery.hubmagic.Player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import me.Archery.hubmagic.Main;

public class AntiDamage implements Listener
{
    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (Main.plugin.config.getBoolean("Disable.Damage", true) && e.getEntity() instanceof Player) {
            e.setCancelled(true);
        }
    }
}
