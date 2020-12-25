package me.Archery.hubmagic.Join;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.Archery.hubmagic.HubMagic;

public class AdventureJoin implements Listener
{
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (!e.getPlayer().hasPermission("HubMagic.AdventureJoin.bypass") && HubMagic.getInstance().config.getBoolean("Enable.AdventureOnJoin", true)) {
            e.getPlayer().setGameMode(GameMode.ADVENTURE);
        }
    }
}
