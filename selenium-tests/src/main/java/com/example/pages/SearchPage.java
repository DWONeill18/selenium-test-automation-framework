package com.example.pages;

import com.example.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.utils.DropDownUtility.getSelectedOption;
import static com.example.utils.DropDownUtility.selectByVisibleText;
import static com.example.utils.GetUtility.getAttribute;
import static com.example.utils.GetUtility.getText;

public class SearchPage extends BasePage {

    private By searchBox = By.id("nav-search");
    private By searchInput = By.id("search-input");
    private By categoryDropdown = By.id("category-select");
    private By statusDropdown = By.id("status-select");
    private By searchButton = By.id("search-button");
    private By clearButton = By.id("clear-search-button");
    private By searchResults = By.id("search-results");
    private By resultsCount = By.id("result-count");
    private By resultsTable =  By.id("search-results-table");
    private By resultRows = By.cssSelector("#search-results-table tbody tr");
    private By noResults = By.id("no-results");
    private By searchResult1 = By.id("search-result-1");
    private By searchResult2 = By.id("search-result-2");

    private By pageHeader = By.xpath("//*[@id=\"root\"]/div/div/h2");
    private By formHeader = By.xpath("//*[@id=\"root\"]/div/div/div[1]/h4");

    private By adminPageMenuButton = By.cssSelector("[data-testid='nav-admin']");
    private By logoutMenuButton = By.cssSelector("[data-testid='logout']");
    private By tablePageMenuButton = By.cssSelector("[data-testid='nav-table']");
    private By formPageMenuButton = By.cssSelector("[data-testid='nav-form']");

    public String getSearchPageHeader() {
        return getText(pageHeader);
    }

    public String getFormHeader() {
        return getText(formHeader);
    }

    public AdminPage clickAdminPageButton() {
        scroll(adminPageMenuButton);
        click(adminPageMenuButton);
        return new AdminPage();
    }

    public boolean isAdminPageButtonEnabled() {
        return find(adminPageMenuButton).isEnabled();
    }

    public TablePage clickTablePageButton() {
        scroll(tablePageMenuButton);
        click(tablePageMenuButton);
        return new TablePage();
    }

    public FormPage clickFormPageButton() {
        scroll(formPageMenuButton);
        click(formPageMenuButton);
        return new FormPage();
    }

    public LoginPage clickLogoutButton() {
        scroll(logoutMenuButton);
        click(logoutMenuButton);
        return new LoginPage();
    }

    public void clickSearchButton() {
        scroll(searchButton);
        click(searchButton);
    }

    public void clickClearButton() {
        scroll(clearButton);
        click(clearButton);
    }

    public void searchForText(String searchText) {
        scroll(searchBox);
        set(searchInput, searchText);
        click(searchInput);
    }

    public void selectCategory(String category) {
        selectByVisibleText(categoryDropdown, category);
    }

    public void selectStatus(String status) {
        selectByVisibleText(statusDropdown, status);
    }

    public String getSearchTerm() {
        return getAttribute(searchInput, "value");
    }

    public String getCategoryOption() {
        return getSelectedOption(categoryDropdown);
    }

    public String getStatusOption() {
        return getSelectedOption(statusDropdown);
    }

    public int getResultsCount() {
        return Integer.parseInt(getText(resultsCount).replaceAll("\\D+", ""));
    }

    public List<String> getSearchResults() {
        return driver.findElements(resultRows)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public String getResultsTable() {
        return getText(resultsTable);
    }

    public String getNoResultsMessage() {
        return getText(noResults);
    }
}
