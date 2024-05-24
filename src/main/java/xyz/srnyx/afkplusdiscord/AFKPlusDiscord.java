package xyz.srnyx.afkplusdiscord;

import xyz.srnyx.annoyingapi.AnnoyingPlugin;
import xyz.srnyx.annoyingapi.PluginPlatform;
import xyz.srnyx.annoyingapi.dependency.AnnoyingDependency;
import xyz.srnyx.annoyingapi.file.AnnoyingResource;


public class AFKPlusDiscord extends AnnoyingPlugin {
    public boolean start = true;
    public boolean stop = true;
    
    public AFKPlusDiscord() {
        options
                .pluginOptions(pluginOptions -> pluginOptions
                        .updatePlatforms(
                                PluginPlatform.modrinth("afkplusdiscord"),
                                PluginPlatform.hangar(this, "srnyx"),
                                PluginPlatform.spigot("114950"))
                        .dependencies(
                                new AnnoyingDependency("AFKPlus", true, false,
                                        PluginPlatform.spigot("35065")),
                                new AnnoyingDependency("DiscordSRV", true, false,
                                        PluginPlatform.modrinth("UmLGoGij"),
                                        PluginPlatform.spigot("18494"))))
                .bStatsOptions(bStatsOptions -> bStatsOptions.id(20921))
                .registrationOptions.toRegister(new ReloadCmd(this));

        reload();
    }

    @Override
    public void enable() {
        new AFKListener(this).register();
    }

    @Override
    public void reload() {
        final AnnoyingResource config = new AnnoyingResource(this, "config.yml");
        start = config.getBoolean("start", true);
        stop = config.getBoolean("stop", true);
    }
}
