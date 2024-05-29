package Helpers;

import org.openqa.selenium.Capabilities;

public class CapabilityFactory {
    public Capabilities capabilities;

    public Capabilities getCapabilities () {
        capabilities = OptionsManager.getChromeOptions();
        return capabilities;
    }
}
