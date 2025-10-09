package test;

import helpers.ImageCheckHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.MainPage;

public class MainPageTest extends BaseTest{

    @BeforeEach
    void setUp() {
        MainPage.openMainPage();
    }

    @Test
    @DisplayName("All images should be loaded at main page")
    void allImagesLoadedAtMainPage() {
        ImageCheckHelper.assertAllProductImagesAreLoaded();
    }
}