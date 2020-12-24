package me.Archery.hubmagic.Join;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.Archery.hubmagic.Main;

public class ClearInvOnJoin implements Listener
{
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (!e.getPlayer().hasPermission("HubMagic.InvClear.bypass") && Main.plugin.config.getBoolean("Enable.InvClearOnJoin", true)) {
            e.getPlayer().getInventory().clear();
        }
    }
}
