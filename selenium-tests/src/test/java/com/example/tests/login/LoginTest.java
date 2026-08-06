package com.example.tests.login;

import com.example.base.BaseTest;
import com.example.pages.FormPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void adminLoginTest(){
        FormPage formPage = loginPage.login("admin", "admin123");
        String actualText = formPage.getFormHeaderText();
        String expectedText = "Form";
        Assert.assertEquals(actualText, expectedText);
    }

    @Test
    public void testerLoginTest(){
        FormPage formPage = loginPage.login("tester", "test123");
        String actualText = formPage.getFormHeaderText();
        String expectedText = "Form";
        Assert.assertEquals(actualText, expectedText);
    }

    @Test
    public void blankUsernameAndPasswordLoginTest(){
        loginPage.login("", "");

        Assert.assertEquals(loginPage.getErrorMessage(), "Login failed");
        Assert.assertEquals(loginPage.getLoginHeaderText(), "Login");
    }

    @Test
    public void validUsernameWrongPasswordLoginTest(){
        loginPage.login("admin", "wrong-password");

        Assert.assertEquals(loginPage.getErrorMessage(), "Login failed");
        Assert.assertEquals(loginPage.getLoginHeaderText(), "Login");
    }

    @Test
    public void wrongUsernameValidPasswordLoginTest(){
        loginPage.login("wrong-user", "admin123");

        Assert.assertEquals(loginPage.getErrorMessage(), "Login failed");
        Assert.assertEquals(loginPage.getLoginHeaderText(), "Login");
    }

    @Test
    public void whitespacePaddedCredentialsLoginTest(){
        loginPage.login(" admin ", " admin123 ");

        Assert.assertEquals(loginPage.getErrorMessage(), "Login failed");
        Assert.assertEquals(loginPage.getLoginHeaderText(), "Login");
    }


}
