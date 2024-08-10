package PageObjects;

import Common.Constant;

public class NavigateToLoginAdmin {
    public NavigateToLoginAdmin open (){
        Constant.WEBDRIVER.navigate().to(Constant.Login_Admin);
        return this;
    }
}
