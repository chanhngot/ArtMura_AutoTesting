package PageObjects;

import Common.Constant;
import Common.WaitTime;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.HashMap;

public class Login {
    private HashMap<String, By> loginMap;

    public Login() {
        loginMap = new HashMap<>();
        loginMap.put("txtUsername", By.xpath("//input[@name='username']"));
        loginMap.put("txtPassword", By.xpath("//input[@name='password']"));
        loginMap.put("btnLogin", By.xpath("//button[@type='submit']"));
        loginMap.put("txtAlert", By.xpath("//div[@role='alert']//div[2]"));
    }

    public WebElement getElementByLoginType(String type) {
        return Constant.WEBDRIVER.findElement(loginMap.get(type));
    }

    public void login(String username, String password) {
        this.getElementByLoginType("txtUsername").sendKeys(username);
        this.getElementByLoginType("txtPassword").sendKeys(password);
        this.getElementByLoginType("btnLogin").click();

    }

    public void loginTest(String username, String password, String expected) {
        this.getElementByLoginType("txtUsername").sendKeys(username);
        this.getElementByLoginType("txtPassword").sendKeys(password);
        this.getElementByLoginType("btnLogin").click();
        WaitTime.sleep(2000);
        String alertText = this.getElementByLoginType("txtAlert").getText();
        Assert.assertEquals(alertText, expected, "Alert text is not as expected");


    }
}




