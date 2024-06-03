package Helpers;

import org.openqa.selenium.Capabilities;

import java.io.IOException;

public class CapabilityFactory {
    public Capabilities capabilities;

    public Capabilities getCapabilities () throws IOException {
        capabilities = OptionsManager.getChromeOptions();
        return capabilities;
    }
}
