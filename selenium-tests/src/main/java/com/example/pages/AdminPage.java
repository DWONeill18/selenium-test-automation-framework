package com.example.pages;

import com.example.base.BasePage;
import org.openqa.selenium.By;

import static com.example.utils.GetUtility.getText;

public class AdminPage extends BasePage {

    private By logoutMenuButton = By.cssSelector("[data-testid='logout']");
    private By tablePageMenuButton = By.cssSelector("[data-testid='nav-table']");
    private By formPageMenuButton = By.cssSelector("[data-testid='nav-form']");

    private By adminPageHeader = By.xpath("//*[@id=\"root\"]/div/div/h2");
    private By adminRestrictedText = By.xpath("//*[@id='root']/div/div/div");

    public String getAdminHeaderText() {
        return getText(adminPageHeader);
    }

    public String getAdminRestrictedText() {
        return getText(adminRestrictedText);
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
}
