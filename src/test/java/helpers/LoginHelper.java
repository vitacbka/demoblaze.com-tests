package helpers;

import Modal.LoginModal;
import pages.Header;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static config.TestConfig.getValidPassword;
import static config.TestConfig.getValidUsername;

public class LoginHelper {

    public void loginWithValidCredentials() {
        LoginModal loginModal = new LoginModal();
        Header header = new Header();
        header.openLoginModal();

        String username = getValidUsername();
        String password = getValidPassword();

        loginModal.login(username, password)
                .clickLoginButton();

        header.getWelcomeMessage()
                .shouldBe(visible)
                .shouldHave(text("Welcome validUser"));
    }
}
