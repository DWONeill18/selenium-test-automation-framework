package com.example.pages;

import com.example.base.BasePage;
import org.openqa.selenium.By;

import static com.example.utils.GetUtility.getText;
import static com.example.utils.SwitchToUtility.switchToDefaultContent;
import static com.example.utils.SwitchToUtility.switchToFrameByElement;

public class FramesPage extends BasePage {

    private By adminPageMenuButton = By.cssSelector("[data-testid='nav-admin']");
    private By logoutMenuButton = By.cssSelector("[data-testid='logout']");
    private By tablePageMenuButton = By.cssSelector("[data-testid='nav-table']");
    private By formPageMenuButton = By.cssSelector("[data-testid='nav-form']");
    private By progressPageMenuButton = By.cssSelector("[data-testid='nav-progress']");
    private By searchPageMenuButton = By.cssSelector("[data-testid='nav-search']");

    private By pageHeader = By.xpath("//*[@id=\"root\"]/div/section/h2");
    private By mainFrameHeader = By.cssSelector("[data-testid='main-frame']");
    private By textInMainFrame = By.cssSelector("[data-testid='main-frame'] p");
    private By iFrameOne = By.cssSelector("[data-testid='frame-one']");
    private By iFrameTwo = By.cssSelector("[data-testid='frame-two']");
    // The child documents are plain srcDoc HTML and do not define data-testid attributes.
    private By textInFrameOne = By.cssSelector("body p");
    private By textInFrameTwo = By.cssSelector("body p");

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

    public void switchToFrameOne() {
        switchToFrameByElement(find(iFrameOne));
    }

    public void switchToFrameTwo() {
        switchToFrameByElement(find(iFrameTwo));
    }

    public String getTextInMainFrame() {

        String mainFrameText = find(textInMainFrame).getText();
        System.out.println("Main Frame Text: " + mainFrameText);
        switchToDefaultContent();
        return mainFrameText;
    }

    public String getTextInFrameOne() {
        switchToFrameOne();
        String frameOneText = find(textInFrameOne).getText();
        System.out.println("FrameOne Text: " + frameOneText);
        switchToDefaultContent();
        return frameOneText;
    }

    public String getTextInFrameTwo() {
        switchToFrameTwo();
        String frameTwoText = find(textInFrameTwo).getText();
        System.out.println("FrameTwo Text: " + frameTwoText);
        switchToDefaultContent();
        return frameTwoText;
    }

}
