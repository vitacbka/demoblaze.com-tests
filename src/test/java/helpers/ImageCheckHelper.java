package helpers;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ImageCheckHelper {

    private static final ElementsCollection images = $$(".card-img-top");

    public static void assertImageIsLoadedAndVisible(SelenideElement image) {
        image.should(exist)
                .should(be(visible))
                .should(attribute("src"));

        Boolean isLoaded = executeJavaScript(
                "return arguments[0].complete && arguments[0].naturalWidth > 0;",
                image
        );

        assertTrue(isLoaded, "Изображение не загружено (битое или lazy-loading не сработал)");
    }

    public static void assertAllProductImagesAreLoaded() {
        $$(".card-img-top").forEach(ImageCheckHelper::assertImageIsLoadedAndVisible);
    }
}