plugins {
    `java-library`
    id("io.github.goooler.shadow") version "8.1.8"
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.14"
}

group = "de.terranova.infini"
version = "0.0.1"

repositories {
    gradlePluginPortal()
    mavenCentral()
    maven { name = "enginehub-maven"; url= uri("https://maven.enginehub.org/repo/") }
    maven { name = "codemc-repo"; url = uri("https://repo.codemc.org/repository/maven-public/") }
    maven {
        name = "papermc-repo"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
    maven { name = "sonatype"; url = uri("https://oss.sonatype.org/content/groups/public/") }
    maven { name = "Hikari&Shadow"; url = uri("https://jitpack.io") }
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/TerraNova-Devs/TerranovaLib")

        credentials {
            val githubUser: String? = findProperty("gpr.user") as String?
                ?: System.getenv("GPR_USER")
            val githubToken: String? = findProperty("gpr.token") as String?
                ?: System.getenv("GPR_TOKEN")

            if (githubUser == null || githubToken == null) {
                throw GradleException("GitHub credentials not found. Please set 'gpr.user' and 'gpr.token' in gradle.properties or as environment variables.")
            }

            username = githubUser
            password = githubToken
        }
    }
}

paperweight.reobfArtifactConfiguration = io.papermc.paperweight.userdev.ReobfArtifactConfiguration.MOJANG_PRODUCTION

dependencies {
    paperweight.paperDevBundle("1.21.4-R0.1-SNAPSHOT")
    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")
    implementation("com.sk89q.worldedit:worldedit-bukkit:7.2.9")
    implementation("net.leonardo_dgs:InteractiveBooks:1.7.10")
    implementation("de.mcterranova:terranova-lib:1.0.1")
    implementation("com.zaxxer:HikariCP:6.2.1")
    implementation("de.tr7zw:item-nbt-api:2.11.3")
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(21)
}

tasks {
    compileJava {
        options.release = 21
    }
    shadowJar {
        destinationDirectory = file("C:\\Users\\papri\\Desktop\\dev 1.21.4\\plugins")
        relocate ("de.tr7zw.changeme.nbtapi", "de.mcterranova.infini.shaded.nbtapi")
    }
}

tasks.processResources {
    val props = mapOf("version" to version)
    filteringCharset = "UTF-8"
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    inputs.properties(props)
    filteringCharset = "UTF-8"
    filesMatching("plugin.yml") {
        expand(props)
    }
}