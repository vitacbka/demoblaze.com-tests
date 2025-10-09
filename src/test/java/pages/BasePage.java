package pages;

import com.codeborne.selenide.Selenide;

public abstract class BasePage {

    protected void openPage(String url) {
        Selenide.open(url);
    }
}
