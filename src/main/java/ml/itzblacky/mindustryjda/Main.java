package ml.itzblacky.mindustryjda;

import io.anuke.arc.Events;
import io.anuke.arc.util.CommandHandler;
import io.anuke.mindustry.Vars;
import io.anuke.mindustry.game.EventType;
import io.anuke.mindustry.plugin.Plugin;
import org.yaml.snakeyaml.Yaml;

import java.util.Map;

import static ml.itzblacky.mindustryjda.Utils.ConfigUtils.getString;
import static ml.itzblacky.mindustryjda.Utils.Utils.loadConfig;

public class Main extends Plugin {

    private static Map<String, Object> config;
    private String path;
    private Yaml yaml = null;
    private Discord discord;

    public Main() {
        path = Vars.modDirectory.absolutePath();
        yaml = new Yaml();
        loadConfig(yaml, path);
        discord = new Discord();
        discord.initJDA();
        Events.on(EventType.PlayerChatEvent.class, (event) -> {
            discord.sendDiscordMessage(getString("mindustry_to_discord_chat_format")
                    .replace("<playername>", event.player.name)
                    .replace("<message>", event.message));
        });
    }

    // Getter and setter for config
    public static Map<String, Object> getConfigMap() {
        return config;
    }

    public static void setConfigMap(Map<String, Object> configMap) {
        config = configMap;
    }


    @Override
    public void registerServerCommands(CommandHandler handler) {
        handler.register("printconfig", "prints the config of mindustryjda", (args) -> {
            System.out.println(config);
        });
    }


}
