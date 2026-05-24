package tests.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import allure.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public abstract class UIBaseTest {

    private static final String DEFAULT_BASE_URL = "https://dadata.ru/";

    @BeforeAll
    static void setupSelenideConfig() {
        Configuration.browser = System.getProperty("browser", "chrome");
        String browserVersion = System.getProperty("browserVersion");
        if (browserVersion != null && !browserVersion.isBlank()) {
            Configuration.browserVersion = browserVersion;
        }
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
        Configuration.baseUrl = System.getProperty("baseUrl", DEFAULT_BASE_URL);
        Configuration.pageLoadStrategy = "eager";
        Configuration.headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        Configuration.timeout = Long.parseLong(System.getProperty("timeout", "15000"));

        String login = System.getProperty("loginSelenoid");
        String password = System.getProperty("passwordSelenoid");
        String urlSelenoid = System.getProperty("urlSelenoid");

        if (login != null && password != null && urlSelenoid != null
                && !login.isBlank() && !password.isBlank() && !urlSelenoid.isBlank()) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true ));
            Configuration.browserCapabilities = capabilities;
            Configuration.remote = "https://" + login + ":" + password + "@" + urlSelenoid;
        }
    }

    @BeforeEach
    void registerAllureSelenideAndOpenSite() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Selenide.open("");
    }

    @AfterEach
    void addAttachmentsAndClose() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        Selenide.closeWebDriver();
    }
}
