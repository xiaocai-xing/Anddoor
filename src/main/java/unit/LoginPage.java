package unit;

import java.io.IOException;

/**
 * @ClassName LoginPage
 * @Description TODO
 * @Author Charles0219
 * @Date 2022/3/23 14:47
 * @Version 1.0
 **/
public class LoginPage extends BaseAction{
    private String path = "src/main/java/unit/UserLocator.xml";

    public   LoginPage() {
//工程内读取对象库文件
        setXmlObjectPath(path);
        getLocatorMap();
    }
    public Locator 用户名输入框() throws IOException
    {
        Locator locator=getLocator("用户名输入框");
        return locator;
    }

    public Locator 密码输入框() throws IOException
    {
        Locator locator=getLocator("密码输入框");
        return locator;
    }

}
