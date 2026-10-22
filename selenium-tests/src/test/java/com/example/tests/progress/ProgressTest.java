package com.example.tests.progress;

import com.example.base.BaseTest;
import com.example.pages.ProgressPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProgressTest extends BaseTest {

    @Test
    public void progressPageLoadedTest() {
        ProgressPage progressPage = openProgressPage();

        Assert.assertEquals(progressPage.getProgressPageHeader(), "Progress");
        Assert.assertEquals(progressPage.getProgressValue(2), "0%");
        Assert.assertEquals(progressPage.getStartButtonTextBeforeClick(), "Start");
    }

    @Test
    public void partialProgressOnProgressBarTest() {
        ProgressPage progressPage = openProgressPage();
        progressPage.clickStartButton();

        Assert.assertTrue(progressPage.waitForStartButtonTextToContain(5, "In progress")
                .contains("In progress"));
        String progress = progressPage.waitForIntermediateProgress(10);
        assertIntermediatePercentage(progress);
    }

    @Test
    public void fullProgressOnProgressBarTest() {
        ProgressPage progressPage = openProgressPage();
        progressPage.clickStartButton();

        Assert.assertEquals(progressPage.waitForProgressToComplete(120), "100%");
        Assert.assertEquals(progressPage.getStartButtonTextAt100Percent(), "Start again");
    }

    @Test
    public void multipleUseOfProgressBarTest() {
        ProgressPage progressPage = openProgressPage();
        progressPage.clickStartButton();

        Assert.assertEquals(progressPage.waitForProgressToComplete(120), "100%");
        Assert.assertEquals(progressPage.getStartButtonTextAt100Percent(), "Start again");

        progressPage.clickStartButton();
        Assert.assertTrue(progressPage.waitForStartButtonTextToContain(5, "In progress")
                .contains("In progress"));
        assertIntermediatePercentage(progressPage.waitForIntermediateProgress(10));
        Assert.assertEquals(progressPage.waitForProgressToComplete(120), "100%");
        Assert.assertEquals(progressPage.getStartButtonTextAt100Percent(), "Start again");
    }

    private ProgressPage openProgressPage() {
        return loginPage.login("admin", "admin123").clickProgressPageButton();
    }

    private void assertIntermediatePercentage(String progress) {
        int percentage = Integer.parseInt(progress.replace("%", ""));
        Assert.assertTrue(percentage > 0 && percentage < 100,
                "Expected progress between 0% and 100%, but was " + progress);
    }

}


