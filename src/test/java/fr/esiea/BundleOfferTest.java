package fr.esiea;

import fr.esiea.supermarket.model.*;
import fr.esiea.supermarket.model.offers.SpecialOfferType;
import fr.esiea.supermarket.model.offers.bundles.BundleOffer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;


public class BundleOfferTest
{

    /*@Test
    public void testBundleOffer() {

        SupermarketCatalog catalog = new FakeCatalog();

        Product rasoir = new Product("rasoir", ProductUnit.Each);
        catalog.addProduct(rasoir,2.00);

        Product boot = new Product("boot", ProductUnit.Each);
        catalog.addProduct(boot, 3.00);


        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(rasoir, 1.0);
        cart.addItemQuantity(boot, 1.0);

        Map<Product,Double> bundleProductQuantities = new HashMap<>();
        bundleProductQuantities.put(rasoir,1.0);
        bundleProductQuantities.put(boot,1.0);

        Teller teller = new Teller(catalog);
        teller.addSpecialBundleOffer(SpecialOfferType.BundlePercentDiscount,bundleProductQuantities, 10);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        double expectedTotalPrice = (2.00 + 3.00) - (2.00 + 3.00) * 0.1;

        double totalPrice = receipt.getTotalPrice();
        Assertions.assertThat(totalPrice).isEqualTo(expectedTotalPrice);
    }*/



    /*@Test
    public void testCompareBundle() {
        SupermarketCatalog catalog = new FakeCatalog();

        Product rasoir = new Product("rasoir", ProductUnit.Each);
        catalog.addProduct(rasoir, 1.5);
        Product boot = new Product("boot", ProductUnit.Each);
        catalog.addProduct(boot, 3);


        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(rasoir, 1);
        cart.addItemQuantity(boot, 1);
        BundleOffer bndl = new BundleOfferTest(cart.getItems());

        //FAKE
        ShoppingCart fakeCart = new ShoppingCart();
        fakeCart.addItemQuantity(rasoir, 1);
        fakeCart.addItemQuantity(boot, 1);
        BundleOffer promotionBundle = new BundleOfferTest(fakeCart.getItems());

        Teller teller = new Teller(catalog);
        if(bndl.compareBundle(promotionBundle.getProductQuantities()))
        {
            for (ProductQuantity p : bndl.getProductQuantities_()) {
                teller.addSpecialOffer(SpecialOfferType.TenPercentDiscount, p, 1);
            }
        }

        Receipt receipt = teller.checksOutArticlesFrom(cart);
        Assertions.assertThat(receipt.getTotalPrice()).isEqualTo(1.5+3+2);
    }*/


}
