package com.example.pages;

import com.example.base.BasePage;
import org.openqa.selenium.By;

import static com.example.utils.DropDownUtility.selectByVisibleText;
import static com.example.utils.GetUtility.getText;

public class FormPage extends BasePage {

    private By formHeader = By.xpath("//*[@id=\"root\"]/div/div/h2");

    private By browserAlertCheckBox = By.cssSelector("[data-testid='toggle-alert']");

    private By lowPriorityRadioButton = By.xpath("//label[contains(.,'medium')]//input[@type='radio']");
    private By mediumPriorityRadioButton = By.xpath("//label[contains(.,'medium')]//input[@type='radio']");
    private By highPriorityRadioButton = By.xpath("//label[contains(.,'high')]//input[@type='radio']");

    private By sportsCheckBox = By.xpath("//label[contains(.,'Sports')]//input[@type='checkbox']");
    private By codingCheckBox = By.xpath("//label[contains(.,'Coding')]//input[@type='checkbox']");
    private By musicCheckBox = By.xpath("//label[contains(.,'Music')]//input[@type='checkbox']");

    public By countryDropdown = By.xpath("//h4[contains(.,'Country')]/following::select[1]");

    private By submitButton = By.xpath("//button[text()='Submit']");

    private By formSubmissionText = By.xpath("//div[@data-testid='form-success']");

    private By fakeElement = By.id("fake-id");

    public String getFormHeaderText() {
        return getText(formHeader);
    }

    public void clickBrowserAlertCheckBox() {
        if (!find(browserAlertCheckBox).isSelected()) {
            waitForElementToBeClickable(browserAlertCheckBox, 5);
            scroll(browserAlertCheckBox);
            click(browserAlertCheckBox);
        }
    }

    public void unclickBrowserAlertCheckBox() {
        if (find(browserAlertCheckBox).isSelected()) {
            waitForElementToBeClickable(browserAlertCheckBox, 5);
            scroll(browserAlertCheckBox);
            click(browserAlertCheckBox);
        }
    }

    public String getSubmissionText() {
        return getText(formSubmissionText);
    }

    public boolean isBrowserAlertCheckBoxSelected() {
        return find(browserAlertCheckBox).isSelected();
    }

    public void clickLowPriorityRadioButton() {
        scroll(lowPriorityRadioButton);
        click(lowPriorityRadioButton);
    }

    public void clickMediumPriorityRadioButton() {
        scroll(mediumPriorityRadioButton);
        click(mediumPriorityRadioButton);
    }

    public void clickHighPriorityRadioButton() {
        scroll(highPriorityRadioButton);
        click(highPriorityRadioButton);
    }

    public boolean isLowPriorityButtonSelected() {
        return find(lowPriorityRadioButton).isSelected();
    }

    public boolean isMediumPriorityRadioButtonSelected() {
        return find(mediumPriorityRadioButton).isSelected();
    }

    public boolean isHighPriorityRadioButtonSelected() {
        return find(highPriorityRadioButton).isSelected();
    }

    public void clickSportCheckBox() {
        if (!find(sportsCheckBox).isSelected()) {
        waitForElementToBeClickable(sportsCheckBox, 5);
        scroll(sportsCheckBox);
        click(sportsCheckBox);
        }
    }

    public void unclickSportCheckBox() {
        if (find(sportsCheckBox).isSelected()) {
            waitForElementToBeClickable(sportsCheckBox, 5);
            scroll(sportsCheckBox);
            click(sportsCheckBox);
        }
    }

    public void clickCodingCheckBox() {
        if (!find(codingCheckBox).isSelected()) {
            waitForElementToBeClickable(codingCheckBox, 5);
            scroll(codingCheckBox);
            click(codingCheckBox);
        }
    }

    public void unclickCodingCheckBox() {
        if (find(codingCheckBox).isSelected()) {
            waitForElementToBeClickable(codingCheckBox, 5);
            scroll(codingCheckBox);
            click(codingCheckBox);
        }
    }

    public void clickMusicCheckBox() {
        if (!find(musicCheckBox).isSelected()) {
            waitForElementToBeClickable(musicCheckBox, 5);
            scroll(musicCheckBox);
            click(musicCheckBox);
        }
    }

    public void unclickMusicCheckBox() {
        if (find(musicCheckBox).isSelected()) {
            waitForElementToBeClickable(musicCheckBox, 5);
            scroll(musicCheckBox);
            click(musicCheckBox);
        }
    }

    public boolean isSportSelected() {
        return find(sportsCheckBox).isSelected();
    }

    public boolean isCodingSelected() {
        return find(codingCheckBox).isSelected();
    }

    public boolean isMusicSelected() {
        return find(musicCheckBox).isSelected();
    }

    public void selectUKFromDropDown() {
        selectByVisibleText(countryDropdown, "UK");
    }

    public void selectUSFromDropDown() {
        selectByVisibleText(countryDropdown, "USA");
    }

    public void selectIndiaFromDropDown() {
        selectByVisibleText(countryDropdown, "India");
    }

    public void clickSubmitButton() {
        scroll(submitButton);
        click(submitButton);
    }

    public void clickFakeElement() {
        click(fakeElement);
    }
}
