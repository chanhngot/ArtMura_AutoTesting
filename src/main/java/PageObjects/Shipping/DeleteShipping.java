package PageObjects.Shipping;

import Common.Constant;
import Common.WaitTime;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashMap;

public class DeleteShipping {
    private HashMap<String, By> delMap;
    public DeleteShipping() {
        delMap = new HashMap<>();
        delMap.put("Sel", By.xpath("//select[@class='px-3 py-2 border rounded-md mr-2']"));
        delMap.put("selOrderID", By.xpath("//option[@value='order_id']"));
        delMap.put("txtSearch", By.xpath("//input[@placeholder='Search shippings...']"));
        delMap.put("btnDelete", By.xpath("(//button[@type='button'])[3]"));
        delMap.put("txtAlert", By.xpath("//div[@role='alert']//div[2]"));

    }
    public WebElement getElementBySetType(String type) {
        return Constant.WEBDRIVER.findElement(delMap.get(type));
    }
    public void deleteShipping (String search){
        try {
            for (int i = 0; i < 5; i++) {
                Constant.WEBDRIVER.findElement(By.xpath("//th[normalize-space()='Status']")).click();
            }
            WaitTime.sleep(1000);
            WaitTime.sleep(1000);
            this.getElementBySetType("Sel").click();
            this.getElementBySetType("selOrderID").click();
            WaitTime.sleep(1000);
            this.getElementBySetType("txtSearch").sendKeys(search);
            WaitTime.sleep(1000);
            this.getElementBySetType("selOrderID").click();
            this.getElementBySetType("btnDelete").click();
            Constant.WEBDRIVER.switchTo().alert().accept();
            WaitTime.sleep(2000);
        } catch (Exception e) {
            System.out.println(" ");
        }

    }
    public void Search (String search){
        this.getElementBySetType("Sel").click();
        this.getElementBySetType("selOrderID").click();
        WaitTime.sleep(1000);
        this.getElementBySetType("txtSearch").sendKeys(search);
        WaitTime.sleep(1000);

    }

}