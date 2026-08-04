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

    @Test(enabled = false)
    public void deletingLastPageUsersKeepsPaginationOnValidPageTest() {
        // Disabled until the app clamps pageIndex after deleting rows from the final page.
        // Current behavior leaves the table on an invalid page indicator, for example 3/2.
        var formPage = loginPage.login("admin", "admin123");
        var tablePage = formPage.clickTablePageButton();

        tablePage.clickNextButton();
        tablePage.clickNextButton();
        Assert.assertEquals(tablePage.getPageIndicatorText(), "3/3");
        Assert.assertTrue(tablePage.userExists("11"),
                "User with ID 11 should exist on the last page");
        Assert.assertTrue(tablePage.userExists("12"),
                "User with ID 12 should exist on the last page");

        tablePage.deleteUser("11");
        tablePage.deleteUser("12");

        Assert.assertEquals(tablePage.getPageIndicatorText(), "2/2",
                "Pagination should move to the last valid page after deleting the final page rows");
        Assert.assertTrue(tablePage.userExists("6"),
                "Second page users should be visible after the last page is removed");
        Assert.assertFalse(tablePage.isNextButtonEnabled(),
                "Next button should be disabled on the new last page");
    }
}
