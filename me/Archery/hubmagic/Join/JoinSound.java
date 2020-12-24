package me.Archery.hubmagic.Join;

import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.Archery.hubmagic.Main;

public class JoinSound implements Listener
{
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (Main.plugin.config.getBoolean("Enable.JoinSound", true)) {
            e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.valueOf(Main.plugin.config.getString("JoinSound.Sound")), (float)Main.plugin.config.getLong("JoinSound.Volume"), (float)Main.plugin.config.getDouble("JoinSound.Pitch"));
        }
    }
}
