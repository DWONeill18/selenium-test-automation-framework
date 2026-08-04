package com.example.tests.form;

import com.example.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FormSubmissionTest extends BaseTest {

    @Test
    public void submitWithBrowserAlertDisabledDoesNotShowAlertTest() {
        var formPage = loginPage.login("admin", "admin123");
        formPage.unclickBrowserAlertCheckBox();

        formPage.clickSubmitButton();

        Assert.assertFalse(formPage.isBrowserAlertPresent(),
                "Browser alert should not appear when alert checkbox is disabled");
        Assert.assertEquals(formPage.getSubmissionText(), "Form submitted successfully");
    }

    @Test
    public void submissionMessageDisappearsAfterDelayTest() {
        var formPage = loginPage.login("admin", "admin123");
        formPage.unclickBrowserAlertCheckBox();

        formPage.clickSubmitButton();

        Assert.assertTrue(formPage.isSubmissionTextDisplayed(),
                "Submission message should be visible immediately after submitting");
        Assert.assertTrue(formPage.waitUntilSubmissionTextIsHidden(),
                "Submission message should disappear after the configured delay");
    }
}
