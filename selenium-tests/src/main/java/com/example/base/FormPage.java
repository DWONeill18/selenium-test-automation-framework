package com.example.base;

import org.openqa.selenium.By;

import static com.example.utils.GetUtility.getText;

public class FormPage extends BasePage {

    private By formHeader = By.xpath("//*[@id=\"root\"]/div/div/h2");

    public String getFormHeaderText() {
        return getText(formHeader);
    }
}
