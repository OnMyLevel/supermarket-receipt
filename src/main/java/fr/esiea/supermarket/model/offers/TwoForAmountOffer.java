package fr.esiea.supermarket.model.offers;

import fr.esiea.supermarket.model.Discount;
import fr.esiea.supermarket.model.Product;

public class TwoForAmountOffer  implements Offer {

    private final Product p;
    private final double argument;


    public TwoForAmountOffer(Product p, double argument) {
        this.p = p;
        this.argument = argument;

    }

    @Override
    public Discount getDiscount(Product p, double quantity, double unitPrice) {

        Discount discount = null;
        if ((int) quantity >= 2) {
            double total = argument * (int) quantity / 2 + (int) quantity % 2 * unitPrice;
            double discountN = unitPrice * quantity - total;
            discount = new Discount(p, "2 for " + argument, discountN);
        }

        return discount;
    }
}
