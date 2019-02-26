package fr.esiea;

import fr.esiea.supermarket.model.*;
import fr.esiea.supermarket.model.offers.SpecialOfferType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TellerTest {

    @Test
    public void testOffers() {

        // Initialisation du catalogue
        SupermarketCatalog catalog = new FakeCatalog();
        Product toothbrush = new Product("Toothbrush", ProductUnit.Each);
        catalog.addProduct(toothbrush, 0.99);
        Product apples = new Product("Apples", ProductUnit.Kilo);
        catalog.addProduct(apples, 1.0);
        // Caddie
        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(apples, 4.5);
        Teller teller = new Teller(catalog);
        // Ajout de l'offre sur la brosse à dents au catalogue
        teller.addSpecialOffer(SpecialOfferType.TenPercentDiscount, toothbrush,10);
        Receipt receipt = teller.checksOutArticlesFrom(cart);
        double expected = 4.5 * 1.0;
        double totalPrice = receipt.getTotalPrice();teller.getOffers();
        Assertions.assertNotNull(teller.getOffers());
        Assertions.assertNotNull(teller.deleteSpecialOffer(toothbrush));
    }
}
