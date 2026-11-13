package com.example.pages;

import com.example.base.BasePage;
import org.openqa.selenium.By;

import static com.example.utils.GetUtility.getText;

public class FramesPage extends BasePage {

    private By adminPageMenuButton = By.cssSelector("[data-testid='nav-admin']");
    private By logoutMenuButton = By.cssSelector("[data-testid='logout']");
    private By tablePageMenuButton = By.cssSelector("[data-testid='nav-table']");
    private By formPageMenuButton = By.cssSelector("[data-testid='nav-form']");
    private By progressPageMenuButton = By.cssSelector("[data-testid='nav-progress']");
    private By searchPageMenuButton = By.cssSelector("[data-testid='nav-search']");

    private By pageHeader = By.xpath("//*[@id=\"root\"]/div/section/h2");
    private By mainFrameHeader = By.id("data-testid='main-frame'");
    private By textInMainFrame = By.xpath("//*[@id=\"root\"]/div/div/h3");
    private By iFrameOne = By.id("data-testid='frame-one'");
    private By iFrameTwo = By.id("data-testid='frame-two'");

    public String getFramesPageHeader() {
        return getText(pageHeader);
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

    public ProgressPage clickProgressPageButton() {
        scroll(progressPageMenuButton);
        click(progressPageMenuButton);
        return new ProgressPage();
    }

    public SearchPage clickSearchPageButton() {
        scroll(searchPageMenuButton);
        click(searchPageMenuButton);
        return new SearchPage();
    }

    public LoginPage clickLogoutButton() {
        scroll(logoutMenuButton);
        click(logoutMenuButton);
        return new LoginPage();
    }

}
