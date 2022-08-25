package org.diplom.selenium.authorization;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.firefox.FirefoxDriver;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthorizationFirefoxTest extends AuthorizationChromeTest {

    /**
     * Настройка и создание драйвера браузера
     */
    public void settingBrowser() {
        System.setProperty("webdriver.gecko.driver", "src/resources/firefox/geckodriver.exe");
        driver = new FirefoxDriver();
    }
}
