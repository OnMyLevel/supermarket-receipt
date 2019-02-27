package fr.esiea.webAppTest;

import fr.esiea.supermarket.webApp.SpringWebApplicationController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class SpringWebApplicationControllerTest {
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
    }

    @Test
    void listUsers() {
    }

    @Test
    void main() {
    }

    @Test
    void activationOffre() {
    }

    @Test
    void desactivationOffre() {
    }

    @Test
    void createClient() {
    }

    @Test
    void clientAddProduct() {
    }

    @Test
    void clientDeleteProduct() {
    }

    @Test
    void checkCartPrinClient() {
    }

    @Test
    void checkCartPrice() {
    }
}
