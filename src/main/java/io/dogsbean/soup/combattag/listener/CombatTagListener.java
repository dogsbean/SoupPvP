package io.dogsbean.soup.combattag.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CombatTagListener implements Listener {

    private final Map<UUID, Integer> combatTag = new HashMap<>();
    public static final int tagtimer = 30;

    public void setCombatTag(UUID player, int time){
        if(time < 1) {
            combatTag.remove(player);
        }
        else {
            combatTag.put(player, time);
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        combatTag.remove(e.getEntity().getUniqueId());
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        Player victim = (Player) e.getEntity();
        Player attacker = (Player) e.getDamager();

        int tagRemainAT = getCombatTag(attacker.getUniqueId());
        int tagRemainVT = getCombatTag(victim.getUniqueId());

        if (tagRemainAT < 1) {
            attacker.sendMessage(ChatColor.RED + "You are not combat tagged!");
            setCombatTag(attacker.getUniqueId(), tagtimer);
        }

        if (tagRemainVT < 1) {
            victim.sendMessage(ChatColor.RED + "You are not combat tagged!");
            setCombatTag(victim.getUniqueId(), tagtimer);
        }
    }

    @EventHandler
    public void onLeft(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        int tagRemain = getCombatTag(player.getUniqueId());
        if (tagRemain > 1) {
            onJoinWhenTag(player);
        }
    }

    public int getCombatTag(UUID player){
        return combatTag.getOrDefault(player, 0);
    }

    public void onJoinWhenTag(Player player) {
        player.setHealth(0);
    }
}
