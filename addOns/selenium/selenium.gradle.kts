import org.zaproxy.gradle.addon.AddOnStatus

description = "WebDriver provider and includes HtmlUnit browser"

zapAddOn {
    addOnName.set("Selenium")
    addOnStatus.set(AddOnStatus.RELEASE)
    zapVersion.set("2.11.0")

    manifest {
        author.set("ZAP Dev Team")
        url.set("https://www.zaproxy.org/docs/desktop/addons/selenium/")
    }

    apiClientGen {
        api.set("org.zaproxy.zap.extension.selenium.SeleniumAPI")
        options.set("org.zaproxy.zap.extension.selenium.SeleniumOptions")
        messages.set(file("src/main/resources/org/zaproxy/zap/extension/selenium/resources/Messages.properties"))
    }
}

repositories {
    maven {
        url = uri("https://oss.sonatype.org/content/repositories/snapshots/")
    }
}

dependencies {
    zap("org.zaproxy:zap:2.11.0-20210923.152813-2")

    api("org.seleniumhq.selenium:selenium-server:3.141.59")
    api("org.seleniumhq.selenium:htmlunit-driver:2.36.0")
    api("com.codeborne:phantomjsdriver:1.4.4")

    testImplementation(project(":testutils"))
}
