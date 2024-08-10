package Testcases;

import Common.Constant;
import Common.NavigationHelper;
import Common.WaitTime;
import PageObjects.Shipping.AddShipping;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import static Common.WaitTime.waitForElementVisible;

public class Add {
    AddShipping addShipping = new AddShipping();


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


    //thêm
    @Test
    public void TC01() {
        System.out.println("TC01-Verify that the [ADD NEW SHIPPING] pop up displays when user click [+] button");
        Constant.WEBDRIVER.findElement(By.xpath("(//button[contains(@type,'button')])[1]")).click();
        WaitTime.sleep(2000);
        String popupText = Constant.WEBDRIVER.findElement(By.xpath("//div[@class='smoothLoading flex items-center justify-between mb-3']//h1")).getText();
        Assert.assertEquals(popupText, "Add New Shipping", "Popuptext is not as expected");
        System.out.println("actual: " + popupText);
        System.out.println("expexced: Add New Shipping");

    }

    //thêm
    @Test
    public void TC02() {
        System.out.println("TC02-Verify that user can back [Shipping Management] page when click [x] in pop up add new shipping ");
        Constant.WEBDRIVER.findElement(By.xpath("(//button[contains(@type,'button')])[1]")).click();
        WaitTime.sleep(2000);
        Constant.WEBDRIVER.findElement(By.xpath("//div[@class='smoothLoading flex items-center justify-between mb-3']//button")).click();
        WaitTime.sleep(2000);
        String popupText = Constant.WEBDRIVER.findElement(By.xpath("//div[@class='flex items-center justify-start']/h1")).getText();
        Assert.assertEquals(popupText, "Shippings", "Text is not as expected");
        System.out.println("actual: " + popupText);
        System.out.println("expexced: Shippings");
    }

    //thêm
    @Test
    public void TC03() {
        System.out.println("TC03-Verify that user can back [Shipping Management] page when click [CANCEL] in pop up add new shipping ");
        Constant.WEBDRIVER.findElement(By.xpath("(//button[contains(@type,'button')])[1]")).click();
        WaitTime.sleep(2000);
        Constant.WEBDRIVER.findElement(By.xpath("//div[@class='flex justify-end']/button[@type='button']")).click();
        WaitTime.sleep(2000);
        String popupText = Constant.WEBDRIVER.findElement(By.xpath("//div[@class='flex items-center justify-start']/h1")).getText();
        Assert.assertEquals(popupText, "Shippings", "Text is not as expected");
        System.out.println("actual: " + popupText);
        System.out.println("expexced: Shippings");
    }

    //     Bỏ ID fit vào là chạy thành công.
    //sửa lại ORDER_ID
    //thiếu bước xem l nó đã có trong bảng chưa
    //thêm thành công
    @Test
    public void TC04() {
        System.out.println("TC04-Add new Shipping successfully");
        addShipping.fillShippingForm("Shipping", "Thanh Hóa", "Thạch Thành", "Kim Tân", "Xóm 1", "02/05/2023", "05/05/2023", "205", "đã done");
        WaitTime.sleep(2000);
        String alertText = Constant.WEBDRIVER.findElement(By.xpath("//div[@role='alert']//div[2]")).getText();
        Assert.assertEquals(alertText, "Save Data Success", "Alert text is not as expected");
        System.out.println("actual: " + alertText);
        System.out.println("expexced: Save Data Success");

    }

