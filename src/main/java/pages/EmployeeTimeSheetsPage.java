package pages;

import lombok.Getter;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

@Getter
public class EmployeeTimeSheetsPage extends BasePage{

    @FindBy(css = "#x_report_employeeId_empName")
   //@FindBy(css = "#requiredField ac_input")
    private WebElementFacade searchInputField;

    @FindBy(css = ".ac_results li")
    private WebElementFacade employeeNameAutoCompleteElement;

    @FindBy(css = "#noncoreIframe")
    private WebElementFacade childFrame;
}
