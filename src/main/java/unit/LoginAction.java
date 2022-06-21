package unit;

import java.io.IOException;

/**
 * @ClassName LoginAction
 * @Description TODO
 * @Author Charles0219
 * @Date 2022/3/23 17:19
 * @Version 1.0
 **/
public class LoginAction extends BaseTestCase{

public LoginAction(String Url,String UserName,String PassWord) throws IOException {
    LoginPage loginPage=new LoginPage();
    loginPage.open(Url);
    ElementAction elementAction = new ElementAction();
    elementAction.click(loginPage.用户名输入框());
    elementAction.type(loginPage.用户名输入框(),"admin");
    elementAction.click(loginPage.密码输入框());
    elementAction.type(loginPage.密码输入框(),"admin123");
    elementAction.click(loginPage.登录按钮());

}

}
