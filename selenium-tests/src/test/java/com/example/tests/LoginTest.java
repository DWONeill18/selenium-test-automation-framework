package com.example.tests;

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
    public void failingLoginTest(){
        loginPage.login("abc", "abc123");
        String actualText = loginPage.getErrorMessage();
        String expectedText = "Login failed";
        Assert.assertEquals(actualText, expectedText);
    }


}