    @Test
    public void TC05() {
        System.out.println("TC05- Add new Shipping unsuccessfully due to a value is not selected in the dropdown list: PROVINCE");
        addShipping.fillShippingForm("Shipping", null, null, null, "Xóm 1", "3/05/2023", "5/05/2023", "206", "Made in Japan");
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, 10);
        WebElement alertElement1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Province cannot be empty']")));
        WebElement alertElement2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='District cannot be empty']")));
        WebElement alertElement3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Ward cannot be empty']")));
        String alertText1 = alertElement1.getText();
        String alertText2 = alertElement2.getText();
        String alertText3 = alertElement3.getText();
        Assert.assertEquals(alertText1, "Province cannot be empty", "Alert text is not as expected");
        Assert.assertEquals(alertText2, "District cannot be empty", "Alert text is not as expected");
        Assert.assertEquals(alertText3, "Ward cannot be empty", "Alert text is not as expected");
    }

    //thêm
    @Test
    public void TC06() {
        System.out.println("TC06-Add new Shipping unsuccessfully due to a value is not selected in the dropdown list: DISTRICT");
        addShipping.fillShippingForm("Shipping", "Sơn La", null, null, "Xóm 1", "03/05/2023", "05/05/2023", "206", "Made in Japan");
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, 10);
        WebElement alertElement2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='District cannot be empty']")));
        WebElement alertElement3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Ward cannot be empty']")));
        String alertText2 = alertElement2.getText();
        String alertText3 = alertElement3.getText();
        Assert.assertEquals(alertText2, "District cannot be empty", "Alert text is not as expected");
        Assert.assertEquals(alertText3, "Ward cannot be empty", "Alert text is not as expected");
    }


    //thêm
    @Test
    public void TC07() {
        System.out.println("TC07-Add new Shipping unsuccessfully due to a value is not selected in the dropdown list:WARD");
        addShipping.fillShippingForm("Shipping", "Sơn La", "Quỳnh Nhai", null, "Xóm 1", "03/05/2023", "05/05/2023", "19", "Made in Japan");
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, 10);
        WebElement alertElement3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Ward cannot be empty']")));
        String alertText3 = alertElement3.getText();
        Assert.assertEquals(alertText3, "Ward cannot be empty", "Alert text is not as expected");
    }

    @Test
    public void TC08() {
        System.out.println("TC08-Add new Shipping unsuccessfully When user do not input anything into textboxt: [ADDRESS]");
        addShipping.fillShippingForm("Shipping", "Thanh Hóa", "Thạch Thành", "Kim Tân", null, "2/05/2023", "5/05/2023", "206", "đã done");
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, 10);
        WebElement alertElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Street address cannot be empty']")));
        String alertText = alertElement.getText();
        Assert.assertEquals(alertText, "Street address cannot be empty", "Alert text is not as expected");
    }

    @Test
    public void TC09() {
        System.out.println("TC09- Add new Shipping unsuccessfully when don't selected ORDER_ID");
        addShipping.fillShippingForm("Shipping", "Thanh Hóa", "Thạch Thành", "Kim Tân", "Xóm 1", "02/05/2023", "05/05/2023", null, "đã done");
        String alertText = Constant.WEBDRIVER.findElement(By.xpath("//span[normalize-space()='You must select an order ID']")).getText();
        Assert.assertEquals(alertText, "You must select an order ID", "Alert text is not as expected");
    }

    @Test
    public void TC10() {
        System.out.println("TC10- Add new Shipping unsuccessfully When user do not input anything into textboxt: [Start Date]");
        addShipping.fillShippingForm("Shipping", "Thanh Hóa", "Thạch Thành", "Kim Tân", "Xóm 1", null, "5/05/2023", "206", "đã done");
        String alertText = Constant.WEBDRIVER.findElement(By.xpath("//span[normalize-space()='Start date cannot be empty']")).getText();
        Assert.assertEquals(alertText, "Start date cannot be empty", "Alert text is not as expected");
    }

    @Test
    public void TC11() {
        System.out.println("TC11- Add new Shipping unsuccessfully When user do not input anything into textboxt: [Arrival Date]");
        addShipping.fillShippingForm("Shipping", "Thanh Hóa", "Thạch Thành", "Kim Tân", "Xóm 1", "5/05/2023", null, "206", "đã done");
        String alertText = Constant.WEBDRIVER.findElement(By.xpath("//span[normalize-space()='Arrival date cannot be empty']")).getText();
        Assert.assertEquals(alertText, "Arrival date cannot be empty", "Alert text is not as expected");
    }

    //thêm
    @Test
    public void TC12() {
        System.out.println("TC12-Add new Shipping unsuccessfully When user input special characters or emojis into textbox: [ADDRESS]");
        addShipping.fillShippingForm("Shipping", "Thanh Hóa", "Thạch Thành", "Kim Tân", "$$@##^&%", "2/05/2023", "5/05/2023", "206", "đã done");
        WebElement alertElement = Constant.WEBDRIVER.findElement(By.xpath("//span[normalize-space()='Please do not insert special characters or emojis']"));
        waitForElementVisible(Constant.WEBDRIVER, alertElement, 10);
        String alertText = alertElement.getText();
        Assert.assertEquals(alertText, "Please do not insert special characters or emojis", "Alert text is not as expected");
    }

    //thêm
    @Test
    public void TC13() {
        System.out.println("TC13-Add new Shipping unsuccessfully When user input the space at the beginning of the textbox: [ADDRESS]");
        addShipping.fillShippingForm("Shipping", "Thanh Hóa", "Thạch Thành", "Kim Tân", "  nguyen doán", "2/05/2023", "5/05/2023", "206", "đã done");
        WebElement alertElement = Constant.WEBDRIVER.findElement(By.xpath("//span[@class='text-red-600 mt-2 inline-block']"));
        waitForElementVisible(Constant.WEBDRIVER, alertElement, 10);
        String alertText = alertElement.getText();
        Assert.assertEquals(alertText, "Please do not insert space at the beginning or end", "Alert text is not as expected");
    }

    //thêm
    @Test
    public void TC14() {
        System.out.println("TC14-Add new Shipping unsuccessfully When user input the space at the ending of the textbox: [ADDRESS]");
        addShipping.fillShippingForm("Shipping", "Thanh Hóa", "Thạch Thành", "Kim Tân", "nguyen doán    ", "2/05/2023", "5/05/2023", "206", "đã done");
        WebElement alertElement = Constant.WEBDRIVER.findElement(By.xpath("//span[@class='text-red-600 mt-2 inline-block']"));
        waitForElementVisible(Constant.WEBDRIVER, alertElement, 10);
        String alertText = alertElement.getText();
        Assert.assertEquals(alertText, "Please do not insert space at the beginning or end", "Alert text is not as expected");
    }

    //thêm
    @Test
    public void TC15() {
        System.out.println("TC15-Add new Shipping unsuccessfully When user input more than 255 charaters of the textbox: [ADDRESS]");
        addShipping.fillShippingForm("Shipping", "Thanh Hóa", "Thạch Thành", "Kim Tân", Constant.More255characters, "2/05/2023", "5/05/2023", "206", "đã done");
        WebElement alertElement = Constant.WEBDRIVER.findElement(By.xpath("//span[@class='text-red-600 mt-2 inline-block']"));
        waitForElementVisible(Constant.WEBDRIVER, alertElement, 10);
        String alertText = alertElement.getText();
        Assert.assertEquals(alertText, "Street address must be at most 255 characters", "Alert text is not as expected");
    }

    //thêm
    @Test
    public void TC16() {
        System.out.println("TC16-Add new Shipping unsuccessfully When user input more than 1000 charaters of the textbox: [Description]");
        addShipping.fillShippingForm("Shipping", "Thanh Hóa", "Thạch Thành", "Kim Tân", "An Vương", "2/05/2023", "5/05/2023", "206", Constant.More1000characters);
        WebElement alertElement = Constant.WEBDRIVER.findElement(By.xpath("//span[@class='text-red-600 mt-2 inline-block']"));
        waitForElementVisible(Constant.WEBDRIVER, alertElement, 10);
        String alertText = alertElement.getText();
        Assert.assertEquals(alertText, "Description must be at most 1000 characters", "Alert text is not as expected");
    }

    ///thêm
    @Test
    public void TC17() {
        System.out.println("TC17-Add new Shipping unsuccessfully When user input Arrival date before Start Date");
        addShipping.fillShippingForm("Shipping", "Thanh Hóa", "Thạch Thành", "Kim Tân", "An Dương Vương", "05/05/2023", "01/01/2023", "206", "đã done");
        WebElement alertElement = Constant.WEBDRIVER.findElement(By.xpath("//span[@class='text-red-600 mt-2 inline-block']"));
        waitForElementVisible(Constant.WEBDRIVER, alertElement, 10);
        String alertText = alertElement.getText();
        Assert.assertEquals(alertText, "Arrival date must be after start date", "Alert text is not as expected");
    }

    @Test
    public void TC18() {
        System.out.println("TC18-Add new Shipping unsuccessfully When user input year more than 4 digits into Arrival Date");
        addShipping.fillShippingForm("Shipping", "Thanh Hóa", "Thạch Thành", "Kim Tân", "An Dương Vương", "05/05/2023", "01/01/20233", "204", "đã done");
        WebElement alertElement = Constant.WEBDRIVER.findElement(By.xpath("//li[normalize-space()='arrival date must be in MM/DD/YYYY format']"));
        waitForElementVisible(Constant.WEBDRIVER, alertElement, 10);
        String alertText = alertElement.getText();
        Assert.assertEquals(alertText, "arrival date must be in MM/DD/YYYY format", "Alert text is not as expected");
    }


}