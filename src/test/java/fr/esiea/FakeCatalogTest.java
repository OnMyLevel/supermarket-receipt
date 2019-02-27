package fr.esiea;

import fr.esiea.supermarket.model.FakeCatalog;
import fr.esiea.supermarket.model.Product;
import fr.esiea.supermarket.model.ProductUnit;
import fr.esiea.supermarket.model.SupermarketCatalog;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FakeCatalogTest {

    @Test
    public void testGetProduct() {
        SupermarketCatalog catalog = new FakeCatalog();
        Product rasoir = new Product("Rasoir", ProductUnit.Each);
        Product boot = new Product("Boot", ProductUnit.Each);
        catalog.addProduct(rasoir, 1.50);
        Assertions.assertNotNull(catalog.getProduct("Rasoir"));
    }

    @Test
    public void testGetProducts() {
        SupermarketCatalog catalog = new FakeCatalog();
        Product rasoir = new Product("Rasoir", ProductUnit.Each);
        Product boot = new Product("Boot", ProductUnit.Each);
        catalog.addProduct(rasoir, 1.50);
        catalog.addProduct(boot,1.50);
        Assertions.assertNotNull(catalog.getProducts());
    }

    @Test
    public void testDeleteProducts(){
        SupermarketCatalog catalog = new FakeCatalog();
        Product rasoir = new Product("Rasoir", ProductUnit.Each);
        catalog.addProduct(rasoir, 1.50);
        catalog.deleteProduct("Rasoir");
    }
}
