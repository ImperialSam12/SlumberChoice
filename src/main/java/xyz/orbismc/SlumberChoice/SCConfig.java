package xyz.orbismc.SlumberChoice;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class SCConfig {

    private final static SCConfig instance = new SCConfig();

    private File file;
    private YamlConfiguration config;

    private SCConfig() {
    }

    public void load() {
        file = new File(SlumberChoice.getInstance().getDataFolder(), "config.yml");

        if (!file.exists()) {
            SlumberChoice.getInstance().saveResource("config.yml", false);
        }

        config = new YamlConfiguration();
        config.options().parseComments(true);

        try {
            config.load(file);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static SCConfig getInstance() {
        return instance;
    }
}