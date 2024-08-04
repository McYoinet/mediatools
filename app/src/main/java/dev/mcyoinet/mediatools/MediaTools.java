package dev.mcyoinet.mediatools;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import dev.mcyoinet.mediatools.utils.AdventureImplementation;
import dev.mcyoinet.mediatools.utils.ChatUtils;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;

public class MediaTools extends JavaPlugin {
    private AdventureImplementation adventureImplementation;
    public BukkitAudiences audiences;

    ChatUtils chat = new ChatUtils(this);

    PluginDescriptionFile meta = getDescription();

    public String name = meta.getName();
    public String version = meta.getVersion();
    public String author = meta.getAuthors().get(0);
    public String description = meta.getDescription();

    public String internalPrefix = "&8[&dMediaTools&8]&r";

    String enableMessage = "&7&m                                                                         &r<br>"
                            + "<prefix> Plugin enabled.&r<br>"
                            + "<prefix> Version: &b<version>&f.<br>"
                            + "<prefix> Developed by: &b<author>&f.<br>"
                            + "&7&m                                                                         &r<br>";
    
    String disableMessage = "&7&m                                                                         &r<br>"
                            + "<prefix> Plugin disabled.&r<br>"
                            + "<prefix> Version: &b<version>&f.<br>"
                            + "<prefix> Developed by: &b<author>&f.<br>"
                            + "&7&m                                                                         &r<br>";

    @Override
    public void onEnable() {
        adventureImplementation = new AdventureImplementation(this);
        
        adventureImplementation.initAdventure();
        audiences = adventureImplementation.adventure();

        audiences.console().sendMessage(chat.mm.deserialize(chat.format(enableMessage),
            Placeholder.unparsed("prefix", internalPrefix),
            Placeholder.unparsed("version", version),
            Placeholder.unparsed("author", author)));
    }

    @Override
    public void onDisable() {
        audiences.console().sendMessage(chat.mm.deserialize(chat.format(disableMessage),
            Placeholder.unparsed("prefix", internalPrefix),
            Placeholder.unparsed("version", version),
            Placeholder.unparsed("author", author)));

        adventureImplementation.closeAdventure();
    }
}