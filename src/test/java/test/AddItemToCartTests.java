package test;

import com.codeborne.selenide.Selenide;
import helpers.AlertHelper;
import helpers.LoginHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.Header;
import pages.MainPage;
import pages.ProductPage;

import static TestData.ProductAlertTestData.EXPECTED_PRODUCT_ADDED_MESSAGE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Add item ti cart, user should be logged in")
public class AddItemToCartTests extends BaseTest {
    MainPage mainPage;
    Header header;
    LoginHelper loginHelper;
    ProductPage productPage;

    @BeforeEach
    void setUp() {
        mainPage = new MainPage();
        header = new Header();
        loginHelper = new LoginHelper();
        productPage = new ProductPage();
        mainPage.openMainPage();
        loginHelper.loginWithValidCredentials();
    }


    @Test
    @DisplayName("Click on second item on main page should open item page")
    void itemPageTitleShouldBeEqualsItemNameTest() {
        String expectedSecondItemName = mainPage.getProductName(1).trim();
        mainPage.clickProduct(1);
        String actualItemPageTitle = productPage.getItemPageTitle().trim();

        assertAll(
                () -> assertThat(expectedSecondItemName)
                        .as("Item page title is not equal to item name")
                        .isEqualTo(actualItemPageTitle),
                () -> assertThat(Selenide.webdriver().driver().url())
                        .contains("prod.html?idp_=")
        );
    }

    @Test
    @DisplayName("Item should be added to cart")
    void itemShouldBeAddedToCartTest() {
        String expectedSecondItemName = mainPage.getProductName(1).trim();
        mainPage.clickProduct(1);
        String actualItemPageTitle = productPage.getItemPageTitle().trim();

        assertAll(
                () -> assertThat(expectedSecondItemName)
                        .as("Item page title is not equal to item name")
                        .isEqualTo(actualItemPageTitle),
                () -> assertThat(Selenide.webdriver().driver().url())
                        .contains("prod.html?idp_=")
        );

        productPage.clickAddToCart();

        AlertHelper alertHelper = new AlertHelper();
        String alertText = alertHelper.getAlertTextIfPresent();

        assertThat(alertText)
                .as("Alert text is not equal to item name")
                .isEqualTo(EXPECTED_PRODUCT_ADDED_MESSAGE);

        alertHelper.acceptAlert();

        assertThat(Selenide.webdriver().driver().url())
                .contains("prod.html?idp_=");
    }
}
