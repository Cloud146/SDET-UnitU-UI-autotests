package Helpers;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;

public class OptionsManager {

    static ConfigurationProvider configurationProvider = new ConfigurationProvider();

    public static ChromeOptions getChromeOptions() throws IOException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized")
                .addArguments("--ignore-certificate-errors")
                .addArguments("--disable-popup-blocking")
                .addArguments("--remote-allow-origins=**")
                .addArguments("--disable-extensions")
                .addArguments("--disable-notifications")
                .addArguments("--disable-infobars")
                .addArguments("--no-default-browser-check")
                .addArguments("--disable-first-run-ui")
                .addArguments("--disable-features=AutofillAssistant")
                .addArguments("--disable-features=TranslateUI")
                .addArguments("--disable-features=ChromeWhatsNewUI")
                .addArguments("--disable-features=ChromeTips")
                .addArguments("--no-first-run")
                .addArguments("--disable-dev-shm-usage")
                .addArguments("--no-sandbox");
              //  .addArguments("--incognito");
        options.setCapability("browserVersion", configurationProvider.getChromeVersion());
        options.setCapability("platformName", "Windows");
        options.setBrowserVersion(configurationProvider.getChromeVersion());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        options.merge(capabilities);
        return options;
    }

    public static InternetExplorerOptions getIEOptions(){
        InternetExplorerOptions options = new InternetExplorerOptions();
        options.ignoreZoomSettings();
        options.introduceFlakinessByIgnoringSecurityDomains();
        return options;
    }
}
