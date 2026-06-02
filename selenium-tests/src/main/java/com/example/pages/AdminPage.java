package com.example.pages;

import com.example.base.BasePage;
import org.openqa.selenium.By;

import static com.example.utils.GetUtility.getText;

public class AdminPage extends BasePage {

    private By adminPageHeader = By.xpath("//*[@id=\"root\"]/div/div/h2");
    private By adminRestrictedText = By.xpath("//*[@id='root']/div/div/div");

    public String getAdminHeaderText() {
        return getText(adminPageHeader);
    }

    public String getAdminRestrictedText() {
        return getText(adminRestrictedText);
    }
}
