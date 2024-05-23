package Helpers;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.io.FileWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;


public class CookieUtils {
    public static void saveCookiesToFile(WebDriver driver, String filePath) throws IOException {
        Set<Cookie> cookies = driver.manage().getCookies();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(cookies);

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(json);
        }
    }

    public static void loadCookiesFromFile(WebDriver driver, String filePath) throws IOException {
        Type type = new TypeToken<HashSet<Cookie>>() {}.getType();
        Set<Cookie> cookiesFromFile = new Gson().fromJson(new FileReader(filePath), type);

        for (Cookie cookie : cookiesFromFile) {
            driver.manage().addCookie(cookie);
        }
    }
}
