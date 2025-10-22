package test;

import TestData.FooterTestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.Footer;
import pages.MainPage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Footer tests")
public class FooterTest extends BaseTest {

    private Footer footer;
    private MainPage mainPage;

    @BeforeEach
    void setUp() {
        mainPage = new MainPage();
        mainPage.openMainPage();
        footer = new Footer();
    }

    @Test
    @DisplayName("Footer should display correct contact info")
    void shouldDisplayCorrectFooterContentTest() {
        footer.scrollToFooter();

        assertAll(
                () -> assertThat(FooterTestData.ADDRESS)
                        .as("Address in footer is not as expected")
                        .isEqualTo(footer.getAddress()),
                () -> assertThat(FooterTestData.PHONE)
                        .as("Phone in footer is not as expected")
                        .isEqualTo(footer.getPhone()),
                () -> assertThat(FooterTestData.EMAIL)
                        .as("Email in footer is not as expected")
                        .isEqualTo(footer.getEmail())
        );
    }

    @Test
    @DisplayName("About us info should be displayed")
    void shouldDisplayAboutUsInfoTest() {
        footer.scrollToFooter();

        assertThat(FooterTestData.ABOUT_US_INFO)
                .as("Text description in footer is not as expected")
                .isEqualTo(footer.getMainDescription());
    }
}