import org.ajoberstar.grgit.Grgit


plugins {
    id("java-library")
    id("org.ajoberstar.grgit") version "4.1.0"
    id("io.github.gradle-nexus.publish-plugin") version "1.1.0"
}

buildscript {
    tasks.named<Wrapper>("wrapper") {
        gradleVersion = "6.8.2"
        distributionType = Wrapper.DistributionType.ALL
    }
}

tasks.withType(GradleBuild::class) {
    // To prevent clashing with directory names and project names
    buildName = project.name
}

val branch = System.getenv("CI_COMMIT_REF_NAME") ?: Grgit.open(mapOf("dir" to project.file("."))).branch.current().name
val tag = System.getenv("CI_COMMIT_TAG")

rootProject.version = if (tag != null || branch == "master") project.version else "${project.version}-SNAPSHOT"

nexusPublishing {
    repositories {
        sonatype{
            username.set(System.getenv("MAVEN_OSSRH_USER"))
            password.set(System.getenv("MAVEN_OSSRH_PASSWORD"))
        }
    }
}

allprojects {
    version = rootProject.version

    apply {
        plugin("java-library")
    }

    buildscript {
        repositories {
            mavenLocal()
            mavenCentral()
        }
    }

    repositories {
        mavenLocal()
        mavenCentral()
    }

    java.sourceCompatibility = JavaVersion.VERSION_1_8
    java.targetCompatibility = JavaVersion.VERSION_1_8

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    tasks.withType<Jar> {
        onlyIf { !sourceSets["main"].allSource.files.isEmpty() }
    }
}

ext["grpcVersion"] = "1.37.1"
ext["grpcKotlinVersion"] = "1.0.0" // CURRENT_GRPC_KOTLIN_VERSION
ext["protobufVersion"] = "3.13.0"