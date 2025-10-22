package test;

import Modal.SignUpModal;
import com.github.javafaker.Faker;
import helpers.AlertHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pages.Header;
import pages.MainPage;

import java.util.stream.Stream;

import static TestData.SignUpAlertTestData.*;
import static config.TestConfig.getValidPassword;
import static config.TestConfig.getValidUsername;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class SignUpTest extends BaseTest {
    MainPage mainPage;
    Header header;
    SignUpModal signUpPage;
    AlertHelper alertHelper;
    Faker faker;

    @BeforeEach
    void setUp() {
        mainPage = new MainPage();
        header = new Header();
        signUpPage = new SignUpModal();
        alertHelper = new AlertHelper();
        faker = new Faker();
        mainPage.openMainPage();
        header.openSignUpModal();
    }

    @Test
    @DisplayName("User should be able to sign up")
    void userShouldBeAbleToSignUpTest() {
        String username = faker.name().username();
        String password = faker.internet().password(8, 16);

        signUpPage
                .fillUsernameInputField(username)
                .fillPasswordInputField(password)
                .clickSubmitButton();

        assertAll(
                () -> assertTrue(alertHelper.isAlertPresent(), "Alert is not present"),
                () -> assertEquals(EXPECTED_ALERT_SUCCESSFUL_SIGNUP_MESSAGE, alertHelper.getAlertTextIfPresent(),
                        "Alert message is not as expected")
        );
    }

    private static Stream<Arguments> invalidSignUpDataProvider() {
        Faker faker = new Faker();
        return Stream.of(
                Arguments.of("empty username and valid password", "", faker.internet().password(8, 16)),
                Arguments.of("valid username and empty password", faker.name().username(), ""),
                Arguments.of("empty username and empty password", "", ""),
                Arguments.of("SQL injection in username", "admin ' OR '1'='1", faker.internet().password(8, 16)),
                Arguments.of("SQL injection in password", faker.name().username(), "12345678 ' OR '1'='1"),
                Arguments.of("script tag in username", "<script>alert(1)</script>", faker.internet().password(8, 16)),
                Arguments.of("script tag in password", faker.name().username(), "<script>alert(1)</script>"),
                Arguments.of("too long username", "a".repeat(100), faker.internet().password(8, 16)),
                Arguments.of("zero-width space in username", "\u200B" + faker.name().username(), faker.internet().password(8, 16)),
                Arguments.of("only spaces in username", "         ", faker.internet().password(8, 16)),
                Arguments.of("only spaces in password", faker.name().username(), "        "),
                Arguments.of("only spaces in both fields", "        ", "        ")
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidSignUpDataProvider")
    @DisplayName("User should not be able to sign up with ")
    void userShouldNotBeAbleSignUpWithInvalidDataTest(String description, String username, String password) {
        signUpPage.fillUsernameInputField(username)
                .fillPasswordInputField(password)
                .clickSubmitButton();

        assertAll(
                () -> assertThat(alertHelper.isAlertPresent())
                        .as("Alert is not present for: " + description)
                        .isTrue(),
                () -> assertThat(alertHelper.getAlertTextIfPresent())
                        .as("Alert message should not be empty for: " + description)
                        .isNotBlank()
        );
        System.out.println(alertHelper.getAlertTextIfPresent());

        alertHelper.acceptAlert();
    }

    @Test
    @Tag("failed")
    @DisplayName("Short password error message should be displayed")
    void shortPasswordErrorMessageShouldBeDisplayedTest() {
        String username = faker.name().username();
        String shortPassword = faker.internet().password(1, 2);

        signUpPage.fillUsernameInputField(username)
                .fillPasswordInputField(shortPassword)
                .clickSubmitButton();

        assertAll(
                () -> assertThat(alertHelper.isAlertPresent())
                        .as("Alert is not present")
                        .isTrue(),
                () -> assertThat(alertHelper.getAlertTextIfPresent())
                        .as("Alert message is not as expected")
                        .isEqualTo(EXPECTED_ALERT_TOO_SHORT_ERROR_MESSAGE)
        );
    }

    @Test
    @Tag("failed")
    @DisplayName("Too long password error message should be displayed")
    void tooLongPasswordErrorMessageShouldBeDisplayedTest() {
        String username = faker.name().username();
        String tooLongPassword = faker.internet().password(30, 50);

        signUpPage.fillUsernameInputField(username)
                .fillPasswordInputField(tooLongPassword)
                .clickSubmitButton();

        assertAll(
                () -> assertThat(alertHelper.isAlertPresent())
                        .as("Alert is not present")
                        .isTrue(),
                () -> assertThat(alertHelper.getAlertTextIfPresent())
                        .as("Alert message is not as expected")
                        .isEqualTo(EXPECTED_ALERT_TOO_LONG_ERROR_MESSAGE)
        );
    }

    @Test
    @Tag("failed")
    @DisplayName("Already exist user error message should be displayed")
    void alreadyExistUserErrorMessageShouldBeDisplayedTest() {
        String username = getValidUsername();
        String password = getValidPassword();

        signUpPage.fillUsernameInputField(username)
                .fillPasswordInputField(password)
                .clickSubmitButton();

        assertAll(
                () -> assertThat(alertHelper.isAlertPresent())
                        .as("Alert is not present")
                        .isTrue(),
                () -> assertThat(alertHelper.getAlertTextIfPresent())
                        .as("Alert message is not as expected")
                        .isEqualTo(EXPECTED_ALERT_ALREADY_EXIST_USER_ERROR_MESSAGE)
        );
    }

    @Test
    @DisplayName("Special characters in username should be handled correctly")
    void specialCharactersInUsernameShouldBeHandledCorrectlyTest() {
        String username = "user@name#123";
        String password = faker.internet().password(8, 16);

        signUpPage.fillUsernameInputField(username)
                .fillPasswordInputField(password)
                .clickSubmitButton();

        assertThat(alertHelper.isAlertPresent())
                .as("Alert should be present for special characters in username")
                .isTrue();

        alertHelper.acceptAlert();
    }

    @Test
    @DisplayName("Unicode characters in credentials should be handled correctly")
    void unicodeCharactersInCredentialsShouldBeHandledCorrectlyTest() {
        String username = "用户" + faker.name().username();
        String password = "пароль" + faker.internet().password(8, 16);

        signUpPage.fillUsernameInputField(username)
                .fillPasswordInputField(password)
                .clickSubmitButton();

        assertThat(alertHelper.isAlertPresent())
                .as("Alert should be present when testing unicode characters")
                .isTrue();

        alertHelper.acceptAlert();
    }
}