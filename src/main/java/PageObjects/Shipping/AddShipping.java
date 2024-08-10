package PageObjects.Shipping;

import Common.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;

public class AddShipping{
    private HashMap<String, By> setMap;
    public AddShipping(){
        setMap = new HashMap<>();
        setMap.put("btnAdd", By.xpath("(//button[contains(@type,'button')])[1]"));
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
    }
    public WebElement getElementBySetType(String type) {
        return Constant.WEBDRIVER.findElement(setMap.get(type));
    }
    public void fillShippingForm(String status, String province, String district, String ward, String address, String startDate, String arrivalDate, String orderID, String description) {

        try {
            this.getElementBySetType("btnAdd").click();
            if (status != null && !status.isEmpty()) {
                new Select( this.getElementBySetType("setStatus")).selectByVisibleText(status);
                Thread.sleep(1000);
            }
            if (province != null && !province.isEmpty()) {
                new Select( this.getElementBySetType("setProvince")).selectByVisibleText(province);
                Thread.sleep(1000);
            }
            if (district != null && !district.isEmpty()) {
                new Select( this.getElementBySetType("setDistrict")).selectByVisibleText(district);
                Thread.sleep(1000);
            }
            if (ward != null && !ward.isEmpty()) {
                new Select( this.getElementBySetType("setWard")).selectByVisibleText(ward);
                Thread.sleep(1000);
            }
            if (address != null && !address.isEmpty()) {
                this.getElementBySetType("setAddress").sendKeys(address);
                Thread.sleep(1000);
            }
            if (startDate != null && !startDate.isEmpty()) {
                this.getElementBySetType("setStartDate").sendKeys(startDate);
                Thread.sleep(1000);
            }
            if (arrivalDate != null && !arrivalDate.isEmpty()) {
                this.getElementBySetType("setArrivalDate").sendKeys(arrivalDate);
                Thread.sleep(1000);
            }
            if (orderID != null && !orderID.isEmpty()) {
                new Select( this.getElementBySetType("setOrderID")).selectByVisibleText(orderID);
                Thread.sleep(1000);
            }
            if (description != null && !description.isEmpty()) {
                this.getElementBySetType("setDescription").sendKeys(description);
                Thread.sleep(1000);
            }
            this.getElementBySetType("btnSubmit").click();
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

