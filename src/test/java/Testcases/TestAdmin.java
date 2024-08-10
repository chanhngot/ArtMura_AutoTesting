package Testcases;

import Common.Constant;
import Common.NavigationHelper;
import Common.WaitTime;
import PageObjects.Shipping.AddShipping;
import PageObjects.Shipping.DeleteShipping;
import PageObjects.Shipping.SearchShipping;
import PageObjects.Shipping.UpdateShipping;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.HashMap;

import static Common.WaitTime.waitForElementVisible;

public class TestAdmin {
    AddShipping addShipping = new AddShipping();
    UpdateShipping updateShipping =new UpdateShipping();
    DeleteShipping deleteShipping =new DeleteShipping();
    SearchShipping searchShipping =new SearchShipping();

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

    // add successfully
    @Test
    public void TC01() {
        System.out.println("TC01- Add new Shipping successfully");
        addShipping.fillShippingForm("Shipping", "Thanh Hóa", "Thạch Thành", "Kim Tân", "Xóm 1", "2/05/2023", "5/05/2023", "204", "đã done");
        WaitTime.sleep(2000);
        String alertText = Constant.WEBDRIVER.findElement(By.xpath("//div[@role='alert']//div[2]")).getText();
        Assert.assertEquals(alertText, "Save Data Success", "Alert text is not as expected");
        searchShipping.Search("204");
    }
    @Test
    public void TC02() {
        System.out.println("TC02-Add new Shipping unsuccessfully due to a value is not selected in the dropdown list:WARD");
        addShipping.fillShippingForm("Shipping", "Sơn La", "Quỳnh Nhai", null, "Xóm 1", "03/05/2023", "05/05/2023", "206", "Made in Japan");
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, 10);
        WebElement alertElement3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Ward cannot be empty']")));
        String alertText3 = alertElement3.getText();
        Assert.assertEquals(alertText3, "Ward cannot be empty", "Alert text is not as expected");
    }
    @Test
    public void TC03() {
        System.out.println("TC03-Add new Shipping unsuccessfully When user input more than 255 charaters of the textbox: [ADDRESS]");
        addShipping.fillShippingForm("Shipping", "Thanh Hóa", "Thạch Thành", "Kim Tân", Constant.More255characters, "2/05/2023", "5/05/2023", "206", "đã done");
        WebElement alertElement = Constant.WEBDRIVER.findElement(By.xpath("//span[@class='text-red-600 mt-2 inline-block']"));
        waitForElementVisible(Constant.WEBDRIVER, alertElement, 10);
        String alertText = alertElement.getText();
        Assert.assertEquals(alertText, "Street address must be at most 255 characters", "Alert text is not as expected");
    }
    // add unsuccessfully (input startDate is after arrival date)
    @Test
    public void TC04() {
        System.out.println("TC04- Add new Shipping unsuccessfully When user input Start Date is after Arrival Date");
        addShipping.fillShippingForm("Shipping", "Thanh Hóa", "Thạch Thành", "Kim Tân", "Xóm 1", "02/05/2023", "01/05/2023", "206", "đã done");
        WebElement alertElement = Constant.WEBDRIVER.findElement(By.xpath("//span[normalize-space()='Arrival date must be after start date']"));
        waitForElementVisible(Constant.WEBDRIVER, alertElement, 10);
        String alertText = alertElement.getText();
        Assert.assertEquals(alertText, "Arrival date must be after start date", "Alert text is not as expected");
    }
    @Test
    public void TC05() {
        System.out.println("TC05-Add new Shipping unsuccessfully When user input year more than 4 digits into Arrival Date");
        addShipping.fillShippingForm("Shipping", "Thanh Hóa", "Thạch Thành", "Kim Tân", "An Dương Vương",  "05/05/2023","01/01/20233", "173", "đã done");
        WebElement alertElement = Constant.WEBDRIVER.findElement(By.xpath("//li[normalize-space()='arrival date must be in MM/DD/YYYY format']"));
        waitForElementVisible(Constant.WEBDRIVER, alertElement, 10);
        String alertText = alertElement.getText();
        Assert.assertEquals(alertText, "arrival date must be in MM/DD/YYYY format", "Alert text is not as expected");
    }
    @Test
    public void TC06() {
        System.out.println("TC06-Add new Shipping unsuccessfully When user input the space at the beginning of the textbox: [ADDRESS]");
        addShipping.fillShippingForm("Shipping", "Thanh Hóa", "Thạch Thành", "Kim Tân", "  nguyen doán", "2/05/2023", "5/05/2023", "206", "đã done");
        WebElement alertElement = Constant.WEBDRIVER.findElement(By.xpath("//span[@class='text-red-600 mt-2 inline-block']"));
        waitForElementVisible(Constant.WEBDRIVER, alertElement, 10);
        String alertText = alertElement.getText();
        Assert.assertEquals(alertText, "Please do not insert space at the beginning or end", "Alert text is not as expected");
    }
    @Test
    public void TC07() {
        System.out.println("TC07- Add new Shipping unsuccessfully when don't selected ORDER_ID");
        addShipping.fillShippingForm("Shipping", "Thanh Hóa", "Thạch Thành", "Kim Tân", "Xóm 1", "02/05/2023", "05/05/2023", null, "đã done");
        String alertText = Constant.WEBDRIVER.findElement(By.xpath("//span[normalize-space()='You must select an order ID']")).getText();
        Assert.assertEquals(alertText, "You must select an order ID", "Alert text is not as expected");
    }



    @Test
    public void TC08() {
        System.out.println("TC08- Update Shipping successfully");
        // thông tin được sử dụng để update
        HashMap<String, String> Data = new HashMap<>();
        Data.put("status", "Cancelled");
        Data.put("address", "243 Phan Dang Luu");
        // Thông tin được lâý ra
        HashMap<String, String> setToSearchType = new HashMap<>();
        setToSearchType.put("status", "searchStatus");
        setToSearchType.put("address", "searchAddress");
        // Update thông tin với OrderID 204
        updateShipping.updateAndSearch( Data,"204");
        // So sánh actual và expected
        WebElement alertElement = Constant.WEBDRIVER.findElement(By.xpath("//div[@role='alert']//div[2]"));
        waitForElementVisible(Constant.WEBDRIVER, alertElement, 10);
        String alertText = alertElement.getText();
        Assert.assertEquals(alertText, "Update Data Success", "Alert text is not as expected");
        // load lại trang web
        Constant.WEBDRIVER.navigate().refresh();
        //Đoạn mã này chuyển đổi các cặp key-value từ Data sang SearchData bằng cách
        // thay đổi key thông qua setToSearchType, sau đó sử dụng SearchData để thực hiện một tìm kiếm
        // thông qua phương thức Search của đối tượng updateShipping.
        HashMap<String, String> SearchData = new HashMap<>();
        //Vòng lặp này duyệt qua từng cặp key-value (cặp khóa-giá trị) trong Data.
        for (HashMap.Entry<String, String> entry : Data.entrySet()) {
            String fieldName = entry.getKey();
            String value = entry.getValue();
            SearchData.put(setToSearchType.get(fieldName), value);
        }
        HashMap<String, String> DataMap = new HashMap<String, String>();
        updateShipping.Search("204", SearchData);
    }

    // Case không thành công khi nhập kí tự đặc biệt
    @Test
    public void TC09() {
        System.out.println("TC09-Update Shipping unsuccessfully when input special charaters or emoji into textbox [STREET ADDRESS]");
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
    public void TC10() {
        System.out.println("TC10-Delete Shipping successfully");
        deleteShipping.deleteShipping("204");
        String alertText = Constant.WEBDRIVER.findElement(By.xpath("//div[@role='alert']//div[2]")).getText();
        Assert.assertEquals(alertText, "Delete Data Success", "Alert text is not as expected");
        Constant.WEBDRIVER.navigate().refresh();
        deleteShipping.Search("204");
        WebElement alertElement = Constant.WEBDRIVER.findElement(By.xpath("//td[@class='text-center text-2xl text-gray-500 font-medium pt-6 pb-6']"));
        waitForElementVisible(Constant.WEBDRIVER, alertElement, 10);
        String alertText_2 = alertElement.getText();
        Assert.assertEquals(alertText_2, "No Shippings", "Alert text is not as expected");
        WaitTime.sleep(2000);
    }
}
