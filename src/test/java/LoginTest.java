import org.dom4j.DocumentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import unit.*;

import java.io.IOException;

/**
 * @ClassName LoginTest
 * @Description TODO
 * @Author Charles0219
 * @Date 2022/3/24 11:11
 * @Version 1.0
 **/

public class LoginTest extends BaseTestCase {
    ElementAction elementAction = new ElementAction();

    //登录成功用例
    @Test
    @Parameters({"BaseUrl"})
    public void login(String BaseUrl)throws IOException {
        LoginAction loginAction=new LoginAction(BaseUrl,"admin\n" +
                "","admin123");
    }
    @DataProvider(name="longinData")
    public Object[][] loginData(){
        String filePath="src/main/resources/涉及应用JVM配置优化.xlsx";
        int rows[] = {2,3};
        int [] cells = {3,4};

        return ExcelReadUtil.seach(filePath,"Sheet2",rows,cells);
    }
    //登录失败用例
    @Test(dataProvider="longinData")
    public void loginFail(String userName,String password) throws IOException, DocumentException {
        String BaseUrl= XmlReadUtil.getTestngParametersValue("testng.xml","BaseUrl");
        LoginAction loginAction=new LoginAction(BaseUrl,userName,password);
        elementAction.sleep(2);
    }

}
