package com.example.tests.navigation;

import com.example.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationButtonsTest extends BaseTest {

    @Test
    public void loginAsAdminAccessAdminPageTest() {
        var formPage = loginPage.login("admin", "admin123");
        var adminPage = formPage.clickAdminPageButton();
        String expectedHeaderText = "Admin Page";
        String expectedAdminPageText = "Restricted admin content";

        Assert.assertTrue(formPage.isAdminPageButtonEnabled(),
                "Admin page button should be enabled for admin user");
        Assert.assertEquals(adminPage.getAdminHeaderText(), expectedHeaderText);
        Assert.assertEquals(adminPage.getAdminRestrictedText(), expectedAdminPageText);
    }

    @Test
    public void adminPageButtonIsDisabledForTesterTest() {
        var formPage = loginPage.login("tester", "test123");

        Assert.assertFalse(formPage.isAdminPageButtonEnabled(),
                "Admin page button should be disabled for tester user");
    }
}
