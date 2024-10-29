package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void removeFirstProductFromCart() {
        // Contar el número inicial de filas de productos en el carrito
        int initialProductCount = driver.findElements(By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr")).size();

        // Clic en el botón de eliminar para el primer producto
        driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr[1]/td[4]/div/span/button[2]")).click();

        // Esperar hasta que el número de productos en el carrito disminuya
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr"), initialProductCount - 1));

        // Clic en el botón "Checkout" para proceder al checkout
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Checkout"))).click();
    }
}
