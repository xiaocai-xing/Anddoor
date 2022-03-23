package com.wan.door.controller;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import unit.BaseTestCase;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName WebDriver
 * @Description TODO
 * @Author Charles0219
 * @Date 2022/3/21 11:31
 * @Version 1.0
 **/
public class WebDriverTest  {


    public static String url = "http://localhost:8080/#/login";
    public static String method = "/api/AccountLogin";
    public static String data = "{\"nameUser\":\"admin\",\"passwordUser\":\"admin123\"}";
    public static void ChromeTest() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedr.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(url);  //输入网址
        driver.manage().window().maximize();  //窗口最大化
        Thread.sleep(1000);


        driver.findElement(By.id("inputloginname")).sendKeys("admin");
        driver.findElement(By.id("inputloginpwd")).sendKeys("admin123");

        driver.close();
    }
    public static void Firefoxtest() throws InterruptedException {
        System.setProperty("webdriver.gecko.driver","src/main/resources/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();  //窗口最大化
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(url);  //输入网址
        Thread.sleep(1000);
        driver.close();
    }

    public static void main(String[] args) throws InterruptedException {
//        WebDriverTest webDriverTest = new WebDriverTest();
        WebDriverTest.ChromeTest();
//        WebDriverTest.Firefoxtest();

    }
}
