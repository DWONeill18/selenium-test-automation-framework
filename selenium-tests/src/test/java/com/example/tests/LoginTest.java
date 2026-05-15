package com.example.tests;

import com.example.base.BaseTest;
import com.example.base.FormPage;
import com.example.base.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void loginTest(){
        FormPage formPage = loginPage.login("admin", "admin123");
        String actualText = formPage.getFormHeaderText();
        String expectedText = "Form";
        Assert.assertEquals(actualText, expectedText);
    }
}
