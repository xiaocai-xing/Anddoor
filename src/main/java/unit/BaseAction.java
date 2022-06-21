package unit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName BaseAction
 * @Description TODO
 * @Author Charles0219
 * @Date 2022/3/23 14:16
 * @Version 1.0
 **/
public class BaseAction extends BaseTestCase{
    protected HashMap<String,Locator> locatorMap;
    public String path=null;
    public InputStream path_inputStream_1;

    //打开浏览器
    public void open(String url){
//        webDriver.navigate().to("http://10.1.134.68:8080/#/");
        webDriver.navigate().to(url);
        System.out.println("打卡网址");
    }
    //关闭浏览器
    public void close(){
        webDriver.close();
    }

    //退出浏览器
    public void quit(){
        webDriver.quit();
    }
    static By getBy (Locator.ByType byType, Locator locator)
    {
        switch(byType)
        {
            case id:
                return By.id(locator.getElement());
            case cssSelector:
                return By.cssSelector(locator.getElement());
            case name:
                return By.name(locator.getElement());
            case xpath:
                return By.xpath(locator.getElement());
            case className:
                return By.className(locator.getElement());
            case tagName:
                return By.tagName(locator.getElement());
            case linkText:
                return By.linkText(locator.getElement());
            case partialLinkText:
                return By.partialLinkText(locator.getElement());
            //return null也可以放到switch外面
            default:
                return null;
        }


    }
    public WebElement findElement(final Locator locator) throws IOException
    {
        /**
         * 查找某个元素等待几秒
         */
        Waitformax(Integer.valueOf(locator.getWaitSec()));
        WebElement webElement;
        webElement=getElement(locator);
        return webElement;
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

                webElement=webDriver.findElement(By.partialLinkText(locator.getElement()));
                break;
            default :

                webElement=webDriver.findElement(By.xpath(locator.getElement()));
                break;
        }
        return webElement;
    }

    private void Waitformax(int t) {
        webDriver.manage().timeouts().implicitlyWait(t, TimeUnit.SECONDS);
    }
    public  void setXmlObjectPathForLocator(InputStream path_inputStream)
    {
        this.path_inputStream_1=path_inputStream;
    }

    public  BaseAction()
    {


    }
    public void getLocatorMap(){
        XmlReadUtil xmlReadUtil = new XmlReadUtil();
        try {
            if((path==null||path.isEmpty()))
            {locatorMap = xmlReadUtil.readXMLDocument(path_inputStream_1, this.getClass().getCanonicalName());}
            else {
                locatorMap = xmlReadUtil.readXMLDocument(path, this.getClass().getCanonicalName());
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public  Locator getLocator(String locatorName)
    {
        Locator locator;
        /**
         * 在对象库通过对象名字查找定位信息
         */
        locator=locatorMap.get(locatorName);
        /**
         * 加入对象库，找不到该定位信息，就创建一个定位信息
         */
        if(locator==null)
        {
            System.out.println("没有找到"+locatorName+"页面元素");
        }
        return locator;

    }
    public String getPageURL()
    {
        String pageURL=null;
        try {
            if(path==null||path.isEmpty())
            {pageURL=XmlReadUtil.getXmlPageURL(path_inputStream_1, this.getClass().getCanonicalName());}
            else {
                pageURL=XmlReadUtil.getXmlPageURL(path, this.getClass().getCanonicalName());
            }
        } catch (Exception e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return pageURL;
    }
    public  void setXmlObjectPath(String path)
    {
        this.path=path;
    }
}
