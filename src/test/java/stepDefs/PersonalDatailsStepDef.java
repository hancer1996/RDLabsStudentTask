package stepDefs;

import com.google.common.collect.Ordering;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.yecht.Data;
import steps.DefaultStepsData;
import steps.PersonalDetailsSteps;

import java.util.List;

import static utils.DateUtils.*;
import static utils.SessionVariables.DATE_OF_BIRTH;

public class PersonalDatailsStepDef extends DefaultStepsData {

    @Steps
    PersonalDetailsSteps personalDetailsSteps;

    @Then("I save current Date of Birth to session")
    public void saveCurentDateOfBirthToSession() {
        DATE_OF_BIRTH.put(personalDetailsSteps.getValueFromDateOfBirthField());
    }

    @When("I change Date of Birth added 1 day to old date")
    public void changeDateOfBirth() {
        String currentDate = personalDetailsSteps.getValueFromDateOfBirthField();
        String updatedDate = getDateInFutureOrPastFromNow(DATEPATTERN_HRM, 1, currentDate);
        personalDetailsSteps.enterDateIntoDateBirthField(updatedDate);
    }

    @Then("Date of Birth field contains old date (date from session)")
    public void checkThatDateOfBirthNotChange() {
        String currentDate = personalDetailsSteps.getValueFromDateOfBirthField();
        softly.assertThat(currentDate).as("After refreshing Date of Birth change").isEqualTo(DATE_OF_BIRTH.get());
    }

    @Then("I check that all countries in Nationality select box ordered by name asc")
    public void checkOrderingInNationalitySelectBox() {
        List<String> optionsFromNationalitySelect = personalDetailsSteps.getOptionsFromNationalitySelect();
        boolean isSorted = Ordering.natural().isOrdered(optionsFromNationalitySelect);
        softly.assertThat(isSorted).as("Wrong ordering inside select box").isTrue();
    }
//TODO: Wrote body for methods
   @Then ("Set $gender radio button as checked")
    public void setRadioButton(String gender){
        personalDetailsSteps.GenderButtonActiveState(gender);
    }

    @Then("Check that $radioButtonName radio button is $state")
    public void checkThatRadioButtonUnchecked(String radioButtonName, String state) {
        if (state.contains("not")) {
            softly.assertThat(personalDetailsSteps.checkSelectedRadioButton(radioButtonName))
                    .as("After checking whether the radio button is not selected").isFalse();
        } else {
            softly.assertThat(personalDetailsSteps.checkSelectedRadioButton(radioButtonName))
                    .as("After checking whether the radio button is selected").isTrue();
        }
    }

    @When("Set Date of Birth as tomorrow date")
    public void setTomorrowAsDateOfBirth() {
        String currentDate = personalDetailsSteps.getValueFromDateOfBirthField();
        String updatedDate = getDateInFutureOrPastFromNow(DATEPATTERN_HRM, 1);
        personalDetailsSteps.enterDateIntoDateBirthField(updatedDate);
    }
    @When("Click on $buttonName button in Personal Details form")
    @Then("Click on $buttonName button in Personal Details form")
    public void ClickOnButton(String buttonName){
        personalDetailsPage.clickOnButton(buttonName);
    }

    @Then("Check that error message with text '$errorText' appears under '$label' field")
    public void getTextFromDateOfBirthErrorMessage(String errorText, String label) {
      switch (label){
          case "Date of Birth":
              softly.assertThat(personalDetailsSteps.getErrorMessageText(label))
                      .as("After getting date of birth error message").isEqualTo(errorText);
              break;
              case "EEO Race and Ethnicity":
                  softly.assertThat(personalDetailsSteps.getErrorMessageText(label))
                          .as("Required").isEqualTo(errorText);
      }
    }
    //TODO:body of method
   @When("Check that '$field' select has '$value' value by default")
    public void checkDefaultValue(String field, String value){

   }

}
