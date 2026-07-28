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

    @Test
    public void editOneUserOnlyTest() {
        var formPage = loginPage.login("admin", "admin123");
        var tablePage = formPage.clickTablePageButton();

        String userTwoUsername = tablePage.getUsernameById("2");
        String userTwoStatus = tablePage.getStatusById("2");
        String userThreeUsername = tablePage.getUsernameById("3");
        String userThreeStatus = tablePage.getStatusById("3");
        String userFourUsername = tablePage.getUsernameById("4");
        String userFourStatus = tablePage.getStatusById("4");

        tablePage.editUser("1", "David", "Injured");

        Assert.assertEquals(tablePage.getUsernameById("1"), "David");
        Assert.assertEquals(tablePage.getStatusById("1"), "Injured");
        Assert.assertEquals(tablePage.getUsernameById("2"), userTwoUsername);
        Assert.assertEquals(tablePage.getStatusById("2"), userTwoStatus);
        Assert.assertEquals(tablePage.getUsernameById("3"), userThreeUsername);
        Assert.assertEquals(tablePage.getStatusById("3"), userThreeStatus);
        Assert.assertEquals(tablePage.getUsernameById("4"), userFourUsername);
        Assert.assertEquals(tablePage.getStatusById("4"), userFourStatus);
    }

    @Test
    public void editSecondUserTest() {
        var formPage = loginPage.login("admin", "admin123");
        var tablePage = formPage.clickTablePageButton();

        tablePage.editUser("2", "Alex", "Available");

        Assert.assertEquals(tablePage.getUsernameById("2"), "Alex");
        Assert.assertEquals(tablePage.getStatusById("2"), "Available");
    }

    @Test
    public void editUsernameOnlyTest() {
        var formPage = loginPage.login("admin", "admin123");
        var tablePage = formPage.clickTablePageButton();

        String originalStatus = tablePage.getStatusById("1");

        tablePage.editUser("1", "David", originalStatus);

        Assert.assertEquals(tablePage.getUsernameById("1"), "David");
        Assert.assertEquals(tablePage.getStatusById("1"), originalStatus);
    }

    @Test
    public void editStatusOnlyTest() {
        var formPage = loginPage.login("admin", "admin123");
        var tablePage = formPage.clickTablePageButton();

        String originalUsername = tablePage.getUsernameById("1");

        tablePage.editUser("1", originalUsername, "Injured");

        Assert.assertEquals(tablePage.getUsernameById("1"), originalUsername);
        Assert.assertEquals(tablePage.getStatusById("1"), "Injured");
    }

    @Test
    public void cancelUsernameEditDoesNotChangeUserTest() {
        var formPage = loginPage.login("admin", "admin123");
        var tablePage = formPage.clickTablePageButton();

        String originalUsername = tablePage.getUsernameById("1");
        String originalStatus = tablePage.getStatusById("1");

        tablePage.cancelUsernameEdit("1");

        Assert.assertEquals(tablePage.getUsernameById("1"), originalUsername);
        Assert.assertEquals(tablePage.getStatusById("1"), originalStatus);
    }

    @Test
    public void cancelStatusEditDoesNotChangeUserTest() {
        var formPage = loginPage.login("admin", "admin123");
        var tablePage = formPage.clickTablePageButton();

        String originalUsername = tablePage.getUsernameById("1");
        String originalStatus = tablePage.getStatusById("1");

        tablePage.cancelStatusEdit("1", originalUsername);

        Assert.assertEquals(tablePage.getUsernameById("1"), originalUsername);
        Assert.assertEquals(tablePage.getStatusById("1"), originalStatus);
    }

    @Test
    public void deleteUserTest() {
        var formPage = loginPage.login("admin", "admin123");
        var tablePage = formPage.clickTablePageButton();

        tablePage.deleteUser("1");

        Assert.assertFalse(tablePage.userExists("1"), "User with ID 1 should not exist");
    }

    @Test
    public void deleteOneUserOnlyTest() {
        var formPage = loginPage.login("admin", "admin123");
        var tablePage = formPage.clickTablePageButton();

        tablePage.deleteUser("1");

        Assert.assertFalse(tablePage.userExists("1"), "User with ID 1 should not exist");
        Assert.assertTrue(tablePage.userExists("2"), "User with ID 2 should exist");
        Assert.assertTrue(tablePage.userExists("3"), "User with ID 3 should exist");
        Assert.assertTrue(tablePage.userExists("4"), "User with ID 4 should exist");
    }

}
