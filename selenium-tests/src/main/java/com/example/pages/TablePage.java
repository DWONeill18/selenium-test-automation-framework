package com.example.pages;

import com.example.base.BasePage;
import org.openqa.selenium.By;

import static com.example.utils.GetUtility.getText;

public class TablePage extends BasePage {

    private By adminPageMenuButton = By.cssSelector("[data-testid='nav-admin']");
    private By logoutMenuButton = By.cssSelector("[data-testid='logout']");
    private By formPageMenuButton = By.cssSelector("[data-testid='nav-form']");

    private By tablePageHeader = By.xpath("//*[@id=\"root\"]/div/div/h2");

    private By previousButton = By.xpath("//button[text()='Prev']");
    private By nextButton = By.xpath("//button[text()='Next']");

    private By editUser1Button = By.xpath("//tr[td[contains(text(),'User1')]]//button[text()='Edit']");

    public String getTableHeaderText() {
        return getText(tablePageHeader);
    }

    public AdminPage clickAdminPageButton() {
        scroll(adminPageMenuButton);
        click(adminPageMenuButton);
        return new AdminPage();
    }

    public boolean isAdminPageButtonEnabled() {
        return find(adminPageMenuButton).isEnabled();
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
