package me.Archery.hubmagic.Join;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.Archery.hubmagic.HubMagic;

public class AntiJoinLeaveMsgs implements Listener
{
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (HubMagic.getInstance().config.getBoolean("Disable.JoinMsgs", true)) {
            e.setJoinMessage("");
        }
    }
    
    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if (HubMagic.getInstance().config.getBoolean("Disable.QuitMsgs", true)) {
            e.setQuitMessage("");
        }
    }
}
