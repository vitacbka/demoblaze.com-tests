package helpers;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AlertHelper {

    public String getAlertTextIfPresent() {
        WebDriverWait wait = new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(7));
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = WebDriverRunner.getWebDriver().switchTo().alert();
            return alert.getText();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean isAlertPresent() {
        WebDriverWait wait = new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(2));
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void acceptAlert() {
        WebDriverWait wait = new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = WebDriverRunner.getWebDriver().switchTo().alert();
        alert.accept();
    }
}
