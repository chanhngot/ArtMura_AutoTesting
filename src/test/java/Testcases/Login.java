package Testcases;

import Common.Constant;
import Common.WaitTime;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class Login {
    private PageObjects.Login loginTest;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "browserDrivers/chromedriver.exe");
        Common.Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.get("http://192.168.3.220:3004/admin/login");
        Constant.WEBDRIVER.manage().window().maximize();
        WaitTime.sleep(5000);
        loginTest = new PageObjects.Login();
    }

    @Test
    public void TC01() {
        System.out.println("TC01-Login successfully");
        loginTest.loginTest("admin", "Hello123", "Login Success");
    }
    @Test
    public void TC02() {
        System.out.println("TC02-Login unsuccessfully when input the wrong password");
        loginTest.loginTest("admin", "Hello123n", "validation.login.credential_wrong");
    }

    @Test
    public void TC03() {
        System.out.println("TC03-Login unsuccessfully when input the wrong username");
        loginTest.loginTest("admin", "Hello123", "validation.login.credential_wrong");
    }

    @Test
    public void TC04() {
        System.out.println("TC04-Login unsuccessfully when input space at the beginning of the text");
        loginTest.loginTest(" admin", "Hello123", "validation.login.credential_wrong");
    }
    @Test
    public void TC05() {
        System.out.println("TC05-Login unsuccessfully when input special characters");
        loginTest.loginTest(" ", " ", "validation.login.credential_wrong");
    }
    @Test
    public void TC06() {
        System.out.println("TC06-Login unsuccessfully when input special characters in username");
        loginTest.loginTest("~!#$%^&(),<>admin", "Hello123", "validation.login.credential_wrong");
    }

    @AfterMethod
    public void cleanup() {
        if (Constant.WEBDRIVER != null) {
            Constant.WEBDRIVER.close(); // Close the browser after each test method
        }
        System.out.println("End");
    }
}
