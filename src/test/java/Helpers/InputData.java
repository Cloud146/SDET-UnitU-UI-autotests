package Helpers;

import java.io.IOException;


public class InputData {

    public String getHomePageURL() throws IOException {
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("config.properties"));
        return System.getProperty("home.url");
    }

    public Integer getScreenWidth() throws IOException {
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("config.properties"));
        int widthInt = Integer.parseInt(System.getProperty("screen.width"));
        return widthInt;
    }

    public Integer getScreenHeight() throws IOException {
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("config.properties"));
        int heightInt = Integer.parseInt(System.getProperty("screen.height"));
        return heightInt;
    }
}
