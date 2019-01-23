package fr.esiea;

import fr.esiea.supermarket.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



public class ShoppingCartTest {

    @Test
    public void testOffers(){

        SupermarketCatalog catalog = new FakeCatalog();
        Product rasoir = new Product("Rasoir", ProductUnit.Each);
        Product stylo = new Product("Stylo", ProductUnit.Each);
        Product salade = new Product("Salade", ProductUnit.Kilo);
        Product pain = new Product("Pain", ProductUnit.Kilo);
        Product trousse = new Product("Trousse", ProductUnit.Each);
        Product pomme = new Product("Pomme", ProductUnit.Kilo);
        Product  jus = new Product("Jus", null);

        catalog.addProduct(rasoir, 1.50);
        catalog.addProduct(stylo, 1.0);
        catalog.addProduct(salade, 0.50);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(rasoir, 3);
        cart.addItemQuantity(salade, 5);
        cart.addItemQuantity(rasoir, 3);
        cart.addItemQuantity(stylo,10);


        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.FiveForAmount, rasoir, 5);
        teller.addSpecialOffer(SpecialOfferType.TwoForAmount, stylo, 2);
        teller.addSpecialOffer(SpecialOfferType.TwoForAmount, salade, 5);


        Receipt receipt = teller.checksOutArticlesFrom(cart);
        Assertions.assertNotNull(cart.toString());

    }

    @Test
    public void testQuantity() {
        SupermarketCatalog catalog = new FakeCatalog();
        Product rasoir = new Product("Rasoir", ProductUnit.Each);
        catalog.addProduct(rasoir, 1.50);
        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(rasoir, 1);

        Teller teller = new Teller(catalog);
        Receipt receipt = teller.checksOutArticlesFrom(cart);
        Assertions.assertNotNull(cart.toString());
        Assertions.assertNotNull(teller.toString());
    }


}
