package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;

public class BaseTest {
    protected WebDriver driver;
    protected TestData testData;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loadTestData();
    }

    public void loadTestData() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            testData = mapper.readValue(new File("src/test/resources/testData.json"), TestData.class);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al cargar los datos de prueba desde testData.json");
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
         //   driver.quit();
        }
    }
}
