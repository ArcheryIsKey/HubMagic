package me.Archery.hubmagic;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import me.Archery.hubmagic.Commands.FlyingWingsCMD;
import me.Archery.hubmagic.Commands.HatSelectorCMD;
import me.Archery.hubmagic.Commands.InvisibilityClockCMD;
import me.Archery.hubmagic.Commands.ParticleGunCMD;
import me.Archery.hubmagic.Commands.PluginCmds;
import me.Archery.hubmagic.Commands.SpeedStickCMD;
import me.Archery.hubmagic.Commands.TeleportGunCMD;
import me.Archery.hubmagic.Commands.ToggleWalkingParticles;
import me.Archery.hubmagic.Commands.WardrobeCMD;
import me.Archery.hubmagic.Gadgets.FlyingWings;
import me.Archery.hubmagic.Gadgets.HatSelector;
import me.Archery.hubmagic.Gadgets.InvisibilityClock;
import me.Archery.hubmagic.Gadgets.ParticleGun;
import me.Archery.hubmagic.Gadgets.SpeedStick;
import me.Archery.hubmagic.Gadgets.TeleportGun;
import me.Archery.hubmagic.Gadgets.Wardrobe;
import me.Archery.hubmagic.Join.AdventureJoin;
import me.Archery.hubmagic.Join.AntiJoinLeaveMsgs;
import me.Archery.hubmagic.Join.ClearInvOnJoin;
import me.Archery.hubmagic.Join.JoinActionbar;
import me.Archery.hubmagic.Join.JoinBossBar;
import me.Archery.hubmagic.Join.JoinFirework;
import me.Archery.hubmagic.Join.JoinSound;
import me.Archery.hubmagic.Join.JoinTitle;
import me.Archery.hubmagic.Player.AntiChat;
import me.Archery.hubmagic.Player.AntiDamage;
import me.Archery.hubmagic.Player.CancelInventoryItemMove;
import me.Archery.hubmagic.Player.HealthFoodLoss;
import me.Archery.hubmagic.Player.WalkingParticles;
import me.Archery.hubmagic.World.AntiBreak;
import me.Archery.hubmagic.World.AntiDrop;
import me.Archery.hubmagic.World.AntiGrab;
import me.Archery.hubmagic.World.AntiMobSpawn;
import me.Archery.hubmagic.World.AntiPlace;
import me.Archery.hubmagic.World.AntiWeather;

public class HubMagic extends JavaPlugin
{
    public FileConfiguration config;
    public Inventory select;
    public Inventory ward;
    public ArrayList<UUID> walkingparticles;
    public ArrayList<UUID> flyingwings;
    public ArrayList<UUID> invis;
    public ArrayList<UUID> haveCooldownsParticleGun;
    public ArrayList<UUID> haveCooldownsTeleportGun;
    public ArrayList<UUID> haveCooldownsSpeedStick;
    public ArrayList<Projectile> arrow;
    public static HubMagic plugin;
    
