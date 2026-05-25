package com.example.tests.form;

import com.example.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FormSubmissionTest extends BaseTest {

    @Test
    public void formSubmissionTest() {
        var  formPage = loginPage.login("admin", "admin123");
        formPage.unclickBrowserAlertCheckBox();
        formPage.clickSubmitButton();

        String submissionText = formPage.getSubmissionText();
        String actualSubmissionText = "Form submitted successfully";
        Assert.assertEquals(submissionText, actualSubmissionText, "\n Form submission text is incorrect \n");
    }
}
