package unit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

/**
 * @ClassName BaseTestCase
 * @Description TODO
 * @Author Charles0219
 * @Date 2022/3/22 15:55
 * @Version 1.0
 **/
public class BaseTestCase {

    public static WebDriver webDriver;
    public static String description;
    @BeforeTest
    @Parameters({"webDriver","nodeURL"})

    public void setup(String driver,String nodeURL){
        if (nodeURL.equals("")||nodeURL.isEmpty()){
            try {
                this.webDriver=SelectDriver(driver);
            } catch (Exception e) {

                e.printStackTrace();
            }
            System.out.println(nodeURL);
            this.webDriver.manage().window().maximize();
        }else {
            try {
                this.webDriver=SelectDriver(driver);
            } catch (Exception e) {

            }
            this.webDriver.manage().window().maximize();
        }
    }

    @AfterTest
    public void tearDown() {
        this.webDriver.close();
        this.webDriver.quit();
    }


    public  WebDriver SelectDriver(String webdriver){
        switch(webdriver){
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedr.exe");
                this.webDriver = new ChromeDriver();
                break;
            case"firefox":
                System.setProperty("webdriver.chrome.driver", "src/main/resources/geckodriver.exe");
                this.webDriver = new FirefoxDriver();
                break;
        }
        return webDriver;
    }
}
