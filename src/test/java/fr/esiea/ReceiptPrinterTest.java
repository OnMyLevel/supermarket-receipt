package fr.esiea;

import fr.esiea.supermarket.ReceiptPrinter;
import fr.esiea.supermarket.model.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


public class ReceiptPrinterTest {

    @Test
    public void receiptPrinterTest() {

        SupermarketCatalog catalog = new FakeCatalog();
        Product rasoir = new Product("Rasoir", ProductUnit.Each);
        Product salade = new Product("Salade", ProductUnit.Kilo);
        catalog.addProduct(rasoir, 1.50);
        catalog.addProduct(salade, 0.50);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(rasoir, 3);
        cart.addItemQuantity(salade, 1);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.ThreeForTwo, rasoir, 1);
        teller.addSpecialOffer(SpecialOfferType.TenPercentDiscount, salade, 1);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        ReceiptPrinter printerDefault = new ReceiptPrinter();

        String expectedPrint =
            "Rasoir                              4.50\n"
                +"  1.50 * 3\n"
                +"Salade                              0.50\n"
                +"3 for 2(Rasoir)                    -1.50\n"
                +"1.0% off(Salade)                   -0.01\n"
                +"\n"
                +"Total:                              3.50";
        Assertions.assertThat(expectedPrint).isEqualTo(printerDefault.printReceipt(receipt));
    }

    @Test
    public void defaultReceiptPrinterTest() {


        SupermarketCatalog catalog = new FakeCatalog();

        Product toothbrush = new Product("Toothbrush", ProductUnit.Each);
        catalog.addProduct(toothbrush, 0.99);

        Product apples = new Product("Apples", ProductUnit.Kilo);
        catalog.addProduct(apples, 1.99);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(toothbrush, 2);
        cart.addItemQuantity(toothbrush,1);
        cart.addItemQuantity(apples, 2.5);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.ThreeForTwo, toothbrush, 1);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        ReceiptPrinter printerDefault = new ReceiptPrinter();

        String expectedPrintDefault =
            "Toothbrush                          1.98\n"
                +"  0.99 * 2\n"
                +"Toothbrush                          0.99\n"
                +"Apples                              4.98\n"
                +"  1.99 * 2.500\n"
                +"3 for 2(Toothbrush)                -0.99\n"
                +"\n"
                +"Total:                              6.96";
        Assertions.assertThat(expectedPrintDefault).isEqualTo(printerDefault.printReceipt(receipt)).as("Test du Receipt Printer par defaut");

    }

    @Test
    public void colsReceiptPrinterTest() {

        SupermarketCatalog catalog = new FakeCatalog();

        Product toothbrush = new Product("Toothbrush", ProductUnit.Each);
        catalog.addProduct(toothbrush, 0.99);

        Product apples = new Product("Apples", ProductUnit.Kilo);
        catalog.addProduct(apples, 1.99);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(toothbrush, 2);
        cart.addItemQuantity(toothbrush,1);
        cart.addItemQuantity(apples, 2.5);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.ThreeForTwo, toothbrush, 1);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        ReceiptPrinter printerWithCols = new ReceiptPrinter(30);

        String expectedPrintWithCol =
            "Toothbrush                1.98\n"
                +"  0.99 * 2\n"
                +"Toothbrush                0.99\n"
                +"Apples                    4.98\n"
                +"  1.99 * 2.500\n"
                +"3 for 2(Toothbrush)      -0.99\n"
                +"\n"
                +"Total:                    6.96";

        Assertions.assertThat(expectedPrintWithCol).isEqualTo(printerWithCols.printReceipt(receipt)).as("Test du Receipt printer avec 30 colonnes");

    }
}


