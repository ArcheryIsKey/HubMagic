package me.Archery.hubmagic.Player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import me.Archery.hubmagic.HubMagic;

public class AntiDamage implements Listener
{
    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (HubMagic.getInstance().config.getBoolean("Disable.Damage", true) && e.getEntity() instanceof Player) {
            e.setCancelled(true);
        }
    }
}
