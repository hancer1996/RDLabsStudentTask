package steps;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

@Getter
@Slf4j
public class EmployeeTimeSheetsSteps extends DefaultStepsData {

    @Step
    public void searchByEmployeeName(String name) {
        getFrame();
        employeeTimeSheetsPage.getSearchInputField().waitUntilClickable().click();
        employeeTimeSheetsPage.getSearchInputField().clear();
        log.info("Searching by name: " + name);
        employeeTimeSheetsPage.getSearchInputField().sendKeys(name);
    }

    @Step
    public String getTextFromAutoCompleteNameField() {
       String buffer = employeeTimeSheetsPage.getEmployeeNameAutoCompleteElement().withTimeoutOf(Duration.ofSeconds(5)).waitUntilVisible().getText();
       buffer = buffer.substring(0,buffer.indexOf("\n"));
       getDriver().switchTo().defaultContent();
       return buffer;
    }

    private void getFrame() {
     getDriver().switchTo().frame("noncoreIframe");
    }
}
