package com.example.tests.form;

import com.example.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HobbyCheckBoxTest extends BaseTest {

    @Test
    public void checkAllHobbyCheckBoxTest() {
        var formPage = loginPage.login("admin", "admin123");
        formPage.clickSportCheckBox();
        formPage.clickMusicCheckBox();
        formPage.clickCodingCheckBox();

        boolean isSportsBoxSelected = formPage.isSportSelected();
        boolean isMusicBoxSelected = formPage.isMusicSelected();
        boolean isCodingBoxSelected = formPage.isCodingSelected();

        Assert.assertTrue(isSportsBoxSelected, "/n Sports CheckBox is not selected");
        Assert.assertTrue(isMusicBoxSelected, "/n Music CheckBox is not selected");
        Assert.assertTrue(isCodingBoxSelected, "/n Coding CheckBox is not selected");
    }

    @Test
    public void uncheckAllHobbyCheckBoxTest() {
        var formPage = loginPage.login("admin", "admin123");
        formPage.clickSportCheckBox();
        formPage.clickMusicCheckBox();
        formPage.clickCodingCheckBox();
        formPage.unclickSportCheckBox();
        formPage.unclickMusicCheckBox();
        formPage.unclickCodingCheckBox();

        boolean isSportsBoxSelected = formPage.isSportSelected();
        boolean isMusicBoxSelected = formPage.isMusicSelected();
        boolean isCodingBoxSelected = formPage.isCodingSelected();

        Assert.assertFalse(isSportsBoxSelected, "/n Sports CheckBox is selected");
        Assert.assertFalse(isMusicBoxSelected, "/n Music CheckBox is selected");
        Assert.assertFalse(isCodingBoxSelected, "/n Coding CheckBox is selected");
    }


}
