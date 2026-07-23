package com.example.tests.table;

import com.example.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EditUserTest extends BaseTest {

    @Test
    public void userExistsTest() {
        var formPage = loginPage.login("admin", "admin123");
        var tablePage = formPage.clickTablePageButton();

        Assert.assertTrue(tablePage.userExists("1"),
                "User with ID 1 should exist");
    }

    @Test
    public void usernameIsCorrectTest() {
        var formPage = loginPage.login("admin", "admin123");
        var tablePage = formPage.clickTablePageButton();

        String username = tablePage.getUsernameById("1");

        Assert.assertEquals(username, "User 1");
    }

    @Test
    public void editUsernameTest() {
        var formPage = loginPage.login("admin", "admin123");
        var tablePage = formPage.clickTablePageButton();

        tablePage.editUser("1", "David", "Injured");

        Assert.assertEquals(tablePage.getUsernameById("1"), "David");
        Assert.assertEquals(tablePage.getStatusById("1"), "Injured");
    }

}
