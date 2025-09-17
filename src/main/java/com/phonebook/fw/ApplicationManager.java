package com.phonebook.fw;

import com.phonebook.utils.MyListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;


public class ApplicationManager {
    WebDriver driver;
    String browser;
    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);

    UserHelper user;
    ContactHelper contact;
    HomePageHelper home;

    public void init() {

        if(browser.equalsIgnoreCase("chrome")){
            driver = new ChromeDriver();
            logger.info("Tests start in Chrome browser");
        }else  if (browser.equalsIgnoreCase("firefox")){
            driver = new FirefoxDriver();
            logger.info("Tests start in Firefox browser");
        }

        WebDriverListener listener = new MyListener();
        driver = new EventFiringDecorator<>(listener).decorate(driver);
        driver = new ChromeDriver();

        driver.get("https://telranedu.web.app");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        user = new UserHelper(driver);
        contact = new ContactHelper(driver);
        home = new HomePageHelper(driver);

    }

    public void stop() {
        driver.quit();
    }

    public UserHelper getUser() {
        return user;
    }

    public ContactHelper getContact() {
        return contact;
    }

    public HomePageHelper getHome() {
        return home;
    }
}
