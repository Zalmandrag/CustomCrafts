package org.Zalmandrag.customCrafts.Crafts;

import org.Zalmandrag.customCrafts.CustomCrafts;
import org.Zalmandrag.customCrafts.Parser.ColorParser;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Crafts {
    public static void RegisterCrafts(@NotNull File Crafts, @NotNull CustomCrafts plugin) throws FileNotFoundException {
        // Базовые настройки для работы с yaml
        Yaml yaml = new Yaml();
        InputStream CraftsInputStream = new FileInputStream(Crafts);
        Map<String, Object> obj = yaml.load(CraftsInputStream);
        // Создание предмета
        for (String key : obj.keySet()) {
            Map<String, Object> value = (Map<String, Object>)obj.get(key);
            ItemStack itemStack = new ItemStack(Material.matchMaterial((String) value.get("material")));
            ItemMeta meta = itemStack.getItemMeta();
            meta.setDisplayName(ColorParser.ParseColor((String) value.get("title")));
            List<String> Lore = new ArrayList<String>();
            Lore.add(ColorParser.ParseColor((String) value.get("lore")));
            meta.setLore(Lore);
            // Добавление чар
            AddEnchantments(value, meta);
            // Добавление меты в ItemStack
            itemStack.setItemMeta(meta);
            // Добавление крафтов
            RegisterCrafts(plugin, value, itemStack, key);
        }
    }
    private static void RegisterCrafts(@NotNull Plugin plugin,@NotNull Map<String, Object> value,@NotNull ItemStack itemStack,@NotNull String key) {
        Map<String, Object> CraftsMap = (Map<String, Object>) value.get("crafts");
        for (String CraftsKey : CraftsMap.keySet()) {
            NamespacedKey key2 = new NamespacedKey(plugin,key + CraftsKey);
            ShapedRecipe Recipe = new ShapedRecipe(key2,itemStack);
            Map<String, Object> ObjectRecipe = (Map<String, Object>) CraftsMap.get(CraftsKey);
            String row_1 = (String) ObjectRecipe.get("row_1");
            String row_2 = (String) ObjectRecipe.get("row_2");
            String row_3 = (String) ObjectRecipe.get("row_3");
            Recipe.shape( (String) ObjectRecipe.get("row_1"), (String) ObjectRecipe.get("row_2"), (String) ObjectRecipe.get("row_3"));
            if (ObjectRecipe.get("material_A") != null) Recipe.setIngredient('A', Material.matchMaterial((String) ((Map<?, ?>) CraftsMap.get(CraftsKey)).get("material_A")));
            if (ObjectRecipe.get("material_B") != null) Recipe.setIngredient('B', Material.matchMaterial((String) ((Map<?, ?>) CraftsMap.get(CraftsKey)).get("material_B")));
            if (ObjectRecipe.get("material_C") != null) Recipe.setIngredient('C', Material.matchMaterial((String) ((Map<?, ?>) CraftsMap.get(CraftsKey)).get("material_C")));
            if (ObjectRecipe.get("material_D") != null) Recipe.setIngredient('D', Material.matchMaterial((String) ((Map<?, ?>) CraftsMap.get(CraftsKey)).get("material_D")));
            if (ObjectRecipe.get("material_E") != null) Recipe.setIngredient('E', Material.matchMaterial((String) ((Map<?, ?>) CraftsMap.get(CraftsKey)).get("material_E")));
            if (ObjectRecipe.get("material_F") != null) Recipe.setIngredient('F', Material.matchMaterial((String) ((Map<?, ?>) CraftsMap.get(CraftsKey)).get("material_F")));
            if (ObjectRecipe.get("material_G") != null) Recipe.setIngredient('G', Material.matchMaterial((String) ((Map<?, ?>) CraftsMap.get(CraftsKey)).get("material_G")));
            if (ObjectRecipe.get("material_H") != null) Recipe.setIngredient('H', Material.matchMaterial((String) ((Map<?, ?>) CraftsMap.get(CraftsKey)).get("material_H")));
            if (ObjectRecipe.get("material_I") != null) Recipe.setIngredient('I', Material.matchMaterial((String) ((Map<?, ?>) CraftsMap.get(CraftsKey)).get("material_I")));
            Bukkit.addRecipe(Recipe);
        }
    }
    public static void AddEnchantments(@NotNull Map<String, Object> value,@NotNull ItemMeta meta) {
        Map<String, Object> ObjectEnchantments = (Map<String, Object>) value.get("enchantments");
        if ((Boolean) value.get("enchantment") && value.get("enchantments") != null && !(Boolean) value.get("hide_enchantments")) {
            // Перебор всех чар и их добавление в мету
            for (String key1 :  ObjectEnchantments.keySet()) {
                NamespacedKey EnchantmentKey = NamespacedKey.fromString(key1);
                Map<String, Object> ObjectEnchantmentsEnchantments = (Map<String, Object>) ObjectEnchantments.get(key1);
                meta.addEnchant(Enchantment.getByKey(EnchantmentKey), (Integer) ObjectEnchantmentsEnchantments.get("level"), true);
            }
        } else if ((Boolean) value.get("enchantment") && value.get("enchantments") != null && (Boolean) value.get("hide_enchantments")) {
            for (String key1 :  ObjectEnchantments.keySet()) {
                NamespacedKey EnchantmentKey = NamespacedKey.fromString(key1);
                Map<String, Object> ObjectEnchantmentsEnchantments = (Map<String, Object>) ObjectEnchantments.get(key1);
                meta.addEnchant(Enchantment.getByKey(EnchantmentKey), (Integer) ObjectEnchantmentsEnchantments.get("level"), true);
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            }
        }
    }
}
