package ml.itzblacky.mindustryjda;

import io.anuke.arc.Events;
import io.anuke.arc.util.CommandHandler;
import io.anuke.arc.util.Log;
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
    private Thread jdaThread;

    public Main() {
        path = Vars.modDirectory.absolutePath();
        yaml = new Yaml();
        loadConfig(yaml, path);
        discord = new Discord();

        jdaThread = new Thread(discord::initJDA, "JDA-Thread");
        jdaThread.setUncaughtExceptionHandler((t, e) -> {
            e.printStackTrace();
            Log.info("JDA Failed to load properly!");
        });
        jdaThread.start();

        Events.on(EventType.PlayerChatEvent.class, (event) -> {
            String toSend = getString("mindustry_to_discord_chat_format")
                    .replace("<playername>", event.player.name)
                    .replace("<message>", event.message);
            discord.sendDiscordMessage(toSend);
        });
        Events.on(EventType.PlayerJoin.class, (event) -> {
            String toSend = getString("player_join_message")
                    .replace("<playername>", event.player.name);
            discord.sendDiscordMessage(toSend);
        });
        Events.on(EventType.PlayerLeave.class, (event) -> {
            String toSend = getString("player_leave_message")
                    .replace("<playername>", event.player.name);
            discord.sendDiscordMessage(toSend);
        });
    }

    @Override
    public void registerServerCommands(CommandHandler handler) {

    }

    // Getter and setter for config
    public static Map<String, Object> getConfigMap() {
        return config;
    }

    public static void setConfigMap(Map<String, Object> configMap) {
        config = configMap;
    }

}
