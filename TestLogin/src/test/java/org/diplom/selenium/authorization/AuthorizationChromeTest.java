package org.diplom.selenium.authorization;

import org.diplom.selenium.Data;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthorizationChromeTest {
    // ссылка для веб драйвера
    protected static WebDriver driver = null;

    // ссылки для элементов на сайте
    // кнопка Log in
    private final By btnLogin = By.xpath("//div[@class='a6jUt']//a[@href='https://unsplash.com/login']");
    // поле ввода email
    private final By inputEmail = By.xpath("//div[@class='form-group']//input[@id='user_email']");
    // поле ввода пароля
    private final By inputPassword = By.xpath("//div[@class='form-group']//input[@id='user_password']");
    // кнопка Join
    private final By btnJoin = By.xpath("//div[@class='form-group']//input[@class='btn btn-default btn-block-level']");
    // кнопка профиля
    private final By btnProfile = By.xpath("//div[@id='popover-avatar-loggedin-menu-desktop']//button[@type='button']");
    // сообщение об ошибке
    private final By errorTextEl = By.xpath("//div[@class='row']//div[@class='col-xs-10 col-sm-6 center-block flash__message']");

    // ссылка на экземпляр Data
    private final Data data = new Data();

    /**
     * Настройка и создание драйвера браузера
     */
    public void settingBrowser() {
        System.setProperty("webdriver.chrome.driver", "src/resources/chrome/chromedriver.exe");
        driver = new ChromeDriver();
    }

    /**
     * Открываем бразуер
     */
    public void openBrowser() {
        // настраиваем браузер
        settingBrowser();
        // открываем путь и раскрываем окно на весь экран
        driver.get(data.url);
        driver.manage().window().maximize();
    }

    @Test
    @Order(1)
    public void negative() {
        try {
            // открываем страницу
            openBrowser();
            // нажимаем кнопку Log In
            driver.findElement(btnLogin).click();
            // заполняем поля логина и пароля
            driver.findElement(inputEmail).sendKeys(data.failEmail);
            driver.findElement(inputPassword).sendKeys(data.failPassword);
            // нажимаем кнопку логина
            driver.findElement(btnJoin).click();
            // проверяем результат
            String errorText = driver.findElement(errorTextEl).getText();
            Assertions.assertEquals(data.errorText, errorText);
        } finally {
            // закрываем
            driver.close();
        }
    }

    @Test
    @Order(2)
    public void positive() {
        try {
            // открываем страницу
            openBrowser();
            // нажимаем кнопку Log In
            driver.findElement(btnLogin).click();
            // заполняем поля логина и пароля
            driver.findElement(inputEmail).sendKeys(data.email);
            driver.findElement(inputPassword).sendKeys(data.password);
            // нажимаем кнопку логина
            driver.findElement(btnJoin).click();
            // проверяем кнопку пользователя на сайте
            WebElement profile = driver.findElement(btnProfile);
            // если она есть - мы залогинились
            Assertions.assertNotNull(profile);
        } finally {
            // закрываем
            driver.close();
        }
    }
}
