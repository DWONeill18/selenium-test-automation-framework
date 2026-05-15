package com.example.utils;

import org.openqa.selenium.By;

public class GetUtility extends Utility{

    public static String getText(By locator) {
        return driver.findElement(locator).getText();
    }

    public static String getAttribute(By locator, String attributeName) {
        return driver.findElement(locator).getDomAttribute(attributeName);
    }

    public static String getURL() {
        return driver.getCurrentUrl();
    }
}
