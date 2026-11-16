package com.example.tests.frames;

import com.example.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FramesTest extends BaseTest {

    @Test
    public void eachFrameDisplaysTextTest() {
        var formPage = loginPage.login("admin", "admin123");
        var framesPage = formPage.clickFramesPageButton();

        Assert.assertEquals(framesPage.getTextInMainFrame(),
                "This text is in the original page, outside the child frames.",
                "Main frame should display the expected text");
        Assert.assertEquals(framesPage.getTextInFrameOne(), "Text from Frame One",
                "First iframe should display the expected text");
        Assert.assertEquals(framesPage.getTextInFrameTwo(), "Text from Frame Two",
                "Second iframe should display the expected text");
    }

}
