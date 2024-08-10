package PageObjects.Shipping;

import Common.Constant;
import Common.WaitTime;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
//import org.testng.Assert;

import java.util.HashMap;

public class SearchShipping {
    private HashMap<String, By> searchMap;
    public SearchShipping(){
        searchMap = new HashMap<>();

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
    public WebElement getElementBySearchType(String type) {
        return Constant.WEBDRIVER.findElement(searchMap.get(type));
    }
    public void Search( String txtSearch) {
        try {
            Constant.WEBDRIVER.findElement(By.xpath("//select[@class='px-3 py-2 border rounded-md mr-2']")).click();
            WaitTime.sleep(1000);
            Constant.WEBDRIVER.findElement(By.xpath("//option[@value='order_id']")).click();
            WaitTime.sleep(1000);
            this.getElementBySearchType("txtSearch").sendKeys(txtSearch);
            WaitTime.sleep(5000);
//            String actual =getElementBySearchType("searchOrderID").getText();
//            System.out.println("Expected Text: " + expected + ", actual text: " + actual);
//            Assert.assertEquals(actual, expected, "Search result does not match the expected value");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
