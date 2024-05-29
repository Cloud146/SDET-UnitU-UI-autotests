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

    public String getSqlExercisesPageURL() throws IOException {
        return ConfigurationManager.getInstance().getProperty("sql.exercises.url");
    }

    public String getCookieFilePath() throws IOException {
        return ConfigurationManager.getInstance().getProperty("cookie.file.path");
    }

    public String getGridHubURL() throws IOException {
        return ConfigurationManager.getInstance().getProperty("grid.hub.url");
    }
}

