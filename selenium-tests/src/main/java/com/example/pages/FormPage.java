package com.example.base;

import org.openqa.selenium.By;

import static com.example.utils.GetUtility.getText;

public class FormPage extends BasePage {

    private By formHeader = By.xpath("//*[@id=\"root\"]/div/div/h2");

    private By femaleRadioButton = By.id("gender-radio-2");
    private By maleRadioButton = By.id("gender-radio-2");

    private By sportsHobbyCheckBox = By.id("hobbies-checkbox-1");
    private By readingHobbyCheckBox = By.id("hobbies-checkbox-2");
    private By musicHobbyCheckBox = By.id("hobbies-checkbox-3");

    private By submitButton = By.id("submit");
    private By bannerCheckBox = By.id("banner-checkbox-1");


    public String getFormHeaderText() {
        return getText(formHeader);
    }

    public void clickFemaleRadioButton() {
        scroll(femaleRadioButton);
        click(femaleRadioButton);
    }

    public void clickMaleRadioButton() {
        scroll(maleRadioButton);
        click(maleRadioButton);
    }

    public boolean isFemaleRadioButtonSelected() {
        return find(femaleRadioButton).isSelected();
    }

    public boolean isMaleRadioButtonSelected() {
        return find(maleRadioButton).isSelected();
    }

    public void clickSportsHobbyCheckBox() {
        if (!find(sportsHobbyCheckBox).isSelected()) {
        waitForElementToBeClickable(sportsHobbyCheckBox, 5);
        scroll(sportsHobbyCheckBox);
        click(sportsHobbyCheckBox);
        }
    }

    public void clickReadingHobbyCheckBox() {
        if (!find(readingHobbyCheckBox).isSelected()) {
            waitForElementToBeClickable(readingHobbyCheckBox, 5);
            scroll(readingHobbyCheckBox);
            click(readingHobbyCheckBox);
        }
    }

    public void clickMusicHobbyCheckBox() {
        if (!find(musicHobbyCheckBox).isSelected()) {
            waitForElementToBeClickable(musicHobbyCheckBox, 5);
            scroll(musicHobbyCheckBox);
            click(musicHobbyCheckBox);
        }
    }

    public void clickSubmitButton() {
        scroll(submitButton);
        click(submitButton);
    }

    public void clickBannerCheckBox() {
        if (!find(bannerCheckBox).isSelected()) {
            waitForElementToBeClickable(bannerCheckBox, 5);
            scroll(bannerCheckBox);
            click(bannerCheckBox);
        }
    }
}
