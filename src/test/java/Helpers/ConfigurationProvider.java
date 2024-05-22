package Helpers;

import java.io.IOException;


public class ConfigurationProvider {

    public String getHomePageURL() throws IOException {
        return ConfigurationManager.getInstance().getProperty("home.url");
    }

    public String getAuthorizationPageURL() throws IOException {
        return ConfigurationManager.getInstance().getProperty("authorization.url");
    }

    public Integer getScreenWidth() throws IOException {
        return Integer.parseInt(ConfigurationManager.getInstance().getProperty("screen.width"));
    }

    public Integer getScreenHeight() throws IOException {
        return Integer.parseInt(ConfigurationManager.getInstance().getProperty("screen.height"));
    }
}

