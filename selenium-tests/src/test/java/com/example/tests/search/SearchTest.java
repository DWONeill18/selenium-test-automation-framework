package com.example.tests.search;

import com.example.base.BaseTest;
import com.example.pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    @Test
    public void searchPageLoadedTest() {
        SearchPage searchPage = openSearchPage();

        Assert.assertEquals(searchPage.getSearchPageHeader(), "Search");
        Assert.assertEquals(searchPage.getFormHeader(), "Practice Search Form");
    }

    @Test
    public void allCategoriesSelectedShowsAllResultsTest() {
        SearchPage searchPage = openSearchPage();

        Assert.assertEquals(searchPage.getCategoryOption(), "All");
        Assert.assertEquals(searchPage.getResultsCount(), 8);
        Assert.assertEquals(searchPage.getSearchResults().size(), 8);
    }

    @Test
    public void searchDefaultsAreSelectedOnPageLoadTest() {
        SearchPage searchPage = openSearchPage();

        Assert.assertEquals(searchPage.getSearchTerm(), "");
        Assert.assertEquals(searchPage.getCategoryOption(), "All");
        Assert.assertEquals(searchPage.getStatusOption(), "All");
        Assert.assertEquals(searchPage.getResultsCount(), 8);
    }

    @Test
    public void individualSearchItemExistsWhenAllCategoriesSelectedTest() {
        SearchPage searchPage = openSearchPage();

        Assert.assertTrue(resultExists(searchPage, "Selenium WebDriver"));
    }

    @Test
    public void searchTestingReturnsThreeResultsTest() {
        SearchPage searchPage = openSearchPage();

        searchPage.searchForText("testing");
        searchPage.clickSearchButton();

        Assert.assertEquals(searchPage.getResultsCount(), 3);
        Assert.assertEquals(searchPage.getSearchResults().size(), 3);
        Assert.assertTrue(resultExists(searchPage, "Selenium WebDriver"));
        Assert.assertTrue(resultExists(searchPage, "TestNG"));
        Assert.assertTrue(resultExists(searchPage, "Page Object Model"));
    }

    @Test
    public void searchIsCaseInsensitiveTest() {
        SearchPage searchPage = openSearchPage();

        searchPage.searchForText("MaVeN");
        searchPage.clickSearchButton();

        Assert.assertEquals(searchPage.getResultsCount(), 1);
        Assert.assertTrue(resultExists(searchPage, "Maven"));
    }

    @Test
    public void searchMavenReturnsOneResultTest() {
        SearchPage searchPage = openSearchPage();

        searchPage.searchForText("maven");
        searchPage.clickSearchButton();

        Assert.assertEquals(searchPage.getResultsCount(), 1);
        Assert.assertEquals(searchPage.getSearchResults().size(), 1);
        Assert.assertTrue(resultExists(searchPage, "Maven"));
    }

    @Test
    public void whitespaceSearchTermIsTrimmedTest() {
        SearchPage searchPage = openSearchPage();

        searchPage.searchForText("  maven  ");
        searchPage.clickSearchButton();

        Assert.assertEquals(searchPage.getResultsCount(), 1);
        Assert.assertTrue(resultExists(searchPage, "Maven"));
    }

    @Test
    public void emptySearchReturnsAllResultsTest() {
        SearchPage searchPage = openSearchPage();

        searchPage.searchForText("");
        searchPage.clickSearchButton();

        Assert.assertEquals(searchPage.getResultsCount(), 8);
        Assert.assertEquals(searchPage.getSearchResults().size(), 8);
    }

    @Test
    public void searchByCategoryTextReturnsMatchingResultTest() {
        SearchPage searchPage = openSearchPage();

        searchPage.searchForText("frontend");
        searchPage.clickSearchButton();

        Assert.assertEquals(searchPage.getResultsCount(), 1);
        Assert.assertTrue(resultExists(searchPage, "React"));
    }

    @Test
    public void searchPythonReturnsNoResultsTest() {
        SearchPage searchPage = openSearchPage();

        searchPage.searchForText("Python");
        searchPage.clickSearchButton();

        Assert.assertEquals(searchPage.getResultsCount(), 0);
        Assert.assertTrue(searchPage.getSearchResults().isEmpty());
        Assert.assertEquals(searchPage.getNoResultsMessage(), "No results found");
    }

    @Test
    public void noResultsShowsMessageAndNoRowsTest() {
        SearchPage searchPage = openSearchPage();

        searchPage.searchForText("Python");
        searchPage.clickSearchButton();

        Assert.assertTrue(searchPage.getSearchResults().isEmpty());
        Assert.assertEquals(searchPage.getNoResultsMessage(), "No results found");
    }

    @Test
    public void testingCategoryReturnsThreeResultsTest() {
        SearchPage searchPage = openSearchPage();

        searchPage.selectCategory("Testing");
        searchPage.clickSearchButton();

        Assert.assertEquals(searchPage.getResultsCount(), 3);
        Assert.assertEquals(searchPage.getSearchResults().size(), 3);
    }

    @Test
    public void testingCategoryAndActiveStatusReturnsThreeResultsTest() {
        SearchPage searchPage = openSearchPage();

        searchPage.selectCategory("Testing");
        searchPage.selectStatus("Active");
        searchPage.clickSearchButton();

        Assert.assertEquals(searchPage.getResultsCount(), 3);
        Assert.assertEquals(searchPage.getSearchResults().size(), 3);
    }

    @Test
    public void searchTermCategoryAndStatusFiltersCanBeCombinedTest() {
        SearchPage searchPage = openSearchPage();

        searchPage.searchForText("testing");
        searchPage.selectCategory("Testing");
        searchPage.selectStatus("Active");
        searchPage.clickSearchButton();

        Assert.assertEquals(searchPage.getResultsCount(), 3);
        Assert.assertEquals(searchPage.getSearchResults().size(), 3);
        Assert.assertTrue(resultExists(searchPage, "Selenium WebDriver"));
        Assert.assertTrue(resultExists(searchPage, "TestNG"));
        Assert.assertTrue(resultExists(searchPage, "Page Object Model"));
    }

    @Test
    public void testingCategoryAndInactiveStatusReturnsNoResultsTest() {
        SearchPage searchPage = openSearchPage();

        searchPage.selectCategory("Testing");
        searchPage.selectStatus("Inactive");
        searchPage.clickSearchButton();

        Assert.assertEquals(searchPage.getResultsCount(), 0);
        Assert.assertTrue(searchPage.getSearchResults().isEmpty());
        Assert.assertEquals(searchPage.getNoResultsMessage(), "No results found");
    }

    @Test
    public void mismatchedSearchTermAndCategoryReturnsNoResultsTest() {
        SearchPage searchPage = openSearchPage();

        searchPage.searchForText("maven");
        searchPage.selectCategory("Testing");
        searchPage.clickSearchButton();

        Assert.assertEquals(searchPage.getResultsCount(), 0);
        Assert.assertTrue(searchPage.getSearchResults().isEmpty());
        Assert.assertEquals(searchPage.getNoResultsMessage(), "No results found");
    }

    @Test
    public void activeStatusReturnsSevenResultsTest() {
        SearchPage searchPage = openSearchPage();

        searchPage.selectStatus("Active");
        searchPage.clickSearchButton();

        Assert.assertEquals(searchPage.getResultsCount(), 7);
        Assert.assertEquals(searchPage.getSearchResults().size(), 7);
    }

    @Test
    public void inactiveStatusReturnsOneResultTest() {
        SearchPage searchPage = openSearchPage();

        searchPage.selectStatus("Inactive");
        searchPage.clickSearchButton();

        Assert.assertEquals(searchPage.getResultsCount(), 1);
        Assert.assertEquals(searchPage.getSearchResults().size(), 1);
        Assert.assertTrue(resultExists(searchPage, "Docker"));
    }

    @Test
    public void directSearchForInactiveItemReturnsInactiveResultTest() {
        SearchPage searchPage = openSearchPage();

        searchPage.searchForText("docker");
        searchPage.clickSearchButton();

        Assert.assertEquals(searchPage.getResultsCount(), 1);
        Assert.assertTrue(resultExists(searchPage, "Docker"));
        Assert.assertTrue(resultExists(searchPage, "Inactive"));
    }

    @Test
    public void resultRowContainsIdNameCategoryAndStatusTest() {
        SearchPage searchPage = openSearchPage();

        searchPage.searchForText("maven");
        searchPage.clickSearchButton();

        Assert.assertEquals(searchPage.getSearchResults().size(), 1);
        Assert.assertEquals(searchPage.getSearchResults().get(0), "5 Maven Build Tool Active");
    }

    @Test
    public void clearButtonResetsSearchFiltersAndResultsTest() {
        SearchPage searchPage = openSearchPage();

        searchPage.searchForText("Python");
        searchPage.selectCategory("Testing");
        searchPage.selectStatus("Inactive");
        searchPage.clickSearchButton();
        searchPage.clickClearButton();

        Assert.assertEquals(searchPage.getSearchTerm(), "");
        Assert.assertEquals(searchPage.getCategoryOption(), "All");
        Assert.assertEquals(searchPage.getStatusOption(), "All");
        Assert.assertEquals(searchPage.getResultsCount(), 8);
        Assert.assertEquals(searchPage.getSearchResults().size(), 8);
    }

    private SearchPage openSearchPage() {
        return loginPage.login("admin", "admin123").clickSearchPageButton();
    }

    private boolean resultExists(SearchPage searchPage, String expectedText) {
        return searchPage.getSearchResults()
                .stream()
                .anyMatch(result -> result.contains(expectedText));
    }
}
