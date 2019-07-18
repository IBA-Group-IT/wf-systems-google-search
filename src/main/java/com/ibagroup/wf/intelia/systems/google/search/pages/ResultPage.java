package com.ibagroup.wf.intelia.systems.google.search.pages;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.ibagroup.wf.intelia.core.clients.RobotDriverWrapper;
import com.ibagroup.wf.intelia.core.config.ConfigurationManager;
import com.ibagroup.wf.intelia.core.pagefactory.Wait;
import com.ibagroup.wf.intelia.core.pagefactory.Wait.WaitFunc;
import com.ibagroup.wf.intelia.systems.google.search.to.ProductSummaryTO;
import com.workfusion.rpa.helpers.RPA;

public class ResultPage extends RobotDriverWrapper {

    @FindBy(xpath = "//*[@id='rso']//h3/a")
    @Wait(waitFunc = WaitFunc.VISIBLE, value = 20)
    private List<WebElement> elements;

    @FindBy(xpath = "//a[@id='logo']")
    @Wait(waitFunc = WaitFunc.CLICKABLE, value = 60)
    private WebElement logo;

    @FindBy(xpath = "//body")
    @Wait(waitFunc = WaitFunc.CLICKABLE, value = 60)
    private WebElement resultPageBody;

    public ResultPage(ConfigurationManager cmn) {
        super(cmn);
    }

    public List<ProductSummaryTO> getResults() {  
        String mainWindowHandler = getDriver().getWindowHandle();
        return elements.stream().map(element -> {
            ProductSummaryTO resultTO = new ProductSummaryTO();
            resultTO.setProductLink(element.getAttribute("href"));

            // open link
            RPA.actions().keyDown(Keys.CONTROL).click(logo).keyUp(Keys.CONTROL).perform();
            // switch to new page
            RPA.switchToLastWindow();
            // WORKAROUND SOLUTION
            RPA.open(resultTO.getProductLink());
            // fetch text from the page
            resultTO.setProductSummary(resultPageBody.getAttribute("innerText"));
            // close page
            RPA.close();
            // switch back to the main page with search result
            RPA.switchToExistingWindow(mainWindowHandler);

            return resultTO;
        }).collect(Collectors.toList());
    }
}
