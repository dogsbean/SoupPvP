package io.dogsbean.soup.listener;

import io.dogsbean.soup.Soup;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class RefillListener implements Listener {
    private final CooldownListener cooldownListener = new CooldownListener();

    @EventHandler
    public void onClickSign(PlayerInteractEvent e) {
        if (!isRightClickSign(e)) return;

        Sign sign = (Sign) e.getClickedBlock().getState();
        if (!ChatColor.stripColor(sign.getLine(1)).equals("[Soup]")) return;

        Player player = e.getPlayer();
        int cooldown = cooldownListener.getCoolDown(player.getUniqueId());

        if (cooldown > 0) {
            player.sendMessage(ChatColor.RED + String.valueOf(cooldown) + " seconds left to use again.");
            return;
        }

        e.setCancelled(true);
        openSoupRefill(player);
        startCooldown(player);
    }

    private boolean isRightClickSign(PlayerInteractEvent e) {
        return e.getAction().name().contains("RIGHT_CLICK_BLOCK")
                && e.getClickedBlock().getType().name().contains("SIGN")
                && e.getClickedBlock().getState() instanceof Sign;
    }

    private void openSoupRefill(Player player) {
        Inventory inv = Soup.getInstance().getServer().createInventory(null, 54, ChatColor.YELLOW + "Soup Refill");

        ItemStack soup = new ItemStack(Material.MUSHROOM_SOUP);
        for (int i = 0; i < 54; i++) inv.setItem(i, soup);

        player.openInventory(inv);
    }

    private void startCooldown(Player player) {
        cooldownListener.setCoolDown(player.getUniqueId(), 10);

        new BukkitRunnable() {
            @Override
            public void run() {
                int time = cooldownListener.getCoolDown(player.getUniqueId()) - 1;
                cooldownListener.setCoolDown(player.getUniqueId(), time);
                if (time <= 0) this.cancel();
            }
        }.runTaskTimer(Soup.getInstance(), 20, 20);
    }
}