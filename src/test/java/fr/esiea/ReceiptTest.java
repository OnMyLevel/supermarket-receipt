package fr.esiea;

import fr.esiea.supermarket.model.*;
import fr.esiea.supermarket.model.offers.SpecialOfferType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


public class ReceiptTest {

    @Test
    public void receiptPriceTest() {
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
        Assertions.assertThat("4.975").isEqualTo(receipt.getTotalPrice().toString());
    }

    @Test
    public void receiptSiseDiscountTest() {
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
        Assertions.assertThat(0).isEqualTo(receipt.getDiscounts().size());
    }

    @Test
    public void receiptNbitemsTest() {
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
        Assertions.assertThat(1).isEqualTo(receipt.getItems().size());
    }

    @Test
    public void receiptDiscountTest() {
        SupermarketCatalog catalog = new FakeCatalog();
        Product toothbrush = new Product("toothbrush", ProductUnit.Each);
        catalog.addProduct(toothbrush, 0.99);
        Discount testDiscount = new Discount(toothbrush,"solde",0.99);
        ShoppingCart cart = new ShoppingCart();
        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.TenPercentDiscount, toothbrush, 10.0);
        Receipt receipt = teller.checksOutArticlesFrom(cart);
        receipt.addDiscount(testDiscount);
        Assertions.assertThat(true).isEqualTo(receipt.getDiscounts().contains(testDiscount));
    }


    @Test
    public void testReceiptItem(){
        Product book = new Product("Book", ProductUnit.Each);
        Product pen = new Product("Pen", ProductUnit.Each);
        ReceiptItem receipt1 = new ReceiptItem(book,10,3, 30.0);
        ReceiptItem receipt2 = new ReceiptItem(book,10,2, 30.0);
        ReceiptItem receipt3 = new ReceiptItem(book,10,3, 20.0);
        ReceiptItem receipt4 = new ReceiptItem(book,5,3, 30.0);
        ReceiptItem receipt5 = new ReceiptItem(pen,1,1.5, 1.5);
        Assertions.assertThat(receipt1.getPrice()).isEqualTo(3.0);
        Assertions.assertThat(receipt1.getTotalPrice()).isEqualTo(30.0);
        Assertions.assertThat(receipt1.equals(receipt4)).isEqualTo(false);
        Assertions.assertThat(receipt1.equals(receipt5)).isEqualTo(false);
        Assertions.assertThat(receipt1.getProduct()).isEqualTo(book);
        Assertions.assertThat(receipt1.hashCode()).as("hash").isEqualTo(receipt1.hashCode());
        Assertions.assertThat(receipt1.hashCode()).as("hash").isNotEqualTo(receipt2.hashCode());
        Assertions.assertThat(receipt1.getQuantity()).isEqualTo(10.0);
        Assertions.assertThat(receipt1.equals(receipt2)).isEqualTo(false);
        Assertions.assertThat(receipt1.equals(receipt3)).isEqualTo(false);
    }

    @Test
    public void testReceiptItemEquals(){
        Product apple = new Product("Apple", ProductUnit.Kilo);
        Product tomate = new Product("Tomate", ProductUnit.Kilo);
        ReceiptItem receipt = new ReceiptItem(apple, 2.0, 2.0, 6.0);
        ReceiptItem receipt1 = new ReceiptItem(tomate, 3.0, 1.0, 1.0);
        ReceiptItem receipt2 = new ReceiptItem(apple, 1.0, 2.0, 4.0);
        ReceiptItem receiptItem6 = new ReceiptItem(tomate, 2.0, 2.0, 6.0);
        ReceiptItem receiptItem4 = new ReceiptItem(apple, 2.0, 2.0, 5.0);
        ReceiptItem receiptItem3 = new ReceiptItem(apple, 2.0, 3.0, 4.0);
        ReceiptItem receiptItem5 = new ReceiptItem(apple, 2.0, 2.0, 6.0);
        Assertions.assertThat(receipt).isNotEqualTo(null);
        Assertions.assertThat(receipt.equals(apple)).isEqualTo(false);
        Assertions.assertThat(receipt.equals(receipt)).isEqualTo(true);
        Assertions.assertThat(receipt.equals(receiptItem4)).isEqualTo(false);
        Assertions.assertThat(receipt.equals(receiptItem5)).isNotEqualTo(false);
        Assertions.assertThat(receipt.equals(receiptItem6)).isEqualTo(false);
        Assertions.assertThat(receipt.equals(receipt1)).isEqualTo(false);
        Assertions.assertThat(receipt.equals(receipt2)).isEqualTo(false);
        Assertions.assertThat(receipt.equals(receiptItem3)).isEqualTo(false);
    }
}
