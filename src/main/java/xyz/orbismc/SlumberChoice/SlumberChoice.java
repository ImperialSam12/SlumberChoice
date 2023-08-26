package xyz.orbismc.SlumberChoice;

import org.bukkit.bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import com.palmergames.bukkit.util.Colors;


/* 
The following work is under the full ownership of the OrbisMC Dev Team
Liscened under the GNU General Public Liscense.
All credits listed in the attached plugin.yml.
*/


public class SlumberChoice extends JavaPlugin {

    public static final String VERSION = "SlumberChoice Alpha 1.0"
    public static final String REPO = "https://github.com/ImperialSam12/SlumberChoice"

    private void slumberChoiceASCIIArt {
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("(~|    _ _ |_  _  _/~`|_  _ . _ _ ");
        Bukkit.getConsoleSender().sendMessage("_)||_|| | ||_)(/_| \_,| |(_)|(_(/_");
        Bukkit.getConsoleSender().sendMessage("==================================");
        Bukkit.getConsoleSender().sendMessage(          "By Imperial_Sam"         );
        Bukkit.getConsoleSender().sendMessage("=================================="); 
        Bukkit.getConsoleSender().sendMessage(REPO);

    private void pluginCrash {
        Bukkit.getConsoleSender().sendMessage(Colors.Red + "Uh oh!" + VERSION + "has crashed!" )

    }

    }
}