package com.example.tests.form;

import com.example.base.BaseTest;
import com.example.utils.DropDownUtility;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CountryDropDownTest extends BaseTest {

    @Test
    public void selectUKInCountryDropDownTest() {
        var formPage = loginPage.login("admin", "admin123");
        formPage.selectUKFromDropDown();

        Assert.assertTrue(DropDownUtility.isOptionSelected(formPage.countryDropdown, "UK"));
    }

    @Test
    public void selectUSInCountryDropDownTest() {
        var formPage = loginPage.login("admin", "admin123");
        formPage.selectUSFromDropDown();

        Assert.assertTrue(DropDownUtility.isOptionSelected(formPage.countryDropdown, "USA"));
    }

    @Test
    public void selectIndiaInCountryDropDownTest() {
        var formPage = loginPage.login("admin", "admin123");
        formPage.selectIndiaFromDropDown();

        Assert.assertTrue(DropDownUtility.isOptionSelected(formPage.countryDropdown, "India"));
    }
}
