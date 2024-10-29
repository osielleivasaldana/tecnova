package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.StorePage;
import pages.CartPage;
import pages.CheckoutPage;
import tests.TestData;
import org.openqa.selenium.By;

import java.io.File;
import java.io.IOException;

public class PurchaseFlowTest extends BaseTest {
    private LoginPage loginPage;
    private StorePage storePage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private TestData testData;

    @BeforeClass
    public void setUpPages() {
        loginPage = new LoginPage(driver);
        storePage = new StorePage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);

        // Cargar datos de prueba desde el archivo JSON
        loadTestData();
    }

    public void loadTestData() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            testData = objectMapper.readValue(new File("src/test/resources/testData.json"), TestData.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("No se pudieron cargar los datos de prueba.");
        }
    }

    @Test(description = "Paso 1: Iniciar sesión con credenciales válidas")
    @Description("Inicia sesión en la aplicación con credenciales válidas.")
    public void step1_login() {
        driver.get("https://opencart.abstracta.us/index.php?route=account/login");
        loginPage.login(testData.credentials.email, testData.credentials.password);
    }

    @Test(description = "Paso 2: Redirigir a 'Your Store'", dependsOnMethods = "step1_login")
    @Description("Redirige al usuario a la página 'Your Store'.")
    public void step2_redirectToYourStore() {
        driver.findElement(By.linkText("Your Store")).click();
    }

    @Test(description = "Paso 3: Seleccionar productos de la tienda", dependsOnMethods = "step2_redirectToYourStore")
    @Description("Selecciona productos de la tienda, incluyendo uno con opciones adicionales.")
    public void step3_selectProducts() {
        for (TestData.Product product : testData.products) {
            storePage.addProductToCart(product.name, product.options);
        }
    }

    @Test(description = "Paso 4: Eliminar un producto del carrito", dependsOnMethods = "step3_selectProducts")
    @Description("Accede al carrito de compras y elimina el primer producto de la lista.")
    public void step4_removeFirstProductFromCart() {
        driver.get("https://opencart.abstracta.us/index.php?route=checkout/cart");
        cartPage.removeFirstProductFromCart();
    }

    @Test(description = "Paso 5: Proceder al checkout", dependsOnMethods = "step4_removeFirstProductFromCart")
    @Description("Procede al proceso de pago desde el carrito.")
    public void step5_proceedToCheckout() {
        driver.findElement(By.linkText("Checkout")).click();
    }

    @Test(description = "Paso 6: Completar detalles de facturación", dependsOnMethods = "step5_proceedToCheckout")
    @Description("Completa los detalles de facturación requeridos durante el proceso de compra.")
    public void step6_completeBillingDetails() {
        checkoutPage.completeBillingDetails(testData.billingDetails);
    }

    @Test(description = "Paso 7: Completar detalles de despacho", dependsOnMethods = "step6_completeBillingDetails")
    @Description("Completa los detalles de despacho y añade un comentario antes de continuar.")
    public void step7_completeShippingDetails() {
        checkoutPage.completeShippingDetails(testData.comments);
    }

    @Test(description = "Paso 8: Aceptar términos y condiciones", dependsOnMethods = "step7_completeShippingDetails")
    @Description("Acepta los términos y condiciones y continúa con el proceso de pago.")
    public void step8_agreeToTermsAndContinue() {
        checkoutPage.agreeToTermsAndContinue();
    }

    @Test(description = "Paso 9: Confirmar la orden", dependsOnMethods = "step8_agreeToTermsAndContinue")
    @Description("Confirma la orden y finaliza la compra.")
    public void step9_confirmOrder() {
        checkoutPage.confirmOrder();
    }

    @Test(description = "Paso 10: Hacer clic en 'Continue' después de confirmar la orden", dependsOnMethods = "step9_confirmOrder")
    @Description("Hace clic en el botón 'Continue' después de la confirmación de la compra.")
    public void step10_clickContinueAfterConfirmation() {
        checkoutPage.clickContinueAfterConfirmation();
    }

    @Test(description = "Paso 11: Validar la orden en el historial de órdenes", dependsOnMethods = "step10_clickContinueAfterConfirmation")
    @Description("Accede al historial de órdenes para validar que la compra se haya registrado correctamente.")
    public void step11_validateOrderInOrderHistory() {
        checkoutPage.goToOrderHistory();
    }

    @Test(description = "Paso 12: Ver los detalles de la última orden realizada", dependsOnMethods = "step11_validateOrderInOrderHistory")
    @Description("Presiona sobre el icono de 'ojo' para ver los detalles de la última orden realizada.")
    public void step12_viewOrderDetails() {
        checkoutPage.viewOrderDetails();
    }
}
