package unit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;

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
    @Parameters({"driver","nodeURL"})
    public void setup(String driver,String nodeURL)throws MalformedURLException {
        if (nodeURL.equals("")||nodeURL.isEmpty()){
            try {
                this.webDriver=SelectDriver(driver);
            } catch (Exception e) {

                e.printStackTrace();
            }

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


    public  WebDriver SelectDriver(String browsername){
        switch(browsername){
            case "ChromeDriver":
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedr.exe");
                this.webDriver = new ChromeDriver();
                break;
            case"FirefoxDriver":
                System.setProperty("webdriver.chrome.driver", "src/main/resources/geckodriver.exe");
                this.webDriver = new FirefoxDriver();
                break;
        }
        return webDriver;
    }
//    public static void main(String args[])
//    {
//        WebDriver driver2=new ChromeDriver();
//        driver2.get("http://www.baidu.com");
//    }
}
