package org.Zalmandrag.customCrafts.Commands;

import org.Zalmandrag.customCrafts.CustomCrafts;
import org.Zalmandrag.customCrafts.Yaml.YamlWorker;
import org.Zalmandrag.customCrafts.Parser.ColorParser;
import org.Zalmandrag.customCrafts.utils.PluginLoader;
import org.Zalmandrag.customCrafts.utils.SendMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;

public class CommandExecutor implements org.bukkit.command.CommandExecutor {
    private CustomCrafts plugin;

    public CommandExecutor(CustomCrafts plugin) {
        this.plugin = plugin;
    }

    private static void CusstomCraftsReload(CommandSender sender, CustomCrafts plugin) throws IOException {
        if (sender.hasPermission(Objects.requireNonNull(YamlWorker.GetYamlPermission().getString("CustomCraftsReload")))) {
            PluginLoader.Reload(plugin);
            SendMessage.SendMessage(sender, YamlWorker.GetYamlMessages().getString("successful_reload"));
        } else {
            sender.sendMessage(ColorParser.ParseColor(YamlWorker.GetYamlMessages().getString("no_permission_message")));
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        // базовая проверка пермишона
        if (commandSender.hasPermission(Objects.requireNonNull(YamlWorker.GetYamlPermission().getString("CustomCrafts")))) {
            if (strings.length < 1) {
                SendMessage.SendMessage(commandSender, YamlWorker.GetYamlMessages().getString("usage_CustomCrafts_message"));
            }
            else {
                try {
                    CommandReload(strings, commandSender, plugin);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            commandSender.sendMessage(ColorParser.ParseColor(Objects.requireNonNull(YamlWorker.GetYamlMessages().getString("no_permission_message"))));
        }
        return true;
    }

    private static void CommandReload(String[] strings, CommandSender sender, CustomCrafts plugin) throws IOException {
        switch (strings[0]) {
            // Место под новые агрументы команды (хз может в следующем обновлении добавлю)
            case "reload": CusstomCraftsReload(sender, plugin);
        }
    }
}