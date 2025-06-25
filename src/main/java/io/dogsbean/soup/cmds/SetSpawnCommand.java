package io.dogsbean.soup.cmds;

import io.dogsbean.soup.Soup;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

public class SetSpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            FileConfiguration config = Soup.getInstance().getConfig();
            Player player = (Player) sender;
            Location l = player.getLocation();
            String world = l.getWorld().getName();
            double x = l.getX();
            double y = l.getY();
            double z = l.getZ();
            float yaw = l.getYaw();
            float pitch = l.getPitch();
            config.set("WORLD.SPAWN.WORLD", world);
            config.set("WORLD.SPAWN.X", x);
            config.set("WORLD.SPAWN.Y", y);
            config.set("WORLD.SPAWN.Z", z);
            config.set("WORLD.SPAWN.YAW", yaw);
            config.set("WORLD.SPAWN.PITCH", pitch);
            Soup.getInstance().saveConfig();
            Soup.getInstance().reloadConfig();
            sender.sendMessage(ChatColor.GREEN + "Spawn point updated!");
        }
        else if(sender instanceof ConsoleCommandSender) {
            sender.sendMessage("This is Player-Only command!");
            return false;
        }
        return false;
    }
}
