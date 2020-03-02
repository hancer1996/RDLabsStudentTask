package pages;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.annotations.findby.FindBy;
import org.openqa.selenium.By;
import net.serenitybdd.core.pages.WebElementFacade;

@Getter
@Slf4j
public class PersonalDetailsPage extends BasePage {

    @FindBy(css = "#personal_details_tab h4")
    private WebElementFacade personalDetailsHeader;

    @FindBy(css = "#emp_birthday")
    private WebElementFacade dateOfBirthInputField;

    @FindBy(xpath = "//div[@id='nation_code_inputfileddiv']//input")
    private WebElementFacade nationalitySelect;

    @FindBy(xpath = "//div[@id='eeo_race_ent_inputfileddiv']//input")
    private WebElementFacade eeoRaceAndEthnicitySelect;

    @FindBy(xpath = "//label[@for='emp_gender_1']")
    private WebElementFacade maleRadioButton;

    @FindBy(xpath = "//label[@for='emp_gender_2']")
    private WebElementFacade femaleRadioButton;

    @FindBy(xpath = "//sf-decorator/div/button")
    private WebElementFacade save;

    @FindBy(xpath = "//span[@class='help-block-message']")
    private WebElementFacade BDayErrorMessage;

    @FindBy(xpath = "//*[@id='eeo_race_ent_inputfileddiv']/span")
    private WebElementFacade RaceAndEthnicityErrorMessage;

    public void enterDateOfBirth(String date) {
        log.info(String.format("Putting %s date into [Date of birth] field", date));
        dateOfBirthInputField.clear();
        dateOfBirthInputField.waitUntilEnabled().sendKeys(date);
    }

    public void clickOnButton(String value) {
        log.info("set gender radio button checked");
        switch (value) {
            case ("Male"):
                maleRadioButton.waitUntilClickable().click();
                break;
            case ("Female"):
                femaleRadioButton.waitUntilClickable().click();
                break;
            case ("Save"):
                save.waitUntilClickable().click();
                break;
            default:
                break;
        }
    }

    public boolean checkSelectedRadioButton(String value) {
        log.info("check selected gender radio button");
        switch (value) {
            case ("Male"):
                return Boolean.parseBoolean(maleRadioButton.findElement(By.xpath("./../input")).getAttribute("checked"));
            case ("Female"):
                log.info("check that {} radio button is checked", value);
                return Boolean.parseBoolean(femaleRadioButton.findElement(By.xpath("./../input")).getAttribute("checked"));
            case ("Save"):

            default:
                System.out.println("Only 'Male' and 'Female' gender exist");
                return false;
        }

    }
}