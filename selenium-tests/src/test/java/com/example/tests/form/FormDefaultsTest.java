package com.example.tests.form;

import com.example.base.BaseTest;
import com.example.utils.DropDownUtility;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FormDefaultsTest extends BaseTest {

    @Test
    public void formLoadsWithExpectedDefaultsTest() {
        var formPage = loginPage.login("admin", "admin123");

        Assert.assertTrue(formPage.isBrowserAlertCheckBoxSelected(),
                "Browser alert checkbox should be selected by default");
        Assert.assertTrue(formPage.isLowPriorityButtonSelected(),
                "Low priority should be selected by default");
        Assert.assertFalse(formPage.isMediumPriorityRadioButtonSelected(),
                "Medium priority should not be selected by default");
        Assert.assertFalse(formPage.isHighPriorityRadioButtonSelected(),
                "High priority should not be selected by default");
        Assert.assertFalse(formPage.isSportSelected(),
                "Sports checkbox should not be selected by default");
        Assert.assertFalse(formPage.isMusicSelected(),
                "Music checkbox should not be selected by default");
        Assert.assertFalse(formPage.isCodingSelected(),
                "Coding checkbox should not be selected by default");
        Assert.assertTrue(DropDownUtility.isOptionSelected(formPage.countryDropdown, "UK"),
                "UK should be selected by default");
        Assert.assertFalse(formPage.isSubmissionTextDisplayed(),
                "Submission message should not be visible before submitting");
    }
}
