package dev.mcyoinet.mediatools;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import dev.mcyoinet.mediatools.utils.AdventureImplementation;
import dev.mcyoinet.mediatools.utils.ChatUtils;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;

public class MediaTools extends JavaPlugin {
    private AdventureImplementation adventureImplementation;
    public BukkitAudiences audiences;
    
    PluginDescriptionFile meta = getDescription();

    public String name = meta.getName();
    public String version = meta.getVersion();
    public String author = meta.getAuthors().get(0);
    public String description = meta.getDescription();
    
    public String internalPrefix = "&8[&dMediaTools&8]&r";

    ChatUtils chat = new ChatUtils(this);

    @Override
    public void onEnable() {
        adventureImplementation = new AdventureImplementation(this);
        
        adventureImplementation.initAdventure();
        audiences = adventureImplementation.adventure();

        audiences.console().sendMessage(chat.mm.deserialize(chat.format("<prefix> &")));
    }

    @Override
    public void onDisable() {
        adventureImplementation.closeAdventure();
    }
}