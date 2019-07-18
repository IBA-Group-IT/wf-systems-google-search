package com.ibagroup.wf.intelia.systems.google.search.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.ibagroup.wf.intelia.core.clients.RobotDriverWrapper;
import com.ibagroup.wf.intelia.core.config.ConfigurationManager;
import com.ibagroup.wf.intelia.core.pagefactory.Wait;
import com.ibagroup.wf.intelia.core.pagefactory.Wait.WaitFunc;

public class HomePage extends RobotDriverWrapper {

    private static final int WAIT_FIELD = 20;

    @FindBy(xpath = "//input[@type='text']")
    @Wait(waitFunc = WaitFunc.CLICKABLE, value = WAIT_FIELD)
    private WebElement searchField;

    public HomePage(ConfigurationManager cmn) {
        super(cmn);
    }

    public ResultPage search(String searchString) {
        searchField.click();
        searchField.clear();
        searchField.sendKeys(searchString);

        getDriver().getKeyboard().sendKeys(Keys.ENTER);

        return new ResultPage(getCfg());
    }

}