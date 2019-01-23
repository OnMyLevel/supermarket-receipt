package fr.esiea;

import fr.esiea.supermarket.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



public class ShoppingCartTest {

    @Test
    public void testSomething(){

        SupermarketCatalog catalog = new FakeCatalog();
        Product rasoir = new Product("Rasoir", ProductUnit.Each);
        Product stylo = new Product("Stylo", ProductUnit.Each);
        Product salade = new Product("Salade", ProductUnit.Kilo);
        catalog.addProduct(rasoir, 1.50);
        catalog.addProduct(stylo, 1.0);
        catalog.addProduct(salade, 0.50);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(rasoir, 3);
        cart.addItemQuantity(salade, 5);
        cart.addItemQuantity(rasoir, 3);
        cart.addItemQuantity(stylo, 1.0);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.FiveForAmount, rasoir, 5);
       // teller.addSpecialOffer(SpecialOfferType.TwoForAmount, stylo, 1.0);
        teller.addSpecialOffer(SpecialOfferType.TwoForAmount, salade, 5);

        Receipt receipt = teller.checksOutArticlesFrom(cart);
        Assertions.assertNotNull(cart.toString());

    }
}
