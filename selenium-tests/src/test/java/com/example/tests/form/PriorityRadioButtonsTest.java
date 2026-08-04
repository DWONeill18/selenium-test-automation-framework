package com.example.tests.form;

import com.example.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PriorityRadioButtonsTest extends BaseTest {

    @Test
    public void selectLowPriorityRadioButtonTest() {
        var formPage = loginPage.login("admin", "admin123");
        formPage.clickLowPriorityRadioButton();

        Assert.assertTrue(formPage.isLowPriorityButtonSelected(),
                "\n Low Priority RadioButton is not selected \n");
        Assert.assertFalse(formPage.isMediumPriorityRadioButtonSelected(),
                "\n Medium Priority RadioButton should not be selected \n");
        Assert.assertFalse(formPage.isHighPriorityRadioButtonSelected(),
                "\n High Priority RadioButton should not be selected \n");
    }

    @Test
    public void selectMediumPriorityRadioButtonTest() {
        var formPage = loginPage.login("admin", "admin123");
        formPage.clickMediumPriorityRadioButton();

        Assert.assertTrue(formPage.isMediumPriorityRadioButtonSelected(),
                "\n Medium Priority RadioButton is not selected \n");
        Assert.assertFalse(formPage.isLowPriorityButtonSelected(),
                "\n Low Priority RadioButton should not be selected \n");
        Assert.assertFalse(formPage.isHighPriorityRadioButtonSelected(),
                "\n High Priority RadioButton should not be selected \n");
    }

    @Test
    public void selectHighPriorityRadioButtonTest() {
        var formPage = loginPage.login("admin", "admin123");
        formPage.clickHighPriorityRadioButton();

        Assert.assertTrue(formPage.isHighPriorityRadioButtonSelected(),
                "\n High Priority RadioButton is not selected \n");
        Assert.assertFalse(formPage.isLowPriorityButtonSelected(),
                "\n Low Priority RadioButton should not be selected \n");
        Assert.assertFalse(formPage.isMediumPriorityRadioButtonSelected(),
                "\n Medium Priority RadioButton should not be selected \n");
    }
}
