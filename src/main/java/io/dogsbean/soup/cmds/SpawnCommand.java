package io.dogsbean.soup.cmds;

import io.dogsbean.soup.Soup;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Location spawn = new Location(player.getLocation().getWorld(), Soup.getInstance().getConfig().getDouble("WORLD.SPAWN.X"), Soup.getInstance().getConfig().getDouble("WORLD.SPAWN.Y"), Soup.getInstance().getConfig().getDouble("WORLD.SPAWN.Z"), Soup.getInstance().getConfig().getInt("WORLD,SPAWN,YAW"), Soup.getInstance().getConfig().getInt("WORLD.SPAWN.PITCH"));
            player.teleport(spawn);
        } else if(sender instanceof ConsoleCommandSender) {
            sender.sendMessage("This is Player-Only command!");
            return false;
        }
        return false;
    }
}
