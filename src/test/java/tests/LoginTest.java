package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @Test(description = "Prueba de login con credenciales incorrectas")
    @Description("Intenta iniciar sesión con credenciales incorrectas y verifica el mensaje de error.")
    public void invalidLoginTest() {
        navigateToLoginPage();
        enterInvalidCredentials();
        verifyErrorMessage();
    }

    @Step("Navegar a la página de login")
    public void navigateToLoginPage() {
        driver.get("https://opencart.abstracta.us/index.php?route=account/login");
    }

    @Step("Ingresar credenciales incorrectas y hacer clic en el botón de login")
    public void enterInvalidCredentials() {
        WebElement emailField = driver.findElement(By.id("input-email"));
        WebElement passwordField = driver.findElement(By.id("input-password"));
        WebElement loginButton = driver.findElement(By.cssSelector("input[value='Login']"));

        emailField.sendKeys("incorrecto@mail.com");
        passwordField.sendKeys("wrongPassword");
        loginButton.click();
    }

    @Step("Verificar el mensaje de error para credenciales incorrectas")
    public void verifyErrorMessage() {
        WebElement errorMessage = driver.findElement(By.cssSelector(".alert-danger"));
        assertTrue(errorMessage.getText().contains("Warning: No match for E-Mail Address and/or Password."),
                "No se mostró el mensaje de error esperado para el login incorrecto.");
    }
}
