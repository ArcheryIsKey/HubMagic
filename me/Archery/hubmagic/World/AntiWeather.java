package me.Archery.hubmagic.World;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

import me.Archery.hubmagic.HubMagic;

public class AntiWeather implements Listener
{
    @EventHandler(priority = EventPriority.NORMAL)
    public void onRainStart(WeatherChangeEvent event) {
        if (HubMagic.plugin.config.getBoolean("Disable.Weather", true)) {
            event.setCancelled(true);
        }
    }
}
