package fr.esiea;

import fr.esiea.supermarket.model.*;
import fr.esiea.supermarket.model.offers.SpecialOfferType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FiveForAmountTest {
    @Test
    public void testFiveForAmount() {
        SupermarketCatalog catalog = new FakeCatalog();
        ShoppingCart cart = new ShoppingCart();
        Teller teller = new Teller(catalog);
        Product toothpaste  = new Product("toothpaste tube", ProductUnit.Each);
        catalog.addProduct(toothpaste , 1.79);
        cart.addItemQuantity(toothpaste , 5);
        teller.addSpecialOffer(SpecialOfferType.FiveForAmount, toothpaste , 7.49);
        Receipt receipt = teller.checksOutArticlesFrom(cart);
        assertThat(receipt.getTotalPrice()).as("five toothpaste tubes for :").isEqualTo(7.49);
        cart.addItem(toothpaste);
        receipt = teller.checksOutArticlesFrom(cart);
        assertThat(receipt.getTotalPrice()).isEqualTo(9.28);
    }
}
