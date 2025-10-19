package Modal;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginModal {
    private final SelenideElement
            loginModal = $("#logInModal"),
            usernameInput = loginModal.$("#loginusername"),
            passwordInput = loginModal.$("#loginpassword"),
            loginButton = loginModal.$("button[onclick='logIn()']");

    public LoginModal fillUsernameInputField(String username) {
        usernameInput.shouldBe(visible).clear();
        usernameInput.shouldBe(visible).setValue(username);
        return this;
    }

    public LoginModal fillPasswordInputField(String password) {
        passwordInput.shouldBe(visible).clear();
        passwordInput.shouldBe(visible).setValue(password);
        return this;
    }

    public LoginModal clickLoginButton() {
        loginButton.shouldBe(visible).click();
        return this;
    }

    public LoginModal login(String username, String password) {
        fillUsernameInputField(username).fillPasswordInputField(password);
        return this;
    }

    public LoginModal loginNegative() {
        fillUsernameInputField("invalidUser").fillPasswordInputField("invalidPassword");
        return this;
    }
}
