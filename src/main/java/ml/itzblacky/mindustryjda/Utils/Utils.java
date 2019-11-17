package ml.itzblacky.mindustryjda.Utils;

import io.anuke.arc.util.Log;
import io.anuke.mindustry.gen.Call;
import ml.itzblacky.mindustryjda.Main;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

import static ml.itzblacky.mindustryjda.Main.setConfigMap;

public class Utils {

    public static boolean copy(InputStream source, String destination) {
        boolean succeess = true;
        File dir = new File(new File(destination).getParentFile().getAbsolutePath());
        if (!dir.exists()) {
            dir.mkdir();
        }
        try {
            Files.copy(source, Paths.get(destination), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            ex.printStackTrace();
            succeess = false;
        }

        return succeess;

    }

    public static void loadConfig(Yaml yaml, String path) {
        try {
            Map<String, Object> config = yaml.load(new FileInputStream(new File(path + "/MindustryJDA/config.yml")));
            Map<String, Object> defaultConfig = loadDefaultConfig(yaml);
            for (Map.Entry<String, Object> entry : defaultConfig.entrySet()) {
                Object o = config.getOrDefault(entry.getKey(), null);
                if (o == null) {
                    Log.info("Using default value for " + entry.getKey() + " To fix this, regenerate your config file!");
                    config.put(entry.getKey(), entry.getValue());
                }
            }
            setConfigMap(config);
        } catch (FileNotFoundException e) {
            copy(Main.class.getResourceAsStream("/config.yaml"), path + "/MindustryJDA/config.yml");
            loadConfig(yaml, path);
        }
    }

    public static Map<String, Object> loadDefaultConfig(Yaml yaml) {
        Map<String, Object> toReturn = new HashMap<>();
        try {
            toReturn = yaml.load(Main.class.getResourceAsStream("/config.yaml"));
        } catch (NullPointerException e) {
            Log.info("Unknown error #51, Please report this to the author of the plugin!");
        }
        return toReturn;
    }
    public static void sendMindustryMessage(String message) {
        Call.sendMessage(message);
    }

    public static boolean isCommand(String prefix, String s) {
        boolean isCommand = false;
        if (s.equalsIgnoreCase(prefix + "info")) isCommand = true;
        else if (s.equalsIgnoreCase(prefix + "resources")) isCommand = true;
        else if (s.equalsIgnoreCase(prefix + "players")) isCommand = true;
        return isCommand;
    }
}

