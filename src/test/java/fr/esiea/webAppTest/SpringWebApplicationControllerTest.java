package fr.esiea.webAppTest;

import fr.esiea.supermarket.webApp.SpringWebApplicationController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


class SpringWebApplicationControllerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringWebApplicationController.class);
   SpringWebApplicationController test = new SpringWebApplicationController();

   @BeforeEach
    void setUp() {
        test.index();
    }

    @AfterEach
    void tearDown() {
       test = null;
    }

    @Test
    void createProduct() {
        Assertions.assertNotNull(test.createProduct("Rasoir","4.00","each"));
    }

    @Test
    void index() {
        test.index();
    }

    @Test
    void deleteProduct() {
        Assertions.assertNotNull(test.createProduct("Rasoir","4.00","Each"));
        Assertions.assertNotNull(test.deleteProduct("Rasoir"));
    }

    @Test
    void listProduct() {
        Assertions.assertNotNull(test.createProduct("Book","6.00","Each"));
        Assertions.assertNotNull(test.createProduct("Rasoir","4.00","Each"));
        Assertions.assertNotNull(test.listProducts());
    }

    @Test
    void activationOffre() {
        Assertions.assertNotNull(test.createProduct("Book","6.00","Each"));
        Assertions.assertNotNull(test.createProduct("Rasoir","4.00","Each"));
        Assertions.assertNotNull(test.createProduct("Pen","7.00","Each"));
        Assertions.assertNotNull(test.activeOffre("Book","ThreeForTwo","2.0"));
        Assertions.assertNotNull(test.activeOffre("Pen","FiveForAmount","1.0"));
        Assertions.assertNotNull(test.activeOffre("Pen","TwoForAmount","3.0"));
        Assertions.assertNotNull(test.activeOffre("Pen","TenPercentDiscount","1.0"));
    }

    @Test
    void desactivationOffre() {
        Assertions.assertNotNull(test.createProduct("Book","6.00","Each"));
        Assertions.assertNotNull(test.createProduct("Rasoir","4.00","Each"));
        Assertions.assertNotNull(test.activeOffre("Book","ThreeForTwo","2.0"));
        Assertions.assertNotNull(test.deactivationOffre("Boock"));
    }

    @Test
    void createClient() {
        Assertions.assertNotNull(test.createClient("4"));
    }

    @Test
    void clientAddProduct() {
        Assertions.assertNotNull(test.createProduct("Book","6.00","Each"));
        Assertions.assertNotNull(test.createProduct("Rasoir","4.00","Each"));
        Assertions.assertNotNull(test.activeOffre("Book","ThreeForTwo","2.0"));
        Assertions.assertNotNull(test.createClient("4"));
        Assertions.assertNotNull(test.clientAddProduct("4","Book"));
    }

    @Test
    void clientDeleteProduct() {
        Assertions.assertNotNull(test.createProduct("Book","6.00","Each"));
        Assertions.assertNotNull(test.createProduct("Rasoir","4.00","Each"));
        Assertions.assertNotNull(test.activeOffre("Book","ThreeForTwo","2.0"));
        Assertions.assertNotNull(test.createClient("4"));
        Assertions.assertNotNull(test.clientAddProduct("4","Book"));
        Assertions.assertNotNull(test.clientDeleteProduct("4","Book","1.0"));
    }

    @Test
    void checkCartPrinClient() {
        Assertions.assertNotNull(test.createProduct("Book","6.00","Each"));
        Assertions.assertNotNull(test.createProduct("Rasoir","4.00","Each"));
        Assertions.assertNotNull(test.activeOffre("Book","ThreeForTwo","2.0"));
        Assertions.assertNotNull(test.createClient("1"));
        Assertions.assertNotNull(test.clientAddProduct("1","Book"));
        Assertions.assertNotNull(test.checkCartPriceClient("1"));
    }

    @Test
    void checkCartPrice() {
        Assertions.assertNotNull(test.createProduct("Book","6.00","Each"));
        Assertions.assertNotNull(test.createProduct("Rasoir","4.00","Each"));
        Assertions.assertNotNull(test.activeOffre("Book","ThreeForTwo","1.0"));
        Assertions.assertNotNull(test.createClient("1"));
        Assertions.assertNotNull(test.clientAddProduct("1","Book"));
        Assertions.assertNotNull(test.checkCartPrinClient("1"));
    }
}
