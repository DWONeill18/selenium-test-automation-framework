package com.example.tests.form;

import com.example.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BrowserAlertCheckBoxTest extends BaseTest {

    @Test
    public void selectBrowserAlertCheckBoxTest() {
        var formsPage = loginPage.login("admin", "admin123");
        if (!formsPage.isBrowserAlertCheckBoxSelected()) {
            formsPage.clickBrowserAlertCheckBox();
        }
        boolean result = formsPage.isBrowserAlertCheckBoxSelected();
        Assert.assertTrue(result, "\n Browser Alert CheckBox is not selected");
    }

    @Test
    public void deselectBrowserAlertCheckBoxTest() {
        var formsPage = loginPage.login("admin", "admin123");
        if (formsPage.isBrowserAlertCheckBoxSelected()) {
            formsPage.unclickBrowserAlertCheckBox();
        }
        boolean result = formsPage.isBrowserAlertCheckBoxSelected();
        Assert.assertFalse(result, "\n Browser Alert CheckBox is selected");
    }

}
