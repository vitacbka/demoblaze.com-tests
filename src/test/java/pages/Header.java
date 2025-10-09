package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Header {

    private final SelenideElement
           // Navigation elements locators
            homeLink = $("a#nava"),
            contactLink = $x("//a[text()='Contact']"),
            aboutUsLink = $x("//a[text()='About us']"),
            cartLink = $x("//a[text()='Cart']"),
            loginLink = $x("//a[text()='Log in']"),
            signUpLink = $x("//a[text()='Sign up']"),

            //Modal windows locators
            contactModal = $("#exampleModal"),
            aboutUsModal = $("#videoModal"),
            loginModal = $("#logInModal"),
            signUpModal = $("#signInModal"),

            //Modal windows title locators
            contactModalTitle = contactModal.$(".modal-title"),
            aboutUsModalTitle = aboutUsModal.$(".modal-title"),
            loginModalTitle = loginModal.$(".modal-title"),
            signUpModalTitle = signUpModal.$(".modal-title"),

            //Video in about us modal
            aboutUsVideo = aboutUsModal.$("#example-video");

    public Header openContactModal() {
        contactLink.shouldBe(visible).click();
        contactModal.shouldBe(visible);
        return this;
    }

    public Header openAboutUsModal() {
        aboutUsLink.shouldBe(visible).click();
        aboutUsModal.shouldBe(visible);
        return this;
    }

    public Header openLoginModal() {
        loginLink.shouldBe(visible).click();
        loginModal.shouldBe(visible);
        return this;
    }

    public Header openSignUpModal() {
        signUpLink.shouldBe(visible).click();
        signUpModal.shouldBe(visible);
        return this;
    }

    public void goToCartPage() {
        cartLink.shouldBe(visible).click();
    }

    public void goToHomePage() {
        homeLink.shouldBe(visible).click();
    }

    public String getContactModalTitleText() {
        return contactModalTitle.shouldBe(visible).text();
    }

    public String getAboutUsModalTitleText() {
        return aboutUsModalTitle.shouldBe(visible).text();
    }

    public String getLoginModalTitleText() {
        return loginModalTitle.shouldBe(visible).text();
    }

    public String getSignUpModalTitleText() {
        return signUpModalTitle.shouldBe(visible).text();
    }

    public boolean isAboutUsVideoPresent() {
        return aboutUsVideo.isDisplayed();
    }
}