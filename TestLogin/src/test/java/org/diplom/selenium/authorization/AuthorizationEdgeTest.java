package org.diplom.selenium.authorization;

import org.junit.jupiter.api.*;
import org.openqa.selenium.edge.EdgeDriver;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthorizationEdgeTest extends AuthorizationChromeTest {

    /**
     * Настройка и создание драйвера браузера
     */
    public void settingBrowser() {
        System.setProperty("webdriver.edge.driver", "src/resources/edge/msedgedriver.exe");
        driver = new EdgeDriver();
    }
}
