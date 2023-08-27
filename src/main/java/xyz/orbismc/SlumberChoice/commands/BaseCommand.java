package xyz.orbismc.SlumberChoice.commands;

import xyz.orbismc.SlumberChoice;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class BaseCommand implements CommandExecutor {

    private int nightSkipCounter = 0; // Initialize the counter
    private Set<UUID> playersWhoUsedCommand = new HashSet<>(); // Keep track of players who used the command

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("slumberchoice")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("This command can only be run by a player.");
            } else {
                Player player = (Player) sender;

                if (args.length == 1 && args[0].equalsIgnoreCase("skipnight")) {
                    if (isNightTime(player.getWorld().getTime())) {
                        if (!playersWhoUsedCommand.contains(player.getUniqueId())) {
                            handleSkipNightCommand(player);
                            playersWhoUsedCommand.add(player.getUniqueId());
                        } else {
                            player.sendMessage("You have already used /skipnight tonight.");
                        }
                    } else {
                        player.sendMessage("You can only use /skipnight during the night!");
                    }
                } else {
                    sender.sendMessage("Players needed: " + calculatePlayersNeeded());
                }
            }
            return true;
        }
        return false;
    }

    private void handleSkipNightCommand(Player player) {
        if (nightSkipCounter >= calculatePlayersNeeded()) {
            player.getWorld().setTime(0); // Set time to day
            player.sendMessage("Night skipped!");
            nightSkipCounter = 0; // Reset the counter
            playersWhoUsedCommand.clear(); // Clear the set
        } else {
            nightSkipCounter++;
            player.sendMessage("Night skip counter increased. " + (calculatePlayersNeeded() - nightSkipCounter) + " players needed to skip the night.");
        }
    }

    private boolean isNightTime(long worldTime) {
        return worldTime >= 13000 && worldTime <= 23000;
    }
}