package dev.mcyoinet.mediatools.utils;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import dev.mcyoinet.mediatools.MediaTools;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.minimessage.tag.standard.StandardTags;

public class ChatUtils {
    private final MediaTools plugin;

    public ChatUtils(MediaTools plugin) {
        this.plugin = plugin;
    }

    public MiniMessage mm = MiniMessage.miniMessage();

    public @NotNull String transformLegacy(@NotNull String text) {
        text = text.replaceAll("&a", "<green>");
        text = text.replaceAll("&b", "<aqua>");
        text = text.replaceAll("&c", "<red>");
        text = text.replaceAll("&d", "<light_purple>");
        text = text.replaceAll("&e", "<yellow>");
        text = text.replaceAll("&f", "<white>");
        text = text.replaceAll("&1", "<dark_blue>");
        text = text.replaceAll("&2", "<dark_green>");
        text = text.replaceAll("&3", "<dark_aqua>");
        text = text.replaceAll("&4", "<dark_red>");
        text = text.replaceAll("&5", "<dark_purple>");
        text = text.replaceAll("&6", "<gold>");
        text = text.replaceAll("&7", "<grey>");
        text = text.replaceAll("&8", "<dark_grey>");
        text = text.replaceAll("&9", "<blue>");
        text = text.replaceAll("&0", "<black>");
        text = text.replaceAll("&l", "<b>");
        text = text.replaceAll("&m", "<st>");
        text = text.replaceAll("&n", "<u>");
        text = text.replaceAll("&k", "<obf>");
        text = text.replaceAll("&i", "<i>");
        text = text.replaceAll("&r", "<reset>");
        text = text.replaceAll("/&#[a-z\\d]{6}/gi", "<" + text.substring(1) + ">");

        return text;
    }

    public @NotNull String applyPlaceholderAPI(@NotNull String text, @Nullable Player player) {
        if(plugin.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            text = PlaceholderAPI.setPlaceholders(player, text);
        }

        return text;
    }

    public @NotNull Component applyMMStyles(@NotNull String text) {
        MiniMessage styleBuilder = MiniMessage.builder()
            .tags(TagResolver.builder()
                .resolver(StandardTags.color())
                .resolver(StandardTags.decorations())
                .build())
            .build();

        return styleBuilder.deserialize(text);
    }

    public @NotNull String format(@NotNull String text, @NotNull Player player) {
        text = applyPlaceholderAPI(text, player);
        text = transformLegacy(text);
        
        return text;
    }

    public @NotNull String format(@NotNull String text) {
        text = applyPlaceholderAPI(text, null);
        text = transformLegacy(text);

        return text;
    }
}
