package me.Archery.hubmagic.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import me.Archery.hubmagic.HubMagic;

public class HealthFoodLoss implements Listener
{
    @EventHandler
    public void onFoodLoss(FoodLevelChangeEvent e) {
        if (HubMagic.getInstance().config.getBoolean("Disable.FoodLoss", true)) {
            e.setCancelled(true);
        }
    }
}
