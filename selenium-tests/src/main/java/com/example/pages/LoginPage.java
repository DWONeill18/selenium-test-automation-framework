package com.example.pages;

import com.example.base.BasePage;
import org.openqa.selenium.By;

import static com.example.utils.GetUtility.getText;

public class LoginPage extends BasePage {

    private By usernameField  = By.cssSelector("[data-testid='username']");
    private By passwordField = By.cssSelector("[data-testid='password']");
    private By loginButton = By.cssSelector("[data-testid='login-btn']");
    private By errorMessage = By.cssSelector("[data-testid='login-error']");

    public void setUsername(String username) {
        set(usernameField, username);
    }

    public void setPassword(String password) {
        set(passwordField, password);
    }

    public FormPage clickLoginButton() {
        scroll(loginButton);
        click(loginButton);
        return new FormPage();
    }

    public FormPage login(String username, String password) {
        setUsername(username);
        setPassword(password);
        return clickLoginButton();
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }
}
