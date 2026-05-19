package com.example.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class DropDownUtility extends Utility{

        private static Select findDropDown(By locator) { return new Select(driver.findElement(locator)); }

        public static void selectByVisibleText(By locator, String text) {
            findDropDown(locator).selectByVisibleText(text);
        }

    public static void selectByValue(By locator, String value) {
        findDropDown(locator).selectByValue(value);
    }

    public static void selectByIndex(int index, By locator) {
        findDropDown(locator).selectByIndex(index);
    }

    public static String getSelectedOption(By locator) {
            return findDropDown(locator).getFirstSelectedOption().getText();
    }

    public static boolean isOptionSelected(By locator, String expectedOption) {
            return getSelectedOption(locator).equals(expectedOption);
    }
}
