package xyz.srnyx.afkplusdiscord;

import github.scarsz.discordsrv.DiscordSRV;
import github.scarsz.discordsrv.dependencies.jda.api.entities.TextChannel;

import net.lapismc.afkplus.AFKPlus;
import net.lapismc.afkplus.api.AFKPlusAPI;
import net.lapismc.afkplus.api.AFKStartEvent;
import net.lapismc.afkplus.api.AFKStopEvent;
import net.lapismc.afkplus.playerdata.AFKPlusPlayer;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

import org.jetbrains.annotations.NotNull;

import xyz.srnyx.annoyingapi.AnnoyingListener;
import xyz.srnyx.annoyingapi.AnnoyingPlugin;
import xyz.srnyx.annoyingapi.message.AnnoyingMessage;

import java.util.Date;


public class AFKListener extends AnnoyingListener {
    @NotNull private final AFKPlusDiscord plugin;
    @NotNull private final AFKPlus afkPlus;
    @NotNull private final DiscordSRV discordSRV;

    public AFKListener(@NotNull AFKPlusDiscord plugin) {
        this.plugin = plugin;
        this.afkPlus = new AFKPlusAPI().getPlugin();
        this.discordSRV = DiscordSRV.getPlugin();
    }

    @Override @NotNull
    public AFKPlusDiscord getAnnoyingPlugin() {
        return plugin;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onAFKStart(@NotNull AFKStartEvent event) {
        if (!event.isCancelled()) sendDiscordMessage(new AnnoyingMessage(plugin, "afk.start"), event.getPlayer());
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onAFKStop(@NotNull AFKStopEvent event) {
        if (event.isCancelled()) return;
        final AFKPlusPlayer player = event.getPlayer();
        final String time = afkPlus.prettyTime.formatDuration(afkPlus.reduceDurationList(afkPlus.prettyTime.calculatePreciseDuration(new Date(player.getAFKStart()))));
        sendDiscordMessage(new AnnoyingMessage(plugin, "afk.stop").replace("%time%", time), player);
    }

    private void sendDiscordMessage(@NotNull AnnoyingMessage message, @NotNull AFKPlusPlayer player) {
        final TextChannel channel = discordSRV.getMainTextChannel();
        if (channel == null) {
            AnnoyingPlugin.LOGGER.warning("Main text channel is null, cannot send AFK message to Discord");
            return;
        }
        channel.sendMessage(ChatColor.stripColor(message
                .replace("%player%", player.getName())
                .toString())).queue();
    }
}
