package com.example.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class WaitUtility extends Utility {

    public static String fluentWaitUntilTextContains(int seconds, By locator, String expectedText) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(seconds))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
        return wait.until(webDriver -> {
            WebElement element = webDriver.findElement(locator);
            String text = element.getText();
            return text.contains(expectedText) ? text : null;
        });
    }

    public static String fluentWaitUntilIntermediatePercentage(int seconds, By locator) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(seconds))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
        return wait.until(webDriver -> {
            String text = webDriver.findElement(locator).getText().trim();
            try {
                int percentage = Integer.parseInt(text.replace("%", ""));
                return percentage > 0 && percentage < 100 ? text : null;
            } catch (NumberFormatException ignored) {
                return null;
            }
        });
    }

    public static void fluentWaitUntilText(int seconds, By locator, String expectedText) {
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(seconds))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
        fluentWait.until(ExpectedConditions.textToBe(locator, expectedText));
    }

    public static void fluentWaitUntilVisible(int seconds, By locator) {
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(seconds))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

}


