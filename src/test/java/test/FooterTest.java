package test;

import TestData.FooterTestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.Footer;
import pages.MainPage;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Footer tests")
public class FooterTest extends BaseTest {

    private Footer footer;

    @BeforeEach
    void setUp() {
        MainPage.openMainPage();
        footer = new Footer();
    }

    @Test
    @DisplayName("Footer should display correct contact info")
    void shouldDisplayCorrectFooterContent() {
        footer.scrollToFooter();

        assertEquals(FooterTestData.ADDRESS, footer.getAddress(),
                "Address in footer is not as expected");
        assertEquals(FooterTestData.PHONE, footer.getPhone(),
                "Phone in footer is not as expected");
        assertEquals(FooterTestData.EMAIL, footer.getEmail(),
                "Email in footer is not as expected");
    }

    @Test
    @DisplayName("About us info should be displayed")
    void shouldDisplayAboutUsInfo() {
        footer.scrollToFooter();
        assertEquals(FooterTestData.ABOUT_US_INFO, footer.getMainDescription(),
                "Text description in footer is not as expected");
    }
}