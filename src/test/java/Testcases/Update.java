package Testcases;

import Common.Constant;
import Common.NavigationHelper;
import Common.WaitTime;
import PageObjects.Shipping.UpdateShipping;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;

import static Common.WaitTime.waitForElementVisible;

public class Update {
    UpdateShipping updateShipping =new UpdateShipping();


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
        System.out.println("TC01-Verify that the [EDIT SHIPPING] pop up displays when user click icon update");
        Constant.WEBDRIVER.findElement(By.xpath("(//button[@type='button'])[2]")).click();
        WaitTime.sleep(2000);
        String popupText = Constant.WEBDRIVER.findElement(By.xpath("//div[@class='smoothLoading flex items-center justify-between mb-3']//h1")).getText();
        Assert.assertEquals(popupText, "Edit Shipping", "Popuptext is not as expected");
        System.out.println("actual: " + popupText);
        System.out.println("expexced: Edit Shipping");
    }

    @Test
    public void TC02() {
        System.out.println("TC02-Verify that user can back [Shipping Management] page when click [x] in pop up edit shipping ");
        Constant.WEBDRIVER.findElement(By.xpath("(//button[@type='button'])[2]")).click();
        WaitTime.sleep(2000);
        Constant.WEBDRIVER.findElement(By.xpath("//div[@class='smoothLoading flex items-center justify-between mb-3']//button")).click();
        WaitTime.sleep(2000);
        String popupText = Constant.WEBDRIVER.findElement(By.xpath("//div[@class='flex items-center justify-start']/h1")).getText();
        Assert.assertEquals(popupText, "Shippings", "Text is not as expected");
        System.out.println("actual: " + popupText);
        System.out.println("expexced: Shippings");
    }

    @Test
    public void TC03() {
        System.out.println("TC03-Verify that user can back [Shipping Management] page when click [CANCEL] in pop Edit shipping ");
        Constant.WEBDRIVER.findElement(By.xpath("(//button[@type='button'])[2]")).click();
        WaitTime.sleep(2000);
        Constant.WEBDRIVER.findElement(By.xpath("//div[@class='flex justify-end']/button[@type='button']")).click();
        WaitTime.sleep(2000);
        String popupText = Constant.WEBDRIVER.findElement(By.xpath("//div[@class='flex items-center justify-start']/h1")).getText();
        Assert.assertEquals(popupText, "Shippings", "Text is not as expected");
        System.out.println("actual: " + popupText);
        System.out.println("expexced: Shippings");
    }

    @Test
    public void TC04() {
        System.out.println("TC04-Update Shipping unsuccessfully when input the space at the beginning of the text into textbox [STREET ADDRESS]");
        HashMap<String, String> Data = new HashMap<>();
        Data.put("address", "    allo");
        HashMap<String, String> setToSearchType = new HashMap<>();
        setToSearchType.put("address", "searchAddress");
        updateShipping.updateAndSearch(Data, "119");
        WebElement alertElement = Constant.WEBDRIVER.findElement(By.xpath("//span[@class='text-red-600 mt-2 inline-block']"));
        waitForElementVisible(Constant.WEBDRIVER, alertElement, 10);
        String alertText = alertElement.getText();
        Assert.assertEquals(alertText, "Please do not insert space at the beginning or end", "Alert text is not as expected");
    }

    @Test
    public void TC05() {
        System.out.println("TC05-Update Shipping unsuccessfully when input the space at the ending of the text into textbox [STREET ADDRESS]");
        HashMap<String, String> Data = new HashMap<>();
        Data.put("address", "allo      ");
        HashMap<String, String> setToSearchType = new HashMap<>();
        setToSearchType.put("address", "searchAddress");
        updateShipping.updateAndSearch(Data, "119");
        WebElement alertElement = Constant.WEBDRIVER.findElement(By.xpath("//span[@class='text-red-600 mt-2 inline-block']"));
        waitForElementVisible(Constant.WEBDRIVER, alertElement, 10);
        String alertText = alertElement.getText();
        Assert.assertEquals(alertText, "Please do not insert space at the beginning or end", "Alert text is not as expected");
    }

    @Test
    public void TC06() {
        System.out.println("TC06-Update Shipping unsuccessfully when input special charaters or emoji into textbox [STREET ADDRESS]");
        HashMap<String, String> Data = new HashMap<>();
        Data.put("address", "$$##@@");
        HashMap<String, String> setToSearchType = new HashMap<>();
        setToSearchType.put("address", "searchAddress");
        updateShipping.updateAndSearch(Data, "119");
        WebElement alertElement = Constant.WEBDRIVER.findElement(By.xpath("//span[@class='text-red-600 mt-2 inline-block']"));
        waitForElementVisible(Constant.WEBDRIVER, alertElement, 10);
        String alertText = alertElement.getText();
        Assert.assertEquals(alertText, "Please do not insert special characters or emojis", "Alert text is not as expected");
    }

    @Test
    public void TC07() {
        System.out.println("TC07-Update Shipping unsuccessfully when input more than 255 charaters into textbox [STREET ADDRESS]");
        HashMap<String, String> Data = new HashMap<>();
        Data.put("address", Constant.More255characters);
        HashMap<String, String> setToSearchType = new HashMap<>();
        setToSearchType.put("address", "searchAddress");
        updateShipping.updateAndSearch(Data, "119");
        WebElement alertElement = Constant.WEBDRIVER.findElement(By.xpath("//span[@class='text-red-600 mt-2 inline-block']"));
        waitForElementVisible(Constant.WEBDRIVER, alertElement, 10);
        String alertText = alertElement.getText();
        Assert.assertEquals(alertText, "Street address must be at most 255 characters", "Alert text is not as expected");
    }

    @Test
    public void TC08() {
        System.out.println("TC08-Update Shipping unsuccessfully when input the space at the beginning of the text into textbox [DESCRIPTION]");
        HashMap<String, String> Data = new HashMap<>();
        Data.put("description", "   Test Description");
        HashMap<String, String> setToSearchType = new HashMap<>();
        setToSearchType.put("description", "searchDescription");
        updateShipping.updateAndSearch(Data, "119");
        WebElement alertElement = Constant.WEBDRIVER.findElement(By.xpath("//span[@class='text-red-600 mt-2 inline-block']"));
        waitForElementVisible(Constant.WEBDRIVER, alertElement, 10);
        String alertText = alertElement.getText();
        Assert.assertEquals(alertText, "Please do not insert space at the beginning or end", "Alert text is not as expected");
    }

    @Test
    public void TC09() {
        System.out.println("TC09-Update Shipping unsuccessfully when input more than 1000 charaters into textbox [DESCRIPTION]");
        HashMap<String, String> Data = new HashMap<>();
        Data.put("description", Constant.More1000characters);
        HashMap<String, String> setToSearchType = new HashMap<>();
        setToSearchType.put("description", "setDescription");
        updateShipping.updateAndSearch(Data, "119");
        WebElement alertElement = Constant.WEBDRIVER.findElement(By.xpath("//span[@class='text-red-600 mt-2 inline-block']"));
        waitForElementVisible(Constant.WEBDRIVER, alertElement, 10);
        String alertText = alertElement.getText();
        Assert.assertEquals(alertText, "Description must be at most 1000 characters", "Alert text is not as expected");
    }

    @Test
    public void TC10() {
        System.out.println("TC10-Update Shipping unsuccessfully when input the space at the ending of the text into textbox [DESCRIPTION ]");
        HashMap<String, String> Data = new HashMap<>();
        Data.put("description", "Test Description      ");
        HashMap<String, String> setToSearchType = new HashMap<>();
        setToSearchType.put("description", "searchDescription");
        updateShipping.updateAndSearch(Data, "119");
        WebElement alertElement = Constant.WEBDRIVER.findElement(By.xpath("//span[@class='text-red-600 mt-2 inline-block']"));
        waitForElementVisible(Constant.WEBDRIVER, alertElement, 10);
        String alertText = alertElement.getText();
        Assert.assertEquals(alertText, "Please do not insert space at the beginning or end", "Alert text is not as expected");
    }

    @Test
    public void TC11() {
        System.out.println("TC11-Update Shipping unsuccessfully when input special charaters or emoji into textbox [DESCRIPTION]");
        HashMap<String, String> Data = new HashMap<>();
        Data.put("description", "$$##@@");
        HashMap<String, String> setToSearchType = new HashMap<>();
        setToSearchType.put("description", "searchDescription");
        updateShipping.updateAndSearch(Data, "119");
        WebElement alertElement = Constant.WEBDRIVER.findElement(By.xpath("//span[@class='text-red-600 mt-2 inline-block']"));
        waitForElementVisible(Constant.WEBDRIVER, alertElement, 10);
        String alertText = alertElement.getText();
        Assert.assertEquals(alertText, "Please do not insert special characters or emojis", "Alert text is not as expected");
    }

    @Test
    public void TC12() {
        System.out.println("TC12-Update Shipping successfully");
        HashMap<String, String> Data = new HashMap<>();
        Data.put("address", "243 Phan Dang Luu");
        HashMap<String, String> setToSearchType = new HashMap<>();
        setToSearchType.put("address", "searchAddress");
        updateShipping.updateAndSearch(Data, "119");
        WebElement alertElement = Constant.WEBDRIVER.findElement(By.xpath("//div[@role='alert']//div[2]"));
        waitForElementVisible(Constant.WEBDRIVER, alertElement, 10);
        String alertText = alertElement.getText();
        Assert.assertEquals(alertText, "Update Data Success", "Alert text is not as expected");
        Constant.WEBDRIVER.navigate().refresh();
        HashMap<String, String> SearchData = new HashMap<>();
        for (HashMap.Entry<String, String> entry : Data.entrySet()) {
            String fieldName = entry.getKey();
            String value = entry.getValue();
            SearchData.put(setToSearchType.get(fieldName), value);
        }
        updateShipping.Search("119", SearchData);
    }

}