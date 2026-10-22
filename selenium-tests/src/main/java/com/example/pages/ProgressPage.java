package com.example.pages;

import com.example.base.BasePage;
import org.openqa.selenium.By;

import static com.example.utils.GetUtility.getText;
import static com.example.utils.WaitUtility.fluentWaitUntilIntermediatePercentage;
import static com.example.utils.WaitUtility.fluentWaitUntilText;
import static com.example.utils.WaitUtility.fluentWaitUntilTextContains;
import static com.example.utils.WaitUtility.fluentWaitUntilVisible;

public class ProgressPage extends BasePage {

    private final By progressHeader = By.xpath("/html/body/div/div/section/h2");

    private final By startProgressButton = By.cssSelector("[data-testid='start-progress']");
    private final By progressValue = By.cssSelector("[data-testid='progress-value']");

    private final By adminPageMenuButton = By.cssSelector("[data-testid='nav-admin']");
    private final By logoutMenuButton = By.cssSelector("[data-testid='logout']");
    private final By tablePageMenuButton = By.cssSelector("[data-testid='nav-table']");
    private final By formPageMenuButton = By.cssSelector("[data-testid='nav-form']");
    private final By searchPageMenuButton = By.cssSelector("[data-testid='nav-search']");


    public String getProgressPageHeader() {
        return getText(progressHeader);
    }

    public AdminPage clickAdminPageButton() {
        scroll(adminPageMenuButton);
        click(adminPageMenuButton);
        return new AdminPage();
    }

    public boolean isAdminPageButtonEnabled() {
        return find(adminPageMenuButton).isEnabled();
    }

    public TablePage clickTablePageButton() {
        scroll(tablePageMenuButton);
        click(tablePageMenuButton);
        return new TablePage();
    }

    public FormPage clickFormPageButton() {
        scroll(formPageMenuButton);
        click(formPageMenuButton);
        return new FormPage();
    }

    public LoginPage clickLogoutButton() {
        scroll(logoutMenuButton);
        click(logoutMenuButton);
        return new LoginPage();
    }

    public SearchPage clickSearchPageButton() {
        scroll(searchPageMenuButton);
        click(searchPageMenuButton);
        return new SearchPage();
    }

    public void clickStartButton() {
        scroll(startProgressButton);
        click(startProgressButton);
    }

    public String getStartButtonTextBeforeClick() {
        return getText(startProgressButton);
    }

    public String getStartButtonTextAfterClick() {
        return getText(startProgressButton);
    }

    public String getStartButtonTextAt100Percent() {
        return getText(startProgressButton);
    }

    public String waitForStartButtonTextToContain(int timeoutInSeconds, String text) {
        return fluentWaitUntilTextContains(timeoutInSeconds, startProgressButton, text);
    }

    public String getProgressValue(int waitInSeconds) {
        fluentWaitUntilVisible(waitInSeconds, progressValue);
        return getText(progressValue);
    }

    public String waitForProgressToComplete(int timeoutInSeconds) {
        fluentWaitUntilText(timeoutInSeconds, progressValue, "100%");
        return getText(progressValue);
    }

    public String waitForIntermediateProgress(int timeoutInSeconds) {
        return fluentWaitUntilIntermediatePercentage(timeoutInSeconds, progressValue);
    }
}


