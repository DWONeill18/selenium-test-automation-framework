package com.example.tests.table;

import com.example.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TablePaginationTest extends BaseTest {

    @Test
    public void nextButtonShowsNextPageUsersTest() {
        var formPage = loginPage.login("admin", "admin123");
        var tablePage = formPage.clickTablePageButton();

        Assert.assertTrue(tablePage.userExists("1"),
                "User with ID 1 should exist on the first page");
        Assert.assertFalse(tablePage.userExists("6"),
                "User with ID 6 should not exist on the first page");

        tablePage.clickNextButton();

        Assert.assertFalse(tablePage.userExists("1"),
                "User with ID 1 should not exist on the next page");
        Assert.assertTrue(tablePage.userExists("6"),
                "User with ID 6 should exist on the next page");
    }

    @Test
    public void previousButtonReturnsToFirstPageUsersTest() {
        var formPage = loginPage.login("admin", "admin123");
        var tablePage = formPage.clickTablePageButton();

        tablePage.clickNextButton();
        Assert.assertTrue(tablePage.userExists("6"),
                "User with ID 6 should exist on the next page");

        tablePage.clickPreviousButton();

        Assert.assertTrue(tablePage.userExists("1"),
                "User with ID 1 should exist after returning to the first page");
        Assert.assertFalse(tablePage.userExists("6"),
                "User with ID 6 should not exist after returning to the first page");
    }

    @Test
    public void previousButtonIsDisabledOnFirstPageTest() {
        var formPage = loginPage.login("admin", "admin123");
        var tablePage = formPage.clickTablePageButton();

        Assert.assertFalse(tablePage.isPreviousButtonEnabled(),
                "Previous button should be disabled on the first page");
        Assert.assertTrue(tablePage.isNextButtonEnabled(),
                "Next button should be enabled on the first page");
    }

    @Test
    public void nextButtonIsDisabledOnLastPageTest() {
        var formPage = loginPage.login("admin", "admin123");
        var tablePage = formPage.clickTablePageButton();

        Assert.assertTrue(tablePage.isNextButtonEnabled(),
                "Next button should be enabled on the first page");

        tablePage.clickNextButton();
        tablePage.clickNextButton();

        Assert.assertFalse(tablePage.isNextButtonEnabled(),
                "Next button should be disabled on the last page");
    }
}
