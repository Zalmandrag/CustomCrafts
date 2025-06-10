package org.Zalmandrag.customCrafts.Yaml;

import org.Zalmandrag.customCrafts.Parser.ColorParser;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

public class YamlCraftsValidator {
    public static Boolean ValidateCrafts(Plugin plugin) throws FileNotFoundException {
        Yaml yaml = new Yaml();
        InputStream CraftsStream = new FileInputStream(YamlWorker.GetCrafts());
        Map<String, Object> obj = yaml.load(CraftsStream);
        return ValidateByNull(obj);
    }
    private static Boolean ValidateByNull(@NotNull Map<String, Object> obj) {
        for (String key : obj.keySet()) {
            if (obj.get(key) == null) return false;
            Map<String, Object> ObjectElements = (Map<String, Object>) obj.get(key);
            for (String ObjectKey: ObjectElements.keySet()) {
                if (ObjectElements.get(ObjectKey) == null) return false;
            }
        }
        return true;
    }
}
