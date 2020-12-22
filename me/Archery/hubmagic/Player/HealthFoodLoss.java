
package me.Archery.hubmagic.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import me.Archery.hubmagic.Main;

public class HealthFoodLoss implements Listener
{
    @EventHandler
    public void onFoodLoss(FoodLevelChangeEvent e) {
        if (Main.plugin.config.getBoolean("Disable.FoodLoss", true)) {
            e.setCancelled(true);
        }
    }
}
