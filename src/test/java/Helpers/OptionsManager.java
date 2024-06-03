package Helpers;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;

import java.io.IOException;

public class OptionsManager {

    static ConfigurationProvider configurationProvider = new ConfigurationProvider();

    public static ChromeOptions getChromeOptions() throws IOException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized")
                .addArguments("--ignore-certificate-errors")
                .addArguments("--disable-popup-blocking")
                .addArguments("--remote-allow-origins=**")
                .addArguments("--disable-infobars");
//                .addArguments("--incognito");
        options.setCapability("browserVersion", configurationProvider.getChromeVersion());
        options.setCapability("platformName", "Windows");
        options.setBrowserVersion(configurationProvider.getChromeVersion());
        return options;
    }

    public static InternetExplorerOptions getIEOptions(){
        InternetExplorerOptions options = new InternetExplorerOptions();
        options.ignoreZoomSettings();
        options.introduceFlakinessByIgnoringSecurityDomains();
        return options;
    }
}
