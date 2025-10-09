package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Footer {

    // Footer Container
    private final SelenideElement footerContainer = $("#footc");

    // Footer texts block
    private final SelenideElement aboutUsParagraph = $$("#fotcont .col-sm-4 p").first();
    private final SelenideElement contactInfoBlock = $("#fotcont .col-sm-3");

    public Footer scrollToFooter() {
        footerContainer.should(exist).scrollTo();
        return this;
    }

    public String getMainDescription() {
        return aboutUsParagraph.shouldBe(visible).text();
    }

    public String getAddress() {
        return contactInfoBlock.$$("p").get(0).shouldBe(visible).text();
    }

    public String getPhone() {
        return contactInfoBlock.$$("p").get(1).shouldBe(visible).text();
    }

    public String getEmail() {
        return contactInfoBlock.$$("p").get(2).shouldBe(visible).text();
    }

}