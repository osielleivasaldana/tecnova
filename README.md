
# PRUEBA DE AUTOMATIZACION DE FLUJO END TO END DE UNA TIENDA E-COMMERCE UTILIZANDO SELNIUM Y ALLURE BAJO EL PATRON DE DISENO POM (Page Object Model)

### Descripción del Proyecto

Este proyecto es una automatización de pruebas end-to-end para un sitio web de e-commerce utilizando Selenium con Java y el patrón de diseño **Page Object Model (POM)**. El flujo de pruebas incluye desde el inicio de sesión hasta la compra de productos, finalizando con la validación del historial de órdenes. Las pruebas están organizadas con **TestNG** y los reportes detallados se generan con **Allure**, proporcionando una solución integral para asegurar la calidad del sitio web.



### Tecnologías Utilizadas

- **Selenium WebDriver**: Automatización de navegadores para la ejecución de pruebas.
- **Java**: Lenguaje de programación principal.
- **TestNG**: Framework de pruebas utilizado para gestionar las pruebas y sus dependencias.
- **Allure**: Herramienta para la generación de reportes detallados y comprensibles.
- **Maven**: Herramienta de construcción y gestión de dependencias.
- **WebDriverManager**: Gestor automático del driver del navegador.

### Estructura del Proyecto


- `src/test/java/pages/` — Contiene clases que representan cada página del sitio web, encapsulando la lógica de interacción con sus elementos.
  - **LoginPage.java**: Clase para manejar las acciones en la página de inicio de sesión.
  - **StorePage.java**: Clase para manejar las acciones en la página principal de la tienda.
  - **CartPage.java**: Clase para manejar las acciones en la página del carrito de compras.
  - **CheckoutPage.java**: Clase para manejar las acciones en la página de checkout.
- `src/test/java/tests/` — Contiene las clases de prueba que coordinan el flujo completo de compra.
  - **PurchaseFlowTest.java**: Clase principal que ejecuta el flujo completo, interactuando con las diferentes páginas del sitio.
  - **LoginTest.java**: Prueba que valida el comportamiento del inicio de sesión con credenciales incorrectas.
- `src/test/resources/` — Contiene archivos de datos para las pruebas, como `testData.json` que almacena credenciales y datos de facturación.


### Configuración y Ejecución

1. **Clonar el repositorio**:
   ```sh
   git clone https://github.com/osielleivasaldana/tecnova.git
   ```


2. **Instalar las dependencias: Utiliza Maven para descargar las dependencias necesarias ejecutando:**:
   
    ```sh
    mvn clean install
    ```


3. **Ejecutar las pruebas: Para ejecutar las pruebas utilizando TestNG, puedes hacerlo desde tu IDE (IntelliJ, Eclipse) o desde la línea de comandos:**:


    ```sh
    mvn test
    ```

    
4. **Generar y ver reportes de Allure**:

   Generar reporte
     ```sh
    allure generate target/allure-results -o allure-report --clean
     ```

    Ver el reporte
     ```sh
    allure serve target/allure-results
     ```


### Estructura del Patrón POM

El patrón Page Object Model (POM) mejora la mantenibilidad y la reutilización del código al representar cada página del sitio web como una clase separada. Cada clase contiene la lógica para interactuar con los elementos de esa página, permitiendo que los scripts de prueba sean más claros y fáciles de mantener.
     
