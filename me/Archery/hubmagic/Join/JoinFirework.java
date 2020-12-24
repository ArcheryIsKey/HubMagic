package me.Archery.hubmagic.Join;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.meta.FireworkMeta;

import me.Archery.hubmagic.Main;

public class JoinFirework implements Listener
{
    @EventHandler
    public void onJoin(final PlayerJoinEvent e) {
        final Firework f = (Firework) e.getPlayer().getWorld().spawnEntity(e.getPlayer().getLocation(), EntityType.FIREWORK);
        final FireworkMeta fm = f.getFireworkMeta();
        if (Main.plugin.config.getBoolean("Enable.JoinFirework", true) && e.getPlayer().hasPermission("HubMagic.JoinFirework.Use")) {
            fm.addEffect(FireworkEffect.builder().flicker(false).trail(true).with(FireworkEffect.Type.CREEPER).withColor(Color.ORANGE).withFade(Color.GREEN).withTrail().build());
            fm.setPower(2);
            f.setFireworkMeta(fm);
        }
    }
}
