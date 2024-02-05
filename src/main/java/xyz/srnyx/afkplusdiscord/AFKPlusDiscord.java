package xyz.srnyx.afkplusdiscord;

import xyz.srnyx.annoyingapi.AnnoyingPlugin;
import xyz.srnyx.annoyingapi.PluginPlatform;
import xyz.srnyx.annoyingapi.dependency.AnnoyingDependency;


public class AFKPlusDiscord extends AnnoyingPlugin {
    public AFKPlusDiscord() {
        options
                .pluginOptions(pluginOptions -> pluginOptions
                        .updatePlatforms(
                                PluginPlatform.modrinth("afkplusdiscord"),
                                PluginPlatform.hangar(this, "srnyx"),
                                PluginPlatform.spigot("123456"))
                        .dependencies(
                                new AnnoyingDependency("AFKPlus", true, false,
                                        PluginPlatform.spigot("35065")),
                                new AnnoyingDependency("DiscordSRV", true, false,
                                        PluginPlatform.modrinth("UmLGoGij"),
                                        PluginPlatform.spigot("18494"))))
                .bStatsOptions(bStatsOptions -> bStatsOptions.id(20921))
                .registrationOptions.toRegister(new ReloadCmd(this));
    }

    @Override
    public void enable() {
        new AFKListener(this).register();
    }
}
