import org.zaproxy.gradle.addon.AddOnStatus

description = "The beta quality Active Scanner rules"

zapAddOn {
    addOnName.set("Active scanner rules (beta)")
    addOnStatus.set(AddOnStatus.BETA)
    zapVersion.set("2.11.0")

    manifest {
        author.set("ZAP Dev Team")
        url.set("https://www.zaproxy.org/docs/desktop/addons/active-scan-rules-beta/")

        dependencies {
            addOns {
                register("commonlib") {
                    version.set(">= 1.5.0 & < 2.0.0")
                }
                register("oast") {
                    version.set(">= 0.3.0")
                }
            }
        }

        extensions {
            register("org.zaproxy.zap.extension.ascanrulesBeta.payloader.ExtensionPayloader") {
                classnames {
                    allowed.set(listOf("org.zaproxy.zap.extension.ascanrulesBeta.payloader"))
                }
                dependencies {
                    addOns {
                        register("custompayloads") {
                            version.set(">= 0.9.0 & < 1.0.0")
                        }
                    }
                }
            }
        }
    }
}

repositories {
    maven {
        url = uri("https://oss.sonatype.org/content/repositories/snapshots/")
    }
}

dependencies {
    zap("org.zaproxy:zap:2.11.0-20210929.165234-4")
    compileOnly(parent!!.childProjects.get("commonlib")!!)
    compileOnly(parent!!.childProjects.get("custompayloads")!!)
    compileOnly(parent!!.childProjects.get("oast")!!)

    implementation("com.googlecode.java-diff-utils:diffutils:1.3.0")
    implementation("org.jsoup:jsoup:1.13.1")

    testImplementation(parent!!.childProjects.get("commonlib")!!)
    testImplementation(parent!!.childProjects.get("commonlib")!!.sourceSets.test.get().output)
    testImplementation(parent!!.childProjects.get("custompayloads")!!)
    testImplementation(parent!!.childProjects.get("oast")!!)
    testImplementation(project(":testutils"))
    testImplementation("org.apache.commons:commons-lang3:3.11")
}

spotless {
    javaWith3rdPartyFormatted(project, listOf("src/**/IntegerOverflowScanRule.java"))
}
