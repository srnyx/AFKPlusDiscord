import xyz.srnyx.gradlegalaxy.enums.Repository
import xyz.srnyx.gradlegalaxy.enums.repository
import xyz.srnyx.gradlegalaxy.utility.setupAnnoyingAPI
import xyz.srnyx.gradlegalaxy.utility.spigotAPI


plugins {
    java
    id("xyz.srnyx.gradle-galaxy") version "1.1.2"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

setupAnnoyingAPI("8469d47bd7", "xyz.srnyx", "1.0.0", "Get AFK+ updates in Discord through DiscordSRV")
spigotAPI("1.19.3")
repository(Repository.DV8TION, Repository.SCARSZ)

dependencies {
    compileOnly("net.lapismc","AFKPlus", "3.4.1")
    compileOnly("com.discordsrv", "discordsrv", "1.27.0")
}
