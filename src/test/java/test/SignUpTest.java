package test;

import helpers.AlertHelper;
import helpers.FakeDataGeneratorHelper;
import org.junit.jupiter.api.*;
import pages.Header;
import pages.MainPage;
import Modal.SignUpModal;
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
        FakeDataGeneratorHelper faker;

        @BeforeEach
        void setUp() {
        mainPage = new MainPage();
        header = new Header();
        signUpPage = new SignUpModal();
        alertHelper = new AlertHelper();
        faker = new FakeDataGeneratorHelper();
        mainPage.openMainPage();
        }

        @Test
        @DisplayName("User should be able to sign up")
        void userShouldBeAbleToSignUpTest() {
        header.openSignUpModal();

        String username = faker.generateRandomUsername();
        String password = faker.generateRandomPassword();

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

        @Test
        @DisplayName("Alert error should be present after click submit button without filling input fields")
        void alertErrorShouldBePresentAfterClickSubmitButtonWithoutFillingInputFieldsTest() {
        header.openSignUpModal();

        signUpPage.clickSubmitButton();
        assertAll(
                () -> assertTrue(alertHelper.isAlertPresent(), "Alert is not present"),
                () -> assertEquals(EXPECTED_ALERT_EMPTY_INPUT_FIELDS_MESSAGE, alertHelper.getAlertTextIfPresent(),
                        "Alert message is not as expected")
                );
        }

        @Test
        @DisplayName("Alert error should be present after click submit button with empty password field")
        void alertErrorShouldBePresentAfterClickSubmitButtonWithEmptyPasswordFieldTest() {
        header.openSignUpModal();

        String username = faker.generateRandomUsername();

        signUpPage
                .fillUsernameInputField(username)
                .fillPasswordInputField("")
                .clickSubmitButton();
                
        assertAll(
                () -> assertTrue(alertHelper.isAlertPresent(), "Alert is not present"),
                () -> assertEquals(EXPECTED_ALERT_EMPTY_INPUT_FIELDS_MESSAGE, alertHelper.getAlertTextIfPresent(),
                        "Alert message is not as expected")
                );
        }

        @Test
        @DisplayName("Alert error should be present after click submit button with empty username field")
        void alertErrorShouldBePresentAfterClickSubmitButtonWithEmptyUsernameFieldTest() {
        header.openSignUpModal();

        String password = faker.generateRandomPassword();

        signUpPage
                .fillUsernameInputField("")
                .fillPasswordInputField(password)
                .clickSubmitButton();

        assertAll(
                () -> assertThat(alertHelper.isAlertPresent()).isTrue(),
                () -> assertThat(alertHelper.getAlertTextIfPresent())
                        .as("Alert message is not as expected")
                        .isEqualTo(EXPECTED_ALERT_EMPTY_INPUT_FIELDS_MESSAGE)
                );
        }

        @Test
        @Tag("failed")
        @DisplayName("Short password error message should be displayed")
        void shortPasswordErrorMessageShouldBeDisplayedTest() {
        header.openSignUpModal();

        String username = faker.generateRandomUsername();
        String shortPassword = faker.generateRandomVeryShortPassword(2);

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
        header.openSignUpModal();

        String username = faker.generateRandomUsername();
        String tooLongPassword = faker.generateRandomLongPassword(30);

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
        @DisplayName("Already exist user error message should be displayed")
        void alreadyExistUserErrorMessageShouldBeDisplayedTest() {
            header.openSignUpModal();

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
}
