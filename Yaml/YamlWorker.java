package org.Zalmandrag.customCrafts.Yaml;

import org.Zalmandrag.customCrafts.Crafts.Crafts;
import org.Zalmandrag.customCrafts.CustomCrafts;
import org.Zalmandrag.customCrafts.Parser.ColorParser;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class YamlWorker {
    // поля класса
    private static File Crafts;
    private static File Messages;
    private static File Permissions;
    private static YamlConfiguration YamlConfig;
    private static YamlConfiguration YamlMessages;
    private static YamlConfiguration YamlPermissions;

    // базовая проверка всех Yaml файлов
    public static void CheckYaml(@NotNull CustomCrafts plugin) throws IOException {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();

            Crafts = new File(plugin.getDataFolder(), "crafts.yml");
            Messages = new File(plugin.getDataFolder(), "messages.yml");
            Permissions = new File(plugin.getDataFolder(), "permissions.yml");
            // выгрузка из плагина yaml конфигов
            CreateConfig(plugin);
            CreateMessages(plugin);
            CreatePermissions(plugin);

            // загрузка Yaml
            YamlConfig = YamlConfiguration.loadConfiguration(Crafts);
            YamlMessages = YamlConfiguration.loadConfiguration(Messages);
            YamlPermissions = YamlConfiguration.loadConfiguration(Permissions);
        }
        else {
            Crafts = new File(plugin.getDataFolder(), "crafts.yml");
            Messages = new File(plugin.getDataFolder(), "messages.yml");
            Permissions = new File(plugin.getDataFolder(), "permissions.yml");

            YamlConfig = YamlConfiguration.loadConfiguration(Crafts);
            YamlMessages = YamlConfiguration.loadConfiguration(Messages);
            YamlPermissions = YamlConfiguration.loadConfiguration(Permissions);
        }
    }
    private static void CreateConfig(@NotNull CustomCrafts plugin) throws IOException {
        InputStream InputStreamConfig = YamlWorker.class.getClassLoader().getResourceAsStream("crafts.yml");
        if (InputStreamConfig == null) {
            throw new IllegalArgumentException("Ресурс: config.yml не найден! Пожалуйста обратитесь к создателю плагина для оказания помощи.");
        }
        Path targetPathConfig = new File(plugin.getDataFolder(), "crafts.yml").toPath();
        Files.copy(InputStreamConfig, targetPathConfig);
    }
    private static void CreateMessages(@NotNull CustomCrafts plugin) throws IOException {
        InputStream InputStreamConfig = YamlWorker.class.getClassLoader().getResourceAsStream("messages.yml");
        if (InputStreamConfig == null) {
            throw new IllegalArgumentException("Ресурс: Messages.yml не найден! Пожалуйста обратитесь к создателю плагина для оказания помощи.");
        }
        Path targetPathConfig = new File(plugin.getDataFolder(), "messages.yml").toPath();
        Files.copy(InputStreamConfig, targetPathConfig);
    }
    private static void CreatePermissions(@NotNull CustomCrafts plugin) throws IOException {
        InputStream InputStreamConfig = YamlWorker.class.getClassLoader().getResourceAsStream("permissions.yml");
        if (InputStreamConfig == null) {
            throw new IllegalArgumentException("Ресурс: permissions.yml не найден! Пожалуйста обратитесь к создателю плагина для оказания помощи.");
        }
        Path targetPathConfig = new File(plugin.getDataFolder(), "permissions.yml").toPath();
        Files.copy(InputStreamConfig, targetPathConfig);
    }
    public static Boolean ValidateYamlCrafts(CustomCrafts plugin) throws FileNotFoundException {
        if (!YamlCraftsValidator.ValidateCrafts(plugin)) {
            Bukkit.getLogger().info(ColorParser.ParseColor(YamlMessages.getString("invalid_yaml_configuration_crafts")));
            Bukkit.getPluginManager().disablePlugin(plugin);
            return false;
        }
        else {
            org.Zalmandrag.customCrafts.Crafts.Crafts.RegisterCrafts(Crafts, plugin);
            return true;
        }
    }
    public static YamlConfiguration GetYamlMessages() {
        return YamlMessages;
    }
    public static YamlConfiguration GetYamlPermission() {
        return YamlPermissions;
    }
    public static File GetCrafts() {
        return Crafts;
    }
}
