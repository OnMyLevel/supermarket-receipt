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
        catalog.addProduct(rasoir, 1.50);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(rasoir, 3);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.ThreeForTwo, rasoir, 1);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        ReceiptPrinter printerDefault = new ReceiptPrinter();

        String expectedPrint =
            "Rasoir                              4.50\n"
                +"  1.50 * 3\n"
                +"3 for 2(Rasoir)                    -1.50\n"
                +"\n"
                +"Total:                              3.00";

        Assertions.assertThat(expectedPrint).isEqualTo(printerDefault.printReceipt(receipt));
    }
}


