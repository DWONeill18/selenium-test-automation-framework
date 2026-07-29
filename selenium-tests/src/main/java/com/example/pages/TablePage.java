package com.example.pages;

import com.example.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.example.utils.GetUtility.getText;
import static com.example.utils.SwitchToUtility.acceptAlert;
import static com.example.utils.SwitchToUtility.dismissAlert;
import static com.example.utils.SwitchToUtility.setAlertText;

public class TablePage extends BasePage {

    private By adminPageMenuButton = By.cssSelector("[data-testid='nav-admin']");
    private By logoutMenuButton = By.cssSelector("[data-testid='logout']");
    private By formPageMenuButton = By.cssSelector("[data-testid='nav-form']");

    private By tablePageHeader = By.xpath("//*[@id=\"root\"]/div/div/h2");

    private By previousButton = By.xpath("//button[text()='Prev']");
    private By nextButton = By.xpath("//button[text()='Next']");

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

    private By rowById(String id) {
        return By.xpath("//tr[td[normalize-space()='" + id + "']]");
    }

    private WebElement getRowById(String id) {
        return driver.findElement(rowById(id));
    }

    public String getUsernameById(String id) {
        return getRowById(id)
                .findElement(By.xpath("./td[2]"))
                .getText();
    }

    public String getStatusById(String id) {
        return getRowById(id)
                .findElement(By.xpath("./td[3]"))
                .getText();
    }

    public void editUser(String id, String username, String status) {
        getRowById(id)
                .findElement(By.xpath(".//button[text()='Edit']"))
                .click();
        setAlertText(username);
        acceptAlert();

        if (!isAlertPresent()) {
            return;
        }

        setAlertText(status);
        acceptAlert();
    }

    private boolean isAlertPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            wait.until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void cancelUsernameEdit(String id) {
        getRowById(id)
                .findElement(By.xpath(".//button[text()='Edit']"))
                .click();
        dismissAlert();
    }

    public void cancelStatusEdit(String id, String username) {
        getRowById(id)
                .findElement(By.xpath(".//button[text()='Edit']"))
                .click();
        setAlertText(username);
        acceptAlert();
        dismissAlert();
    }

    public void deleteUser(String id) {
        getRowById(id)
                .findElement(By.xpath(".//button[text()='Delete']"))
                .click();
    }


    public boolean userExists(String id) {
        return !driver.findElements(
            By.xpath("//tr[td[normalize-space()='" + id + "']]")
        ).isEmpty();
    }
}

