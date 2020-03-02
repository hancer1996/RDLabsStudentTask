package steps;

import grids.UsersGrid;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;
import pageComponents.FilterUsersModalWindow;
import pages.UsersPage;

import java.util.List;

import static utils.SessionVariables.FILTER_USERS_WINDOW;

@Slf4j
public class UsersSteps extends DefaultStepsData {

    @Step
    public void openFilterWindow() {
        log.info("Opens Filter Users window");
        usersPage.clickOnFilterButton();
        FILTER_USERS_WINDOW.put(new FilterUsersModalWindow(usersPage.getFilterUsersModalWindow()));
    }

    @Step
    public FilterUsersModalWindow getFiltetUsersWindow() {
        return new FilterUsersModalWindow(usersPage.getFilterUsersModalWindow());
    }

    @Step
    public void clickOnTheSearchButton() {
        log.info("Clicking on the Search button");
        FilterUsersModalWindow filterUsersModalWindow = FILTER_USERS_WINDOW.get();
        filterUsersModalWindow.clickOnSearchButton();
    }

    @Step
    public void filterUsersByEmployeeName(String employeeName) {
        FilterUsersModalWindow filterUsersModalWindow = FILTER_USERS_WINDOW.get();
        log.info("Filtering by Employee Name: " + employeeName);
        log.info("Typing employee name into [Employee Name] input field");
        filterUsersModalWindow.getEmployeeNameField().waitUntilEnabled().sendKeys(employeeName);
        WebElementFacade employeeDropDown = filterUsersModalWindow.getEmployeeNameField().find(By.xpath("./..//div[contains(@class,'angucomplete-row')]"));
        log.info("Clicking on the autocomplete search result");
        employeeDropDown.waitUntilVisible().waitUntilClickable().click();
        employeeDropDown.waitUntilNotVisible();
    }

    @Step
    public List<UsersGrid> getUsersGrid() {
        log.info("Getting [Users] grid");
        return new UsersGrid().getAllItems(usersPage.getDriver());
    }

    @Step
    public void changeStatusTo(String status) {
        FilterUsersModalWindow filterUsersModalWindow = FILTER_USERS_WINDOW.get();
        log.info("Filtering by Status: " + status);
        filterUsersModalWindow.changeStatusTo(status);
    }

    @Step
    public boolean employeeIsShown(String employeeName) {
        List<UsersGrid> allItems;
        int amountOfShownEmployees = 0;
        boolean IsThereNextPage;
        do {
            allItems = getUsersGrid();
            for (UsersGrid Item : allItems) {
                if (Item.getEmployeeName().equals(employeeName)) {
                    amountOfShownEmployees++;
                }
            }
            allItems.clear();
            if (usersPage.getNextPageButton().isVisible()) {
                usersPage.getNextPageButton().waitUntilClickable().click();
                IsThereNextPage = true;
            } else {
                IsThereNextPage = false;
            }
        } while (IsThereNextPage);
        return amountOfShownEmployees != 0;
    }

    @Step
    public void selectFilter(String filterName){
        switch (filterName){
            case "Username":
                usersPage.getFilters().find(By.id("systemuser_uname_filter")).click();
                break;
            case "Employee Name":
                usersPage.getFilters().find(By.id("employee_name_filter_value")).click();
                break;
            case "ESS Role":
                usersPage.getFilters().find(By.id("select-options-a63dd7e0-5393-d429-9a11-72f3e7449419")).click();
                break;
            case "Admin Role":
                usersPage.getFilters().find(By.id("select-options-f8cfd673-9d06-2b3c-540f-1b4694727b72")).click();
                break;
            case "Supervisor Role":
                usersPage.getFilters().find(By.id("select-options-3015d85b-eaf1-7195-72be-5616f5ecbe86")).click();
                break;
            case "Status":
                usersPage.getFilters().find(By.id("select-options-ed4f82d2-f951-8c55-266d-18ac43ae8f3b")).click();
                break;
            case "Location":
                usersPage.getFilters().find(By.id("select-options-a5e5aa5d-c40a-882e-659b-74ff940ff8e1")).click();
                break;
        }
    }

    @Step
    public void selectStatus(String status){

    }
}
