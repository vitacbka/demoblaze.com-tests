package test;

import Modal.LoginModal;
import helpers.AlertHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pages.Header;
import pages.MainPage;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static config.TestConfig.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class LoginTest extends BaseTest {

    MainPage mainPage;
    Header header;
    LoginModal loginModal;
    AlertHelper alertHelper;

    @BeforeEach
    void setUp() {
        mainPage = new MainPage();
        header = new Header();
        loginModal = new LoginModal();
        alertHelper = new AlertHelper();
        mainPage.openMainPage();
        header.openLoginModal();
    }

    @Test
    @DisplayName("User should be successfully logged in with valid credentials")
    void userShouldBeSuccessfullyLoggedInWithValidCredentialsTest() {
        String username = getValidUsername();
        String password = getValidPassword();

        loginModal.login(username, password)
                .clickLoginButton();

        header.getWelcomeMessage()
                .shouldBe(visible)
                .shouldHave(text("Welcome validUser"));
    }

    private static Stream<Arguments> invalidCredentialsForLoginProvider() {
        return Stream.of(
                Arguments.of("with valid username and empty password", getValidUsername(), ""),
                Arguments.of("with empty username and empty password", "", ""),
                Arguments.of("with invalid credentials", getInvalidUsername(), getInvalidPassword()),
                Arguments.of("with empty username and invalid password", "", getInvalidPassword()),
                Arguments.of("with SQL injection in username", "admin ' OR '1'='1", "12345678"),
                Arguments.of("with SQL injection in password", "admin", "12345678 ' OR '1'='1 "),
                Arguments.of("with script tag in password", getValidUsername(), "<script>alert(1)></script>"),
                Arguments.of("with too long username", "a".repeat(100), getValidPassword()),
                Arguments.of("with too long password", getValidUsername(), "b".repeat(100)),
                Arguments.of("with zero-width space in username", "\u200B" + getValidUsername(), getValidPassword()),
                Arguments.of("with only spaces in username and valdi password", "         ", getValidPassword()),
                Arguments.of("with only spaces in password and valid username", getValidUsername(), "        "),
                Arguments.of("with only spaces in username and password", "        ", "        ")
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidCredentialsForLoginProvider")
    @DisplayName("User should not be login in with ")
    void userShouldNotBeLoginWithInvalidCredentialsTest(String description, String username, String password) {
        loginModal
                .fillUsernameInputField(username)
                .fillPasswordInputField(password)
                .clickLoginButton();

        assertAll(
                () -> assertThat(alertHelper.isAlertPresent())
                        .as("Alert should be present for invalid credentials")
                        .isTrue(),
                () -> assertThat(alertHelper.getAlertTextIfPresent())
                        .as("Alert message should not be empty")
                        .isNotBlank()
        );

        System.out.println(alertHelper.getAlertTextIfPresent());
        alertHelper
                .acceptAlert();
        loginModal.closeLoginModalWindow();
        header.getWelcomeMessage().shouldNotBe(visible);
    }
}