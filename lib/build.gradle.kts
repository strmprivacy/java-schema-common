plugins {
    id("maven-publish")
}


val sourcesJar = tasks.register("sourcesJar", Jar::class) {
    from(sourceSets["main"].allSource)
    archiveClassifier.set("sources")
}

val javadocJar = tasks.register("javadocJar", Jar::class) {
    from(tasks["javadoc"])
    archiveClassifier.set("javadoc")
}

publishing {
    publications {
        register("mavenJava", MavenPublication::class) {
            from(components["java"])

            artifacts {
                add("archives", sourcesJar)
                add("archives", javadocJar)
            }

            groupId = "io.streammachine.schemas"
            artifactId = "schema-common"
            version = project.version.toString()

            pom {
                name.set("$groupId:$artifactId")
                description.set("Common module for Stream Machine schemas.")
                url.set("https://streammachine.io")

                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("https://apache.org/licenses/LICENSE-2.0.txt")
                    }
                }

                developers {
                    developer {
                        name.set("Stream Machine B.V.")
                        email.set("apis@streammachine.io")
                        organization.set("Stream Machine B.V.")
                        organizationUrl.set("https://streammachine.io")
                    }
                }

                scm {
                    url.set("https://github.com/streammachineio/java-schema-common")
                    connection.set("scm:git:git://github.com/streammachineio/java-schema-common.git")
                    developerConnection.set("scm:git:git://github.com/streammachineio/java-schema-common.git")
                }
            }
        }
    }
}

tasks.findByName("publish")?.dependsOn("build")
