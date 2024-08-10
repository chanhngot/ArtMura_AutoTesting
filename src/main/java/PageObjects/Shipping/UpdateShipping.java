package PageObjects.Shipping;

import Common.Constant;
import Common.WaitTime;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.HashMap;

public class UpdateShipping {
    private HashMap<String, By> setMap;
    private HashMap<String, By> searchMap;
    public UpdateShipping() {
        setMap = new HashMap<>();
        searchMap = new HashMap<>();

        setMap.put("setStatus", By.xpath("//select[@id='status']"));
        setMap.put("setProvince", By.xpath("//select[@id='province']"));
        setMap.put("setDistrict", By.xpath("//select[@id='district']"));
        setMap.put("setWard", By.xpath("//select[@id='ward']"));
        setMap.put("setAddress", By.xpath("//input[@name='streetAddress']"));
        setMap.put("setStartDate", By.xpath("//input[@name='startDate']"));
        setMap.put("setArrivalDate", By.xpath("//input[@name='arrivalDate']"));
        setMap.put("setOrderID", By.xpath("//select[@id='order_id']"));
        setMap.put("setDescription", By.xpath("//textarea[@name='description']"));
        setMap.put("btnSubmit", By.xpath("//button[@type='submit']"));
        setMap.put("btnCancel", By.xpath("//button[@type='button']"));
        setMap.put("txtSearch", By.xpath("//input[@placeholder='Search shippings...']"));


        searchMap.put("txtSearch", By.xpath("//input[@placeholder='Search shippings...']"));
        searchMap.put("searchStatus", By.xpath("//td[1]"));
        searchMap.put("searchProvince", By.xpath("//td[5]"));
        searchMap.put("searchDistrict", By.xpath("//td[3]"));
        searchMap.put("searchWard", By.xpath("//td[4]"));
        searchMap.put("searchAddress", By.xpath("//td[2]"));
        searchMap.put("searchStartDate", By.xpath("//td[7]"));
        searchMap.put("searchArrivalDate", By.xpath("//td[8]"));
        searchMap.put("searchOrderID", By.xpath("//td[9]"));
        searchMap.put("searchDescription", By.xpath("//td[6]"));


    }
    public WebElement getElementBySetType(String type) {
        return Constant.WEBDRIVER.findElement(setMap.get(type));
    }
    public WebElement getElementBySearchType(String type) {
        return Constant.WEBDRIVER.findElement(searchMap.get(type));
    }
    public void fillShippingForm(HashMap<String, String> fields) {
        try {
            for (HashMap.Entry<String, String> entry : fields.entrySet()) {
                String fieldName = entry.getKey();
                String value = entry.getValue();

                if (value != null && !value.isEmpty()) {
                    switch (fieldName) {
                        case "status":
                            new Select(this.getElementBySetType("setStatus")).selectByVisibleText(value);
                            break;
                        case "province":
                            new Select(this.getElementBySetType("setProvince")).selectByVisibleText(value);
                            break;
                        case "district":
                            new Select(this.getElementBySetType("setDistrict")).selectByVisibleText(value);
                            break;
                        case "ward":
                            new Select(this.getElementBySetType("setWard")).selectByVisibleText(value);
                            break;
                        case "address":
                            this.getElementBySetType("setAddress").clear();
                            this.getElementBySetType("setAddress").sendKeys(value);
                            break;
                        case "startDate":
                            this.getElementBySetType("setStartDate").clear();
                            this.getElementBySetType("setStartDate").sendKeys(value);
                            break;
                        case "arrivalDate":
                            this.getElementBySetType("setArrivalDate").sendKeys(value);
                            break;
                        case "orderID":
                            new Select(this.getElementBySetType("setOrderID")).selectByVisibleText(value);
                            break;
                        case "description":
                            this.getElementBySetType("setDescription").clear();
                            this.getElementBySetType("setDescription").sendKeys(value);
                            break;
                        default:
                            System.out.println("Unknown field: " + fieldName);
                            break;
                    }
                    Thread.sleep(2000);
                }
            }

            this.getElementBySetType("btnSubmit").click();
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void updateAndSearch(HashMap<String, String> SetAndValues, String txtSearch) {
        try {
            for (int i = 0; i < 5; i++) {
                Constant.WEBDRIVER.findElement(By.xpath("//th[normalize-space()='Status']")).click();
            }
            WaitTime.sleep(1000);
            Constant.WEBDRIVER.findElement(By.xpath("//select[@class='px-3 py-2 border rounded-md mr-2']")).click();
            WaitTime.sleep(1000);
            Constant.WEBDRIVER.findElement(By.xpath("//option[@value='order_id']")).click();
            WaitTime.sleep(1000);
            this.getElementBySetType("txtSearch").sendKeys(txtSearch);
            WaitTime.sleep(1000);
            Constant.WEBDRIVER.findElement(By.xpath("(//button[@type='button'])[2]")).click();
            WaitTime.sleep(1000);
            this.fillShippingForm(SetAndValues);
            WaitTime.sleep(2000);
            this.getElementBySetType("btnSubmit").click();
            WaitTime.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Search( String txtSearch, HashMap<String, String> columnAndExpected) {
        try {
            Constant.WEBDRIVER.findElement(By.xpath("//select[@class='px-3 py-2 border rounded-md mr-2']")).click();
            WaitTime.sleep(1000);
            Constant.WEBDRIVER.findElement(By.xpath("//option[@value='order_id']")).click();
            WaitTime.sleep(1000);
            this.getElementBySearchType("txtSearch").sendKeys(txtSearch);
            WaitTime.sleep(5000);
            for (HashMap.Entry<String, String> entry : columnAndExpected.entrySet()) {
                String fieldName = entry.getKey();
                String value = entry.getValue();
                String actual = getElementBySearchType(fieldName).getText();
                System.out.println("Column: " + fieldName);
                System.out.println("Expected Text: " + value + ", actual text: " + actual);
                Assert.assertEquals(actual, value, "Search result does not match the expected value");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
