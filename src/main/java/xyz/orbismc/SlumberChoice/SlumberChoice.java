package xyz.orbismc.SlumberChoice;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import com.palmergames.bukkit.util.Colors;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.logging.Level;
import java.io.File;

import org.apache.commons.lang3.StringUtils;


/* 
The following work is under the full ownership of the OrbisMC Dev Team
Liscened under the GNU General Public Liscense.
All credits listed in the attached plugin.yml.
*/


public class SlumberChoice extends JavaPlugin {

    public static final String VERSION = "SlumberChoice Alpha 1.0";
    public static final String REPO = "https://github.com/ImperialSam12/SlumberChoice";

    private void slumberChoiceASCIIArt() {
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("(~|    _ _ |_  _  _/~`|_  _ . _ _ ");
        Bukkit.getConsoleSender().sendMessage("_)||_|| | ||_)(/_| \_,| |(_)|(_(/_");
        Bukkit.getConsoleSender().sendMessage("==================================");
        Bukkit.getConsoleSender().sendMessage("          By Imperial_Sam         ");
        Bukkit.getConsoleSender().sendMessage("=================================="); 
        Bukkit.getConsoleSender().sendMessage(REPO);
    }

    private void pluginCrash() {
        Bukkit.getConsoleSender().sendMessage(Colors.Red + "Uh oh!" + VERSION + " has crashed!" );
        Bukkit.getPluginManager().disablePlugin(this);
    }

    private void pluginFail() {
        if (VERSION.contains("Alpha")) {
            Bukkit.getConsoleSender().sendMessage(Colors.Red + "SlumberChoice has failed to initialize.");
            Bukkit.getPluginManager().disablePlugin(this);
        } else if (VERSION.contains("Dev")) {
            Bukkit.getConsoleSender().sendMessage(Colors.Red + "SlumberChoice has failed to initialize.");
            Bukkit.getPluginManager().disablePlugin(this);

        } else {
            Bukkit.getConsoleSender().sendMessage(Colors.Red + "You have disabled SlumberChoice in your config.yml, please re-enable it in order for the plugin to function.");
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    public int calculatePlayersNeeded() {
        FileConfiguration config = this.getConfig();
        
        // Get the vote percentage from config.yml
        double votePercentage = config.getDouble("settings.vote-percentage", 50.0);

        // Get the total online players
        int totalPlayers = getServer().getOnlinePlayers().size();

        // Calculate the number of players needed based on the vote percentage
        int playersNeeded = (int) Math.ceil((votePercentage / 100.0) * totalPlayers);

        return playersNeeded;
    }

    public void onEnable() {
        SCConfig.getInstance().load(); // Load the config
        Objects.requireNonNull(getCommand("slumberchoice")).setExecutor(new BaseCommand());
        Objects.requireNonNull(getCommand("skipnight")).setExecutor(new BaseCommand());
        String storedVersion = config.getString("information.version");
        boolean isEnabled = config.getBoolean("important.is-enabled");
        if (!VERSION.equals(storedVersion)) {
            config.set("information.version", VERSION);
        }
        saveConfig(); // Save the updated configuration
        if (isEnabled) {
            Bukkit.getConsoleSender().sendMessage(Colors.Green + VERSION + " has been enabled!");
            slumberChoiceASCIIArt();
            
            int votesRequired = calculateVotesNeeded();
            Bukkit.getConsoleSender().sendMessage("Votes Required to Skip Night: " + votesRequired);
        } else {
            pluginFail();
        }
    }

    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(Colors.LightPurple + VERSION + " has been disabled!");
        slumberChoiceASCIIArt();
    }
}