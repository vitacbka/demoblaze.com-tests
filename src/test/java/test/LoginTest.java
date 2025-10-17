package test;

import Modal.LoginModal;
import helpers.AlertHelper;
import helpers.FakeDataGeneratorHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.Header;
import pages.MainPage;
import static TestData.LoginAlertTestData.EXPECTED_ERROR_EMPTY_USERNAME_OR_PASSWORD_MESSAGE_MODAL;
import static TestData.LoginAlertTestData.EXPECTED_ERROR_WRONG_PASSWORD_MESSAGE_MODAL;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static config.TestConfig.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest extends BaseTest {

    MainPage mainPage;
    Header header;
    LoginModal loginModal;
    AlertHelper alertHelper;
    FakeDataGeneratorHelper faker;

    @BeforeEach
    void setUp() {
        mainPage = new MainPage();
        header = new Header();
        loginModal = new LoginModal();
        alertHelper = new AlertHelper();
        faker = new FakeDataGeneratorHelper();
        mainPage.openMainPage();
    }

    @Test
    @DisplayName("User should be successfully logged in with valid credentials")
    void userShouldBeSuccessfullyLoggedInWithValidCredentialsTest() {
        header.openLoginModal();

        String username = getValidUsername();
        String password = getValidPassword();

        loginModal.login(username, password)
                .clickLoginButton();

        header.getWelcomeMessage()
                .shouldBe(visible)
                .shouldHave(text("Welcome validUser"));
    }

    @Test
    @DisplayName("Error alert should be displayed when user enters valid username and invalid password")
    void loginWithInvalidPasswordTest() {
        header.openLoginModal();

        String username = getBaseUrl();

        loginModal
                .fillUsernameInputField(username)
                .fillPasswordInputField("123");

        loginModal.clickLoginButton();

        assertAll(
                () -> assertThat(alertHelper.isAlertPresent())
                        .as("Alert is not present")
                        .isTrue(),
                () -> assertThat(alertHelper.getAlertTextIfPresent())
                        .as("Alert message is not as expected")
                        .isEqualTo(EXPECTED_ERROR_WRONG_PASSWORD_MESSAGE_MODAL),

                () -> header.getWelcomeMessage().shouldNotBe(visible)
        );
    }

    @Test
    @DisplayName("Error alert message should be displayed when user input valid username and empty password")
    void loginWithEmptyPasswordTest() {
        header.openLoginModal();

        String username = getBaseUrl();

        loginModal
                .fillUsernameInputField(username)
                .fillPasswordInputField("");

        loginModal.clickLoginButton();
        assertAll(
                () -> assertThat(alertHelper.isAlertPresent())
                        .as("Alert is not present")
                        .isTrue(),
                () -> assertThat(alertHelper.getAlertTextIfPresent())
                        .as("Alert message is not as expected")
                        .isEqualTo(EXPECTED_ERROR_EMPTY_USERNAME_OR_PASSWORD_MESSAGE_MODAL),
                () -> header.getWelcomeMessage().shouldNotBe(visible)
        );
    }

    @Test
    @DisplayName("Error alert message should be displayed when user input empty username and valid password")
    void loginWithEmptyUsernameTest() {
        header.openLoginModal();

        String password = getValidPassword();

        loginModal
                .fillUsernameInputField("")
                .fillPasswordInputField(password);

        loginModal.clickLoginButton();
        assertAll(
                () -> assertThat(alertHelper.isAlertPresent())
                        .as("Alert is not present")
                        .isTrue(),
                () -> assertThat(alertHelper.getAlertTextIfPresent())
                        .as("Alert message is not as expected")
                        .isEqualTo(EXPECTED_ERROR_EMPTY_USERNAME_OR_PASSWORD_MESSAGE_MODAL),
                () -> header.getWelcomeMessage().shouldNotBe(visible)
        );
    }
}
