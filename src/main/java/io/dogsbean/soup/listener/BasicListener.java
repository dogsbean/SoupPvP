package io.dogsbean.soup.listener;

import io.dogsbean.soup.Soup;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class BasicListener implements Listener {

    //Thank you Mr MineHQ
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);
    }
    @EventHandler
    public void onPlayerKick(PlayerKickEvent event) {
        event.setLeaveMessage(null);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
    }

    @EventHandler
    public void onThunderChange(ThunderChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockSpread(BlockSpreadEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onLeavesDecay(LeavesDecayEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockFade(BlockFadeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockForm(BlockFormEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerPortal(PlayerPortalEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (!canBlock(event.getPlayer())) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.RED + "You cannot build here!");
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (!canBlock(event.getPlayer())) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.RED + "You cannot build here!");
        }
    }

    @EventHandler
    public void onPrepareCraft(PrepareItemCraftEvent event) {
        event.getInventory().setResult(null);
    }

    @EventHandler
    public void onCraft(CraftItemEvent event) {
        event.setCancelled(true);
    }

    private boolean canBlock(Player player) {

        boolean isCreative = player.getGameMode() == GameMode.CREATIVE;
        boolean isOp = player.isOp();
        boolean hasPermission = player.hasPermission("soup.build");

        return isCreative && isOp && hasPermission;
    }
    //MineHQ Ends here

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        Player player = e.getPlayer();
        Location spawn = new Location(player.getLocation().getWorld(), Soup.getInstance().getConfig().getDouble("WORLD.SPAWN.X"), Soup.getInstance().getConfig().getDouble("WORLD.SPAWN.Y"), Soup.getInstance().getConfig().getDouble("WORLD.SPAWN.Z"), Soup.getInstance().getConfig().getInt("WORLD,SPAWN,YAW"), Soup.getInstance().getConfig().getInt("WORLD.SPAWN.PITCH"));
        player.teleport(spawn);
    }
}
