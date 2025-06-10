package org.Zalmandrag.customCrafts.utils;

import org.Zalmandrag.customCrafts.CustomCrafts;
import org.Zalmandrag.customCrafts.Yaml.YamlWorker;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class PluginLoader {
    private CustomCrafts plugin;
    public static void Reload(@NotNull CustomCrafts plugin) throws IOException {
        YamlWorker.CheckYaml(plugin);
    }
}
