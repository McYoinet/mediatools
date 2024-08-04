package dev.mcyoinet.mediatools.utils;

import org.checkerframework.checker.nullness.qual.NonNull;

import dev.mcyoinet.mediatools.MediaTools;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;

public class AdventureImplementation {
    private final MediaTools plugin;

    public AdventureImplementation(MediaTools plugin) {
        this.plugin = plugin;
    }

    private BukkitAudiences adventure;

    public @NonNull BukkitAudiences adventure() {
        if(this.adventure == null) {
            throw new IllegalStateException("Tried to access Adventure API when the plugin was disabled.");
        }

        return this.adventure;
    }

    public void initAdventure() {
        this.adventure = BukkitAudiences.create(plugin);
    }

    public void closeAdventure() {
        if(this.adventure != null) {
            this.adventure.close();

            this.adventure = null;
        }
    }
}
