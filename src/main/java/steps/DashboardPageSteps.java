package steps;

import emuns.ItemsContainer;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

@Slf4j
public class DashboardPageSteps extends DefaultStepsData {

    @Step
    public String getDashBoardPageTitle() {
        return dashboardPage.getTitle();
    }

    @Step
    public String getAccountNameFromDashboard() {
        return dashboardPage.getAccountNameLabel().getText();
    }

    @Step
    public void clickOnHideMenuButton() {
        dashboardPage.clickOnHideMenuButton();
    }

    @Step
    public void expandContainerClickingOnThreeDots(String sectionName) {
        ItemsContainer itemsContainer = ItemsContainer.getItemsContainerName(sectionName);
        switch (itemsContainer) {
            case EMPLOYEE_DISTRIBUTION:
                dashboardPage.getThreeDotsButton().waitUntilEnabled().click();
                break;
            case LEAVE_TAKEN:
                //  dashboardPage.getThreeDotsButton().waitUntilEnabled().click();
                dashboardPage.find(By.cssSelector("#panel_resizable_0_5 > div.card-content > span > i")).click();//TODO:Reformat code to make things like they supposed to be
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + itemsContainer);
        }
    }

    public boolean checkThatLegendAppearsIn(String sectionName) {
        ItemsContainer itemsContainer = ItemsContainer.getItemsContainerName(sectionName);
        switch (itemsContainer) {
            case EMPLOYEE_DISTRIBUTION:
                return dashboardPage.getEmployeeLegend().isVisible();
            case LEAVE_TAKEN:
                return dashboardPage.getLeavesLegend().isVisible();
            default:
                throw new IllegalStateException("Unexpected value: " + itemsContainer);
        }
    }

    @Step
    public String executeTextFromSectionHeader(String sectionName) {
        ItemsContainer itemsContainer = ItemsContainer.getItemsContainerName(sectionName);
        switch (itemsContainer) {
            case NEWS:
                return dashboardPage.getNewsHeader().waitUntilVisible().getText();
            case DOCUMENTS:
                return dashboardPage.getDocumentsHeader().waitUntilVisible().getText();
            default:
                throw new IllegalStateException("Unexpected value: " + itemsContainer);
        }
    }

    @Step
    public Integer actualNumber(WebElementFacade sectionName){
        List<WebElement> list = sectionName.findElements(By.xpath("//li"));
        return list.size();
    }

    @Step
    public Integer expectedNumber(){
      String expectedNumber =  dashboardPage.getDocumentCount().getTextValue();
        expectedNumber = expectedNumber.substring(expectedNumber.lastIndexOf('/') + 1 );
        return Integer.parseInt(expectedNumber);
    }
}

