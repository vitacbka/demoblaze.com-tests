package helpers;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$$;

public class PriceValidationHelper {

    // All product prices locator
    private static final ElementsCollection productPrices = $$("#tbodyid h5");

    public boolean checkPrices() {
        boolean allPricesValid = true;
        for (SelenideElement price : productPrices) {
            if (!price.isDisplayed() || price.getText().isEmpty() || price.getText().startsWith("-")) {
                allPricesValid = false;
                break;
            }
        }
        return allPricesValid;
    }
}
