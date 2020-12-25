package me.Archery.hubmagic.Join;

import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.Archery.hubmagic.HubMagic;

public class JoinSound implements Listener
{
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (HubMagic.getInstance().config.getBoolean("Enable.JoinSound", true)) {
            e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.valueOf(HubMagic.getInstance().config.getString("JoinSound.Sound")), (float)HubMagic.getInstance().config.getLong("JoinSound.Volume"), (float)HubMagic.getInstance().config.getDouble("JoinSound.Pitch"));
        }
    }
}
