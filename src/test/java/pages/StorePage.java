package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.TestData;

import java.time.Duration;

public class StorePage {
    private WebDriver driver;
    private WebDriverWait wait;

    public StorePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void addProductToCart(String productName, TestData.Product.Options options) {
        // Buscar el producto y hacer clic para abrir la página de detalles
        WebElement product = driver.findElement(By.xpath("//a[text()='" + productName + "']"));
        product.click();

        // Seleccionar opciones adicionales si existen
        if (options != null && options.color != null) {
            WebElement colorDropdown = driver.findElement(By.id("input-option226"));
            colorDropdown.sendKeys(options.color);
        }

        // Agregar el producto al carrito
        wait.until(ExpectedConditions.elementToBeClickable(By.id("button-cart"))).click();

        // Navegar de vuelta a la página principal y verificar la presencia de un elemento de la página principal
        driver.navigate().back();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Your Store']")));
    }
}
