package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MainPage {

    private final SelenideElement
            mainPageTitle = $("#nava");

    public static void openMainPage() {
        open("https://www.demoblaze.com/index.html");
    }
}
