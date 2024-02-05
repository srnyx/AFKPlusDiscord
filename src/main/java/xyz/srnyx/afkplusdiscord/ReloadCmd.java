package xyz.srnyx.afkplusdiscord;

import org.jetbrains.annotations.NotNull;

import xyz.srnyx.annoyingapi.command.AnnoyingCommand;
import xyz.srnyx.annoyingapi.command.AnnoyingSender;
import xyz.srnyx.annoyingapi.message.AnnoyingMessage;


public class ReloadCmd extends AnnoyingCommand {
    @NotNull private final AFKPlusDiscord plugin;

    public ReloadCmd(@NotNull AFKPlusDiscord plugin) {
        this.plugin = plugin;
    }

    @Override @NotNull
    public AFKPlusDiscord getAnnoyingPlugin() {
        return plugin;
    }

    @Override @NotNull
    public String getName() {
        return "afkplusdiscordreload";
    }
    
    @Override @NotNull
    public String getPermission() {
        return "afkplusdiscord.reload";
    }

    @Override
    public void onCommand(@NotNull AnnoyingSender sender) {
        plugin.reloadPlugin();
        new AnnoyingMessage(plugin, "reload").send(sender);
    }
}
