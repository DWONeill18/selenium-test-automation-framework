package com.example.tests.form;

import com.example.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PriorityRadioButtonsTest extends BaseTest {

    @Test
    public void selectLowPriorityRadioButtonTest() {
        var formPage = loginPage.login("admin", "admin123");
        formPage.clickLowPriorityRadioButton();

        boolean isLowPrioritySelected = formPage.isLowPriorityButtonSelected();
        Assert.assertTrue(isLowPrioritySelected,
                "\n Low Priority RadioButton is not selected \n");
        }

    @Test
    public void selectMediumPriorityRadioButtonTest() {
        var formPage = loginPage.login("admin", "admin123");
        formPage.clickMediumPriorityRadioButton();

        boolean isMediumPrioritySelected = formPage.isMediumPriorityRadioButtonSelected();
        Assert.assertTrue(isMediumPrioritySelected,
                "\n Medium Priority RadioButton is not selected \n");
    }

    @Test
    public void selectHighPriorityRadioButtonTest() {
        var formPage = loginPage.login("admin", "admin123");
        formPage.clickHighPriorityRadioButton();

        boolean isHighPrioritySelected = formPage.isHighPriorityRadioButtonSelected();
        Assert.assertTrue(isHighPrioritySelected,
                "\n High Priority RadioButton is not selected \n");
    }
}
