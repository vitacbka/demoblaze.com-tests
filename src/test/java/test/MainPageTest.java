package test;

import helpers.ImageCheckHelper;
import helpers.PriceValidationHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.MainPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Main page tests")
public class MainPageTest extends BaseTest {
    private MainPage mainPage;
    private PriceValidationHelper priceValidationHelper;

    @BeforeEach
    void setUp() {
        mainPage = new MainPage();
        priceValidationHelper = new PriceValidationHelper();
        mainPage.openMainPage();
    }

    @Test
    @DisplayName("All images should be loaded at main page")
    void allImagesLoadedAtMainPageTest() {
        ImageCheckHelper.assertAllProductImagesAreLoaded();
    }

    @Test
    @DisplayName("Items price should be displayed")
    void itemsPriceDisplayedWithValidValueTest() {
        assertTrue(priceValidationHelper.checkPrices(), "One or more prices are not valid");
    }

    @Test
    @DisplayName("Product cards should be displayed")
    void productCardsDisplayedTest() {
        assertTrue(mainPage.isProductCardsVisible(), "Product cards are not displayed");
    }
}