package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.TestData;

import java.time.Duration;

public class CheckoutPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void completeBillingDetails(TestData.BillingDetails billingDetails) {
        WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-payment-firstname")));
        firstNameField.sendKeys(billingDetails.firstName);

        driver.findElement(By.id("input-payment-lastname")).sendKeys(billingDetails.lastName);
        driver.findElement(By.id("input-payment-address-1")).sendKeys(billingDetails.address);
        driver.findElement(By.id("input-payment-city")).sendKeys(billingDetails.city);
        driver.findElement(By.id("input-payment-postcode")).sendKeys(billingDetails.postcode);

        Select countrySelect = new Select(driver.findElement(By.id("input-payment-country")));
        countrySelect.selectByVisibleText(billingDetails.country);

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("input-payment-zone"), billingDetails.region));
        Select zoneSelect = new Select(driver.findElement(By.id("input-payment-zone")));
        zoneSelect.selectByVisibleText(billingDetails.region);

        driver.findElement(By.id("button-payment-address")).click();
    }

    public void completeShippingDetails(String comment) {
        WebElement continueShippingButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("button-shipping-address")));
        continueShippingButton.click();

        WebElement commentField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("comment")));
        commentField.sendKeys(comment);

        WebElement continueShippingMethodButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("button-shipping-method")));
        continueShippingMethodButton.click();
    }

    public void agreeToTermsAndContinue() {
        WebElement agreeCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.name("agree")));
        agreeCheckbox.click();

        WebElement continuePaymentMethodButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("button-payment-method")));
        continuePaymentMethodButton.click();
    }

    public void confirmOrder() {
        WebElement confirmOrderButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("button-confirm")));
        confirmOrderButton.click();
    }

    public void clickContinueAfterConfirmation() {
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Continue")));
        continueButton.click();
    }

    public void goToOrderHistory() {
        WebElement myAccountMenu = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("My Account")));
        myAccountMenu.click();

        WebElement orderHistoryOption = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Order History")));
        orderHistoryOption.click();
    }

    public void viewOrderDetails() {
        WebElement viewOrderIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.btn-info")));
        viewOrderIcon.click();
    }
}
