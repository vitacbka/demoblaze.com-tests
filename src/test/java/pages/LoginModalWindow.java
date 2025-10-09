package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoginModalWindow extends BasePage{
    private final SelenideElement
            loginUsernameInput = $("#loginusername") ,
            loginPasswordInput = $("#loginpassword"),
            loginButton = $("button[onclick='logIn()");
}