    public HubMagic() {
        this.config = this.getConfig();
        this.select = Bukkit.createInventory(null, 27, ChatColor.translateAlternateColorCodes('&', ChatColor.translateAlternateColorCodes('&', this.config.getString("HatSelector.GUIName"))));
        this.ward = Bukkit.createInventory(null, 36, ChatColor.translateAlternateColorCodes('&', ChatColor.translateAlternateColorCodes('&', this.config.getString("Wardrobe.GUIName"))));
        this.walkingparticles = new ArrayList<>();
        this.flyingwings = new ArrayList<>();
        this.invis = new ArrayList<>();
        this.haveCooldownsParticleGun = new ArrayList<>();
        this.haveCooldownsTeleportGun = new ArrayList<>();
        this.haveCooldownsSpeedStick = new ArrayList<>();
    }
    
    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.reloadConfig();
        Bukkit.getServer().getPluginManager().registerEvents(new AntiBreak(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new AntiPlace(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new AntiGrab(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new AntiDrop(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new AntiChat(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new AntiJoinLeaveMsgs(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new AntiMobSpawn(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new AntiWeather(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new JoinSound(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new HealthFoodLoss(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new CancelInventoryItemMove(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new ClearInvOnJoin(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new WalkingParticles(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new JoinFirework(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new JoinActionbar(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new JoinTitle(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new JoinBossBar(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new TeleportGun(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new AdventureJoin(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new ParticleGun(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new SpeedStick(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new InvisibilityClock(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new HatSelector(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Wardrobe(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new FlyingWings(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new AntiDamage(), this);
        this.getCommand("particlegun").setExecutor(new ParticleGunCMD());
        this.getCommand("speedstick").setExecutor(new SpeedStickCMD());
        this.getCommand("teleportgun").setExecutor(new TeleportGunCMD());
        this.getCommand("invisibilityclock").setExecutor(new InvisibilityClockCMD());
        this.getCommand("walkingparticles").setExecutor(new ToggleWalkingParticles());
        this.getCommand("hatselector").setExecutor(new HatSelectorCMD());
        this.getCommand("wardrobe").setExecutor(new WardrobeCMD());
        this.getCommand("flyingwings").setExecutor(new FlyingWingsCMD());
        this.getCommand("hubmagic").setExecutor(new PluginCmds());
        this.select.setItem(0, new ItemStack(Material.BEACON));
        this.select.setItem(1, new ItemStack(Material.TNT));
        this.select.setItem(2, new ItemStack(Material.PUMPKIN));
        this.select.setItem(3, new ItemStack(Material.BLACK_WOOL));
        this.select.setItem(4, new ItemStack(Material.BLACK_BANNER));
        this.select.setItem(5, new ItemStack(Material.BRICK));
        this.select.setItem(6, new ItemStack(Material.FURNACE_MINECART));
        this.select.setItem(7, new ItemStack(Material.FURNACE));
        this.select.setItem(8, new ItemStack(Material.LAPIS_BLOCK));
        this.select.setItem(9, new ItemStack(Material.DIAMOND_BLOCK));
        this.select.setItem(10, new ItemStack(Material.QUARTZ_BLOCK));
        this.select.setItem(11, new ItemStack(Material.EMERALD_BLOCK));
        this.select.setItem(12, new ItemStack(Material.IRON_BLOCK));
        this.select.setItem(13, new ItemStack(Material.COAL_BLOCK));
        this.select.setItem(14, new ItemStack(Material.STONE));
        this.select.setItem(15, new ItemStack(Material.END_PORTAL_FRAME));
        this.select.setItem(16, new ItemStack(Material.CRAFTING_TABLE));
        this.select.setItem(17, new ItemStack(Material.GLOWSTONE));
        this.select.setItem(18, new ItemStack(Material.COBWEB));
        this.select.setItem(19, new ItemStack(Material.COMMAND_BLOCK));
        this.select.setItem(20, new ItemStack(Material.JACK_O_LANTERN));
        this.select.setItem(21, new ItemStack(Material.BRICK));
        this.select.setItem(22, new ItemStack(Material.DRAGON_EGG));
        this.select.setItem(23, new ItemStack(Material.PISTON));
        this.select.setItem(24, new ItemStack(Material.MYCELIUM));
        this.select.setItem(25, new ItemStack(Material.SEA_LANTERN));
        this.select.setItem(26, new ItemStack(Material.REDSTONE_BLOCK));
        this.ward.setItem(2, new ItemStack(Material.LEATHER_HELMET));
        this.ward.setItem(11, new ItemStack(Material.LEATHER_CHESTPLATE));
        this.ward.setItem(20, new ItemStack(Material.LEATHER_LEGGINGS));
        this.ward.setItem(29, new ItemStack(Material.LEATHER_BOOTS));
        this.ward.setItem(3, new ItemStack(Material.GOLDEN_HELMET));
        this.ward.setItem(12, new ItemStack(Material.GOLDEN_CHESTPLATE));
        this.ward.setItem(21, new ItemStack(Material.GOLDEN_LEGGINGS));
        this.ward.setItem(30, new ItemStack(Material.GOLDEN_BOOTS));
        this.ward.setItem(4, new ItemStack(Material.CHAINMAIL_HELMET));
        this.ward.setItem(13, new ItemStack(Material.CHAINMAIL_CHESTPLATE));
        this.ward.setItem(22, new ItemStack(Material.CHAINMAIL_LEGGINGS));
        this.ward.setItem(31, new ItemStack(Material.CHAINMAIL_BOOTS));
        this.ward.setItem(5, new ItemStack(Material.IRON_HELMET));
        this.ward.setItem(14, new ItemStack(Material.IRON_CHESTPLATE));
        this.ward.setItem(23, new ItemStack(Material.IRON_LEGGINGS));
        this.ward.setItem(32, new ItemStack(Material.IRON_BOOTS));
        this.ward.setItem(6, new ItemStack(Material.DIAMOND_HELMET));
        this.ward.setItem(15, new ItemStack(Material.DIAMOND_CHESTPLATE));
        this.ward.setItem(24, new ItemStack(Material.DIAMOND_LEGGINGS));
        this.ward.setItem(33, new ItemStack(Material.DIAMOND_BOOTS));
        ItemStack clear = new ItemStack(Material.BARRIER);
        ItemMeta clearm = clear.getItemMeta();
        clearm.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&4&lClear Wardrobe"));
        clear.setItemMeta(clearm);
        this.ward.setItem(35, clear);
        HubMagic.plugin = this;
    }
    
    @Override
    public void onDisable() {
        HubMagic.plugin = null;
        
    }
}
