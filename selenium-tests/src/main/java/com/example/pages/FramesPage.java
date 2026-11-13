package com.example.pages;

import com.example.base.BasePage;
import org.openqa.selenium.By;

public class FramesPage extends BasePage {

    private By adminPageMenuButton = By.cssSelector("[data-testid='nav-admin']");
    private By logoutMenuButton = By.cssSelector("[data-testid='logout']");
    private By tablePageMenuButton = By.cssSelector("[data-testid='nav-table']");
    private By formPageMenuButton = By.cssSelector("[data-testid='nav-form']");
    private By progressPageMenuButton = By.cssSelector("[data-testid='nav-progress']");

    private By pageHeader = By.xpath("//*[@id=\"root\"]/div/section/h2");
    private By mainFrameHeader = By.id("data-testid='main-frame'");
    private By textInMainFrame = By.xpath("//*[@id=\"root\"]/div/div/h3");
    private By iFrameOne = By.id("data-testid='frame-one'");
    private By iFrameTwo = By.id("data-testid='frame-two'");



}
