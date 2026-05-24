package allure;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selenide.sessionId;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.openqa.selenium.logging.LogType.BROWSER;

public final class Attach {

    private Attach() {
    }

    @Attachment(value = "{attachName}", type = "image/png")
    public static byte[] screenshotAs(String attachName) {
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Page source", type = "text/html")
    public static byte[] pageSource() {
        return getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
    }

    @Attachment(value = "{attachName}", type = "text/plain")
    public static String attachAsText(String attachName, String message) {
        return message;
    }

    public static void browserConsoleLogs() {
        attachAsText(
                "Browser console logs",
                String.join("\n", Selenide.getWebDriverLogs(BROWSER))
        );
    }

    @Attachment(value = "Video", type = "text/html", fileExtension = ".html")
    public static String addVideo() {
        URL video = getVideoUrl();
        if (video == null) {
            return "<html><body><p>Видео: только при прогоне через Selenoid (-DurlSelenoid ...)</p></body></html>";
        }
        return "<html><body><video width='100%' height='100%' controls autoplay><source src='"
                + video
                + "' type='video/mp4'></video></body></html>";
    }

    public static URL getVideoUrl() {
        String host = System.getProperty("selenoidVideoHost", "https://selenoid.autotests.cloud/video/");
        if (System.getProperty("urlSelenoid") == null || System.getProperty("urlSelenoid").isBlank()) {
            return null;
        }
        try {
            return new URL(host + sessionId() + ".mp4");
        } catch (MalformedURLException e) {
            return null;
        }
    }
}
