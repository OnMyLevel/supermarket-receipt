package fr.esiea;

import fr.esiea.supermarket.model.*;
import fr.esiea.supermarket.model.offers.SpecialOfferType;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TwoForAmount {

    @Test
    public void testTwoForAmount() {
        SupermarketCatalog catalog = new FakeCatalog();
        ShoppingCart cart = new ShoppingCart();
        Teller teller = new Teller(catalog);
        Product toothbrush = new Product("toothbrush", ProductUnit.Each);
        catalog.addProduct(toothbrush, 0.99);
        cart.addItemQuantity(toothbrush, 2);
        teller.addSpecialOffer(SpecialOfferType.TwoForAmount, toothbrush, 0.99);
        Receipt receipt = teller.checksOutArticlesFrom(cart);
        assertThat(receipt.getTotalPrice()).as("two toothbrushes for :").isEqualTo(0.99);
    }
}
