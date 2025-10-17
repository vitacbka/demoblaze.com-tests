package pages;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static config.TestConfig.getBaseUrl;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPage {

    private final ElementsCollection productCards = $$("#tbodyid > .col-lg-4");

    public MainPage openMainPage() {
        String URL = getBaseUrl();
        open(URL);
        return this;
    }

    public void clickCategory(String categoryName) {
        $(String.format("a:contains('%s')", categoryName)).shouldBe(visible).click();
        assertTrue($$("#tbodyid .card").size() > 0, "No products found after category click");
    }

    public boolean isProductCardsVisible() {
        $$(".card-img-top").shouldHave(sizeGreaterThanOrEqual(1));
        return true;
    }

    public String getProductName(int index) {
        return productCards.get(index)
                .$(".card-title .hrefch")
                .shouldBe(visible)
                .text();
    }

    public void clickProduct(int indexOfProduct) {
        productCards.get(indexOfProduct)
                .$(".card-title .hrefch")
                .shouldBe(visible)
                .click();
    }
}