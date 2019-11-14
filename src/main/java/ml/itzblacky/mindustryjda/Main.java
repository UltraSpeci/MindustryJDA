package ml.itzblacky.mindustryjda;

import io.anuke.arc.Events;
import io.anuke.arc.util.CommandHandler;
import io.anuke.mindustry.Vars;
import io.anuke.mindustry.game.EventType;
import io.anuke.mindustry.mod.Mods;
import io.anuke.mindustry.plugin.Plugin;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

import static ml.itzblacky.mindustryjda.Utils.copy;

public class Main extends Plugin {

    private static Map<String, Object> config;
    private String path;
    private Yaml yaml = null;
    private Discord discord;

    public Main() {
        path = Vars.modDirectory.absolutePath();
        yaml = new Yaml();
        loadConfig();
        discord = new Discord();
        discord.initJDA();
        Events.on(EventType.PlayerChatEvent.class, (event) -> {
            discord.sendDiscordMessage(event.message);
        });
    }

    public static Map<String, Object> getConfigMap() {
        return config;
    }

    public static void disablePlugin() {
        for (Mods.LoadedMod mod : Vars.mods.all()) {
            if (mod.name.equals("MindustryJDA")) {
                Vars.mods.setEnabled(mod, false);
            }
        }
    }

    @Override
    public void registerServerCommands(CommandHandler handler) {
        handler.register("printconfig", "prints the config of mindustryjda", (args) -> {
            System.out.println(config);
        });
    }

    private void loadConfig() {
        try {
            config = yaml.load(new FileInputStream(new File(path + "/MindustryJDA/config.yml")));

        } catch (FileNotFoundException e) {
            copy(Main.class.getResourceAsStream("/config.yaml"), path + "/MindustryJDA/config.yml");
            loadConfig();
        }
    }
}
