package ml.itzblacky.mindustryjda.Utils;

import java.util.Map;

import static ml.itzblacky.mindustryjda.Main.getConfigMap;

public class ConfigUtils {
    public static String getString(String key) {
        return getString(getConfigMap(), key);
    }

    public static String getString(Map<String, Object> map, String key) {
        try {
            Object o = map.getOrDefault(key, "");
            return o instanceof String ? (String) o : "";
        } catch (NullPointerException e) {
            return "";
        }
    }

    public static Long getLong(String key) {
        return getLong(getConfigMap(), key);
    }

    public static Long getLong(Map<String, Object> map, String key) {
        try {
            Object o = map.getOrDefault(key, 0L);
            return o instanceof Long ? (Long) o : 0L;
        } catch (NullPointerException e) {
            return 0L;
        }
    }

    public static Integer getInt(String key) {
        return getInt(getConfigMap(), key);
    }

    public static Integer getInt(Map<String, Object> map, String key) {
        try {
            Object o = map.getOrDefault(key, 0);
            return o instanceof Integer ? (Integer) o : 0;
        } catch (NullPointerException e) {
            return 0;
        }
    }

    public static Double getDouble(String key) {
        return getDouble(getConfigMap(), key);
    }

    public static Double getDouble(Map<String, Object> map, String key) {
        try {
            Object o = map.getOrDefault(key, 0.0);
            return o instanceof Double ? (Double) o : 0.0;
        } catch (NullPointerException e) {
            return 0.0;
        }
    }
}
