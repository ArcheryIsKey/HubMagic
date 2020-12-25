package me.Archery.hubmagic.World;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import me.Archery.hubmagic.HubMagic;

public class AntiMobSpawn implements Listener
{
    
    @EventHandler
    public void onMobSpawn(CreatureSpawnEvent e) {
        if (HubMagic.getInstance().config.getBoolean("Disable.MobSpawn", true)) {
            e.setCancelled(true);
        }
    }
}
