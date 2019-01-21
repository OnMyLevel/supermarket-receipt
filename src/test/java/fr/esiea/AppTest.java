package fr.esiea;

import fr.esiea.supermarket.model.*;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        Assertions.assertThat(true).isTrue();

    }
    @Test
    public void appleTest() {
        SupermarketCatalog catalog = new FakeCatalog();
        Product toothbrush = new Product("toothbrush", ProductUnit.Each);
        catalog.addProduct(toothbrush, 0.99);
        Product apples = new Product("apples", ProductUnit.Kilo);
        catalog.addProduct(apples, 1.99);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(apples, 2.5);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.TenPercentDiscount, toothbrush, 10.0);

        Receipt receipt = teller.checksOutArticlesFrom(cart);
        System.out.println(cart.toString());
        System.out.println("price"+catalog.getUnitPrice(toothbrush));
        System.out.println("price"+catalog.getUnitPrice(apples));
        System.out.println("name"+toothbrush.getName().toString());
        System.out.println("unit"+toothbrush.getUnit());
        System.out.println("name apple"+apples.getName().toString());
        System.out.println("unit"+apples.getUnit());
        Assertions.assertThat("4.975").isEqualTo(receipt.getTotalPrice().toString());
        Assertions.assertThat(0).isEqualTo(receipt.getDiscounts().size());
        Assertions.assertThat(1).isEqualTo(receipt.getItems().size());

    // Todo: complete this test
    }
}
