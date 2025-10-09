package test;

import org.junit.jupiter.api.*;
import pages.Header;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Header tests")
public class HeaderTest extends BaseTest {

    private Header header;

    @BeforeEach
    void setUp() {
        open("https://www.demoblaze.com");
        header = new Header();
    }

    @Test
    @DisplayName("Clicking 'Contact' should open modal with correct title")
    void shouldOpenContactModalWithTitle() {
        header.openContactModal();
        assertEquals("New message", header.getContactModalTitleText());
    }

    @Test
    @DisplayName("Clicking 'About us' should open modal with video and correct title")
    void shouldOpenAboutUsModalWithVideoAndTitle() {
        header.openAboutUsModal();
        assertEquals("About us", header.getAboutUsModalTitleText());
        assertTrue(header.isAboutUsVideoPresent(), "Видео в модальном окне 'About us' не отображается");
    }

    @Test
    @DisplayName("Clicking 'Log in' should open login modal with correct title")
    void shouldOpenLoginModalWithTitle() {
        header.openLoginModal();
        assertEquals("Log in", header.getLoginModalTitleText());
    }

    @Test
    @DisplayName("Clicking 'Sign up' should open signup modal with correct title")
    void shouldOpenSignUpModalWithTitle() {
        header.openSignUpModal();
        assertEquals("Sign up", header.getSignUpModalTitleText());
    }

    @Test
    @DisplayName("Clicking 'Cart' should navigate to cart page")
    void shouldNavigateToCartPage() {
        header.goToCartPage();
        assertEquals("https://www.demoblaze.com/cart.html", url());
    }

    @Test
    @DisplayName("Clicking 'Home' should reload main page")
    void shouldReturnToHomePage() {
        header.goToCartPage();
        header.goToHomePage();
        assertEquals("https://www.demoblaze.com/index.html", url());
    }
}