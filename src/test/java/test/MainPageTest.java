package test;

import helpers.ImageCheckHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.MainPage;

@DisplayName("Main page tests")
public class MainPageTest extends BaseTest {
    private MainPage mainPage;

    @BeforeEach
    void setUp() {
        mainPage = new MainPage();
        MainPage.openMainPage();
    }

    @Test
    @DisplayName("All images should be loaded at main page")
    void allImagesLoadedAtMainPage() {
        ImageCheckHelper.assertAllProductImagesAreLoaded();
    }
}