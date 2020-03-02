package steps;

import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

public class PersonalDetailsSteps extends DefaultStepsData {

    @Step
    public String getValueFromDateOfBirthField() {
        return personalDetailsPage.getDateOfBirthInputField().getAttribute("value");
    }

    @Step
    public void enterDateIntoDateBirthField(String date) {
        personalDetailsPage.getDateOfBirthInputField().clear();
        personalDetailsPage.enterDateOfBirth(date);
    }

    @Step
    public List<String> getOptionsFromNationalitySelect() {
        List<String> nationalityOptions = personalDetailsPage.getNationalitySelect().thenFindAll(By.xpath("./..//li//span"))
                .stream().map(we -> we.getAttribute("innerText")).collect(Collectors.toList());
        return nationalityOptions;
    }

    @Step
    public void GenderButtonActiveState(String gender) {
        personalDetailsPage.clickOnButton(gender);
    }

    @Step
    public void GenderButtonUnchecked(String gender) {
        switch (gender) {
            case "Male":
                softly.assertThat(personalDetailsPage.getMaleRadioButton().isSelected()).isFalse();
                break;
            case "Female":
                softly.assertThat(personalDetailsPage.getFemaleRadioButton().isDisabled()).isFalse();
                break;
        }
    }

    @Step
    public boolean checkSelectedRadioButton(String gender) {
        return personalDetailsPage.checkSelectedRadioButton(gender);
    }

    @Step
    public String getErrorMessageText(String labelName) {
        if (labelName.equals("Date of Birth")) return personalDetailsPage.getBDayErrorMessage().getText();
        else return personalDetailsPage.getRaceAndEthnicityErrorMessage().getText();
    }
}