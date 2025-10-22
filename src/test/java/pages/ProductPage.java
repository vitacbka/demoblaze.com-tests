package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ProductPage {

    private final SelenideElement
            productPageTitle = $("h2.name"),
            addToCartButton = $("a.btn-success");


    public String getItemPageTitle() {
        return productPageTitle.shouldBe(visible).text();
    }

    public void clickAddToCart() {
        addToCartButton.shouldBe(visible).shouldBe(clickable).click();
    }
}