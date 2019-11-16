package ml.itzblacky.mindustryjda.Utils;

import io.anuke.mindustry.gen.Call;
import ml.itzblacky.mindustryjda.Main;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
            // TODO: add version checking.
            //if(getString(config, "version"))
            setConfigMap(config);
        } catch (FileNotFoundException e) {
            copy(Main.class.getResourceAsStream("/config.yaml"), path + "/MindustryJDA/config.yml");
            loadConfig(yaml, path);
        }
    }
    public static void sendMindustryMessage(String message) {
        Call.sendMessage(message);
    }
}

