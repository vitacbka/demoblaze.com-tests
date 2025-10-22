package Modal;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class SignUpModal {

    private final SelenideElement
            signUpModal = $("#signInModal"),
            usernameInput = signUpModal.$("#sign-username"),
            passwordInput = signUpModal.$("#sign-password"),
            signUpButton = signUpModal.$("button[onclick='register()']");

    public SignUpModal fillUsernameInputField(String username) {
        usernameInput.shouldBe(visible).clear();
        usernameInput.shouldBe(visible).setValue(username);
        return this;
    }

    public SignUpModal fillPasswordInputField(String password) {
        passwordInput.shouldBe(visible).clear();
        passwordInput.shouldBe(visible).setValue(password);
        return this;
    }

    public SignUpModal clickSubmitButton() {
        signUpButton.shouldBe(visible).click();
        return this;
    }
}
