package ml.itzblacky.mindustryjda;

import io.anuke.mindustry.Vars;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;

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

    public static String getString(Map<String, Object> config, String key) {
        try {
            Object o = config.getOrDefault(key, "");
            return o instanceof String ? (String) o : "";
        } catch (NullPointerException e) {
            return "";
        }
    }

    public static void sendMindustryMessage(String message) {
        Vars.playerGroup.all().forEach((player) -> {
            player.sendMessage(message);
        });

    }
}

