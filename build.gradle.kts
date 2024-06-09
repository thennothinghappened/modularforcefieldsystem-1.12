import net.minecraftforge.gradle.userdev.UserDevExtension

plugins {
    id("net.minecraftforge.gradle")
}

val outputFileName = "MFFS"

group = "com.nekokittygames.mffs"
version = "1.12.2-4.0.1-SNAPSHOT"

repositories {
    mavenCentral()
    exclusiveContent {
        forRepository {
            maven("https://cursemaven.com")
        }
        filter {
            includeGroup("curse.maven")
        }
    }
}

dependencies {

    minecraft("net.minecraftforge:forge:1.12.2-14.23.5.2859")

    implementation("curse.maven:industrialcraft-242638:3078604")
    implementation("curse.maven:redstone_flux-270789:2623090")
    implementation("curse.maven:tesla-244651:2487959")
    implementation("curse.maven:hwyla-253449:2568751")
    implementation("curse.maven:guide_api-228832:2645992")
    implementation("curse.maven:buildcraft-61811:3204475")

}

configure<UserDevExtension> {

    mappings("stable",  "39-1.12")

    runs {

        create("client") {

            workingDirectory(project.file("run"))

            // Recommended logging data for a userdev environment
            property("forge.logging.markers", "SCAN,REGISTRIES,REGISTRYDUMP")

            // Recommended logging level for the console
            property("forge.logging.console.level", "debug")

        }

        create("server") {

            // Recommended logging data for a userdev environment
            property("forge.logging.markers", "SCAN,REGISTRIES,REGISTRYDUMP")

            // Recommended logging level for the console
            property("forge.logging.console.level", "debug")

        }

    }
}

tasks {

    withType<Jar> {
        archiveBaseName.set(outputFileName)
    }

}

sourceSets.all {
    output.setResourcesDir(output.classesDirs.files.iterator().next())
}

