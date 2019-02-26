package fr.esiea;

import fr.esiea.supermarket.model.*;
import fr.esiea.supermarket.model.offers.SpecialOfferType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class SupermarketTest {

    @Test
    public void testOffers() {

        // Initialisation du catalogue
        SupermarketCatalog catalog = new FakeCatalog();
        Product toothbrush = new Product("toothbrush", ProductUnit.Each);
        catalog.addProduct(toothbrush, 0.99);
        Product apples = new Product("apples", ProductUnit.Kilo);
        catalog.addProduct(apples, 1.0);

        // Caddie
        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(apples, 4.5);

        Teller teller = new Teller(catalog);
        // Ajout de l'offre sur la brosse à dents au catalogue
        teller.addSpecialOffer(SpecialOfferType.TenPercentDiscount, toothbrush,10);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        double expected = 4.5 * 1.0;
        double totalPrice = receipt.getTotalPrice();

        Assertions.assertThat(totalPrice).isEqualTo(expected).as("2.5kg de pommes à 1.99€ avec réduction sur les brosses à dents");


    }

    @Test
    public void testOfferTenPercentDiscount() {

        SupermarketCatalog catalog = new FakeCatalog();

        Product rice = new Product("rice",ProductUnit.Each);
        catalog.addProduct(rice, 0.49);

        ShoppingCart cart = new ShoppingCart();
        cart.addItem(rice);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.TenPercentDiscount, rice,10);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        double expected = 0.49 - (0.49*0.1);
        double totalPrice = receipt.getTotalPrice();

        Assertions.assertThat(totalPrice).isEqualTo(expected).as(" test offer ten percent discount");

    }

    @Test
    public void testOfferThreeForTwo() {

        SupermarketCatalog catalog = new FakeCatalog();

        Product toothbrush = new Product("toothbrush", ProductUnit.Each);
        catalog.addProduct(toothbrush, 5.99);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(toothbrush, 3.0);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.ThreeForTwo, toothbrush,0);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        double expected = 5.99 * 2;
        double totalPrice = receipt.getTotalPrice();

        Assertions.assertThat(totalPrice).isEqualTo(expected).as("testr offer three for two");

    }

    @Test
    public void testOfferTwoForAmount() {

        SupermarketCatalog catalog = new FakeCatalog();

        Product tomatoesBox = new Product("tomatoes Box", ProductUnit.Each);
        catalog.addProduct(tomatoesBox,0.99);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(tomatoesBox, 2.0);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.TwoForAmount, tomatoesBox,2.99);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        double expected = 2.99;
        double totalPrice = receipt.getTotalPrice();

        Assertions.assertThat(totalPrice).isEqualTo(expected).as("test offer two for amount");

    }

    @Test
    public void testFiveForAmount() {

        SupermarketCatalog catalog = new FakeCatalog();
        Product toothpaste = new Product("toothpaste", ProductUnit.Each);
        catalog.addProduct(toothpaste, 1.79);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(toothpaste, 5.0);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.FiveForAmount, toothpaste, 5.00);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        double expected = 5.00;
        double totalPrice = receipt.getTotalPrice();

        Assertions.assertThat(totalPrice).isEqualTo(expected).as("test offer five for Amount");
    }

    }
