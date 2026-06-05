package com.example.tests.navigation;

import com.example.base.BaseTest;
import com.example.pages.AdminPage;
import com.example.pages.FormPage;
import com.example.pages.LoginPage;
import com.example.pages.TablePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationButtonsTest extends BaseTest {

    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";
    private static final String TESTER_USERNAME = "tester";
    private static final String TESTER_PASSWORD = "test123";
    private static final String FORM_HEADER_TEXT = "Form";
    private static final String TABLE_HEADER_TEXT = "Table";
    private static final String ADMIN_HEADER_TEXT = "Admin Page";
    private static final String ADMIN_RESTRICTED_TEXT = "Restricted admin content";
    private static final String LOGIN_HEADER_TEXT = "Login";

    @Test
    public void loginAsAdminAccessAdminPageReturnToFormPageTest() {
        var formPage = loginAsAdmin();
        assertAdminPageButtonEnabled(formPage);

        var adminPage = formPage.clickAdminPageButton();
        assertOnAdminPage(adminPage);

        var returningFormPage = adminPage.clickFormPageButton();
        assertOnFormPage(returningFormPage);
    }

    @Test
    public void adminPageButtonIsDisabledForTesterTest() {
        var formPage = loginAsTester();

        assertAdminPageButtonDisabled(formPage);
    }

    @Test
    public void formPageToTablePageToFormPageTest() {
        var formPage = loginAsAdmin();

        var tablePage = formPage.clickTablePageButton();
        assertOnTablePage(tablePage);

        var returningFormPage = tablePage.clickFormPageButton();
        assertOnFormPage(returningFormPage);
    }

    @Test
    public void tablePageToAdminPageToTablePageTest() {
        var formPage = loginAsAdmin();

        var tablePage = formPage.clickTablePageButton();
        assertOnTablePage(tablePage);
        assertAdminPageButtonEnabled(tablePage);

        var adminPage = tablePage.clickAdminPageButton();
        assertOnAdminPage(adminPage);

        var returningTablePage = adminPage.clickTablePageButton();
        assertOnTablePage(returningTablePage);
    }

    @Test
    public void logoutFromFormPage() {
        var formPage = loginAsAdmin();
        assertOnFormPage(formPage);

        var loginPage = formPage.clickLogoutButton();
        assertOnLoginPage(loginPage);
    }

    @Test
    public void logoutFromTablePage() {
        var formPage = loginAsAdmin();

        var tablePage = formPage.clickTablePageButton();
        assertOnTablePage(tablePage);

        var loginPage = tablePage.clickLogoutButton();
        assertOnLoginPage(loginPage);
    }

    @Test
    public void logoutFromAdminPage() {
        var formPage = loginAsAdmin();
        assertAdminPageButtonEnabled(formPage);

        var adminPage = formPage.clickAdminPageButton();
        assertOnAdminPage(adminPage);

        var loginPage = adminPage.clickLogoutButton();
        assertOnLoginPage(loginPage);
    }

    private FormPage loginAsAdmin() {
        return loginPage.login(ADMIN_USERNAME, ADMIN_PASSWORD);
    }

    private FormPage loginAsTester() {
        return loginPage.login(TESTER_USERNAME, TESTER_PASSWORD);
    }

    private void assertOnFormPage(FormPage formPage) {
        Assert.assertEquals(formPage.getFormHeaderText(), FORM_HEADER_TEXT);
    }

    private void assertOnTablePage(TablePage tablePage) {
        Assert.assertEquals(tablePage.getTableHeaderText(), TABLE_HEADER_TEXT);
    }

    private void assertOnAdminPage(AdminPage adminPage) {
        Assert.assertEquals(adminPage.getAdminHeaderText(), ADMIN_HEADER_TEXT);
        Assert.assertEquals(adminPage.getAdminRestrictedText(), ADMIN_RESTRICTED_TEXT);
    }

    private void assertOnLoginPage(LoginPage loginPage) {
        Assert.assertEquals(loginPage.getLoginHeaderText(), LOGIN_HEADER_TEXT);
    }

    private void assertAdminPageButtonEnabled(FormPage formPage) {
        Assert.assertTrue(formPage.isAdminPageButtonEnabled(),
                "Admin page button should be enabled for admin user");
    }

    private void assertAdminPageButtonEnabled(TablePage tablePage) {
        Assert.assertTrue(tablePage.isAdminPageButtonEnabled(),
                "Admin page button should be enabled for admin user");
    }

    private void assertAdminPageButtonDisabled(FormPage formPage) {
        Assert.assertFalse(formPage.isAdminPageButtonEnabled(),
                "Admin page button should be disabled for tester user");
    }
}
