package com.example.pages;

import com.example.base.BasePage;
import org.openqa.selenium.By;

import static com.example.utils.GetUtility.getText;

public class ProgressPage extends BasePage {

    private By progressHeader = By.xpath("/html/body/div/div/section/h2");

    private By startProgressButton = By.cssSelector("[data-testid='start-progress']");
    private By progressValue = By.cssSelector("[data-testid='progress-value']");
    private By progressBar = By.cssSelector("[data-testid='progress-bar']");

    private By adminPageMenuButton = By.cssSelector("[data-testid='nav-admin']");
    private By logoutMenuButton = By.cssSelector("[data-testid='logout']");
    private By tablePageMenuButton = By.cssSelector("[data-testid='nav-table']");
    private By formPageMenuButton = By.cssSelector("[data-testid='nav-form']");
    private By searchPageMenuButton = By.cssSelector("[data-testid='nav-search']");


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

    public String getProgressValue() {
        return getText(progressValue);
    }
}
