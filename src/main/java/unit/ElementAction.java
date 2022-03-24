package unit;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ElementAction
 * @Description TODO
 * @Author Charles0219
 * @Date 2022/3/23 16:24
 * @Version 1.0
 **/
public class ElementAction extends BaseTestCase{
    public static ArrayList<Exception> noSuchElementExceptions=new ArrayList<Exception>();
    public List<WebElement> findElements(final Locator locator){
        List<WebElement>  webElements=null;
        try {
            webElements=(new WebDriverWait(webDriver, 20)).until(
                    new ExpectedCondition<List<WebElement>>() {

                        @Override
                        public List<WebElement> apply(WebDriver webDriver) {
                            // TODO 自动生成的方法存根
                            List<WebElement> element=null;
                            element=getElements(locator);
                            return element;}
                    });
            return webElements;
        }catch (NoSuchElementException e){
            e.printStackTrace();
            noSuchElementExceptions.add(e);
            return webElements;
        }
        catch (TimeoutException e){
            e.printStackTrace();
            noSuchElementExceptions.add(e);
            return webElements;
        }
        catch (ElementNotVisibleException e){
            e.printStackTrace();
            noSuchElementExceptions.add(e);
            return webElements;
        }

    }
    public WebElement findElement(final Locator locator){
        WebElement webElement=null;
        try {
            webElement=(new WebDriverWait(webDriver, 20)).until(
                    new ExpectedCondition<WebElement>() {

                        @Override
                        public WebElement apply(WebDriver driver) {
                            // TODO 自动生成的方法存根
                            WebElement element=null;
                            element=getElement(locator);
                            return element;
                        }
                    });
            return webElement;
        }catch (NoSuchElementException e){
            e.printStackTrace();
            noSuchElementExceptions.add(e);
            return webElement;
        }catch (TimeoutException e){
            e.printStackTrace();
            noSuchElementExceptions.add(e);
            return webElement;
        }catch (ElementNotVisibleException e){
            e.printStackTrace();
            noSuchElementExceptions.add(e);
            return webElement;
        }catch (Exception e){
            e.printStackTrace();
            noSuchElementExceptions.add(e);
            return webElement;
        }

    }

    private WebElement getElement(Locator locator) {
        WebElement webElement;
        switch (locator.getBy())
        {
            case xpath :
                webElement=webDriver.findElement(By.xpath(locator.getElement()));
                break;
            case id:
                webElement=webDriver.findElement(By.id(locator.getElement()));
                break;
            case cssSelector:
                webElement=webDriver.findElement(By.cssSelector(locator.getElement()));
                break;
            case name:
                webElement=webDriver.findElement(By.name(locator.getElement()));
                break;
            case className:
                webElement=webDriver.findElement(By.className(locator.getElement()));
                break;
            case linkText:
                webElement=webDriver.findElement(By.linkText(locator.getElement()));
                break;
            case partialLinkText:
                webElement=webDriver.findElement(By.partialLinkText(locator.getElement()));
                break;
            case tagName:
                webElement=webDriver.findElement(By.tagName(locator.getElement()));
                break;
            default :
                webElement=webDriver.findElement(By.xpath(locator.getElement()));
                break;
        }
            return webElement;
    }

    public List<WebElement> getElements(Locator locator) {
        List<WebElement> webElements;
        switch (locator.getBy()){
            case xpath :

                webElements=webDriver.findElements(By.xpath(locator.getElement()));
                break;
            case id:

                webElements=webDriver.findElements(By.id(locator.getElement()));
                break;
            case cssSelector:

                webElements=webDriver.findElements(By.cssSelector(locator.getElement()));
                break;
            case name:

                webElements=webDriver.findElements(By.name(locator.getElement()));
                break;
            case className:

                webElements=webDriver.findElements(By.className(locator.getElement()));
                break;
            case linkText:

                webElements=webDriver.findElements(By.linkText(locator.getElement()));
                break;
            case partialLinkText:

                webElements=webDriver.findElements(By.partialLinkText(locator.getElement()));
                break;
            case tagName:

                webElements=webDriver.findElements(By.partialLinkText(locator.getElement()));
                break;
            default :

                webElements=webDriver.findElements(By.xpath(locator.getElement()));
                break;
        }
        return webElements;
    }
    /**
     * 显式等待，程序休眠暂停
     * @param time 以秒为单位
     */
    public void sleep(long time)
    {
        try {
            Thread.sleep(time*1000);
        } catch (InterruptedException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }
    /**
     * 文本框输入操作
     * @param locator  元素locator
     * @param value 输入值
     */
    public void type(Locator locator,String value){
        try {
            WebElement webElement=findElement(locator);
            //文本框输入
            webElement.sendKeys(value);
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }

    }

    public void type_action(Locator locator,String value)
    {
        Actions actions =new Actions(webDriver);
        WebElement weElement=findElement(locator);
        actions.sendKeys(weElement, value);
    }
    /**
     * 普通单击操作
     * @param locator  元素locator
     */
    public  void click(Locator locator)
    {
        try {
            WebElement webElement=findElement(locator);
            webElement.click();
        } catch (NoSuchElementException e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }

    }






}
