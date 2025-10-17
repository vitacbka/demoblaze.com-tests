package test;

import org.junit.jupiter.api.*;
import pages.Header;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Header tests")
public class HeaderTest extends BaseTest {

    private Header header;

    @BeforeEach
    void setUp() {
        header = new Header();
        open("https://www.demoblaze.com");
    }

    @Test
    @DisplayName("Clicking 'Contact' should open modal with correct title")
    void shouldOpenContactModalWithTitleTest() {
        header.openContactModal();

        assertThat(header.getContactModalTitleText())
                .as("Contact modal title text is not as expected")
                .isEqualTo("New message");
    }

    @Test
    @DisplayName("Clicking 'About us' should open modal with video and correct title")
    void shouldOpenAboutUsModalWithVideoAndTitleTest() {
        header.openAboutUsModal();

        assertAll(
                () -> assertThat(header.getAboutUsModalTitleText())
                        .as("About us modal title text is not as expected")
                        .isEqualTo("About us"),

                () -> header.aboutUsVideo().shouldBe(visible),

                () -> assertThat(header.aboutUsVideo().getAttribute("poster"))
                        .as("Video poster is missing")
                        .isEqualTo("imgs/front.jpg"),

                () -> header.aboutUsVideo().shouldBe(visible)
                );
    }

    @Test
    @DisplayName("Clicking 'Log in' should open login modal with correct title")
    void shouldOpenLoginModalWithTitleTest() {
        header.openLoginModal();

        assertThat(header.getLoginModalTitleText())
                .as("Login modal title text is not as expected")
                .isEqualTo("Log in");
    }

    @Test
    @DisplayName("Clicking 'Sign up' should open signup modal with correct title")
    void shouldOpenSignUpModalWithTitleTest() {
        header.openSignUpModal();

        assertThat(header.getSignUpModalTitleText())
                .as("Sign up modal title text is not as expected")
                .isEqualTo("Sign up");
    }

    @Test
    @DisplayName("Clicking 'Cart' should navigate to cart page")
    void shouldNavigateToCartPageTest() {
        header.goToCartPage();

        assertThat("https://www.demoblaze.com/cart.html")
                .as("Cart page url is not as expected")
                .isEqualTo(url());
    }

    @Test
    @DisplayName("Clicking 'Home' should reload main page")
    void shouldReturnToHomePageTest() {
        header.goToCartPage();
        header.goToHomePage();

        assertThat("https://www.demoblaze.com/index.html")
                .as("Home page url is not as expected")
                .isEqualTo(url());
    }
}