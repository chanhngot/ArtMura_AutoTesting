package Testcases;

import Common.Constant;
import Common.NavigationHelper;
import Common.WaitTime;
import PageObjects.Shipping.DeleteShipping;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static Common.WaitTime.waitForElementVisible;

public class Delete {
    DeleteShipping deleteShipping = new DeleteShipping();

    @BeforeTest
    public void setUp() {
        WaitTime.sleep(1000);
        System.out.println("Pre Condition");
        System.setProperty("webdriver.chrome.driver", "browserDrivers/chromedriver.exe");
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
        WaitTime.sleep(1000);
        NavigationHelper.navigateToShippingPage(Constant.WEBDRIVER);
    }

    @BeforeMethod
    public void resetPage() {
        // Refresh the shipping page to ensure a clean state
        Constant.WEBDRIVER.navigate().refresh();
        WaitTime.sleep(1000);
    }

    @AfterTest
    public void cleanup() {
        System.out.println("Post Condition");
        if (Constant.WEBDRIVER != null) {
            Constant.WEBDRIVER.quit(); // Close the browser after all test methods
        }
    }

    @Test
    public void TC01() {
        System.out.println("TC33-Delete Shipping successfully");
        deleteShipping.deleteShipping("15");
        String alertText = Constant.WEBDRIVER.findElement(By.xpath("//div[@role='alert']//div[2]")).getText();
        Assert.assertEquals(alertText, "Delete Data Success", "Alert text is not as expected");
        Constant.WEBDRIVER.navigate().refresh();
        deleteShipping.Search("15");
        WebElement alertElement = Constant.WEBDRIVER.findElement(By.xpath("//td[@class='text-center text-2xl text-gray-500 font-medium pt-6 pb-6']"));
        waitForElementVisible(Constant.WEBDRIVER, alertElement, 10);
        String alertText_2 = alertElement.getText();
        Assert.assertEquals(alertText_2, "No Shippings", "Alert text is not as expected");
    }


    @Test
    public void TC02() {
        System.out.println("TC34-Delete Shipping unsuccessfully by non-exist OrderID");
        deleteShipping.deleteShipping("14");
        WebElement alertElement = Constant.WEBDRIVER.findElement(By.xpath("//td[@class='text-center text-2xl text-gray-500 font-medium pt-6 pb-6']"));
        waitForElementVisible(Constant.WEBDRIVER, alertElement, 10);
        String alertText = alertElement.getText();
        Assert.assertEquals(alertText, "No Shippings", "Alert text is not as expected");
    }


}