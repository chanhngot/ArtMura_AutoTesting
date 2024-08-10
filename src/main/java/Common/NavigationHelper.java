package Common;

import PageObjects.Login;

import PageObjects.NavigateToLoginAdmin;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper {
    public static void navigateToShippingPage(WebDriver webdriver) {
        NavigateToLoginAdmin loginPage = new NavigateToLoginAdmin();
        loginPage.open();
        WaitTime.sleep(1000);
        Login login= new Login();
        login.login(Constant.Username, Constant.Password);
        WaitTime.sleep(2000);
        Constant.WEBDRIVER.findElement(By.xpath("//a[@href='/admin/shippings']")).click();
        WaitTime.sleep(5000);
    }
}
